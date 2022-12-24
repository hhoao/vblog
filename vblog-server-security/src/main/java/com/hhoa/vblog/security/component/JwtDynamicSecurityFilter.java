package com.hhoa.vblog.security.component;

import com.hhoa.vblog.security.config.DynamicSecurityFilterProperties;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 动态权限过滤器，用于实现基于路径的动态权限过滤.
 */
public class JwtDynamicSecurityFilter extends AbstractSecurityInterceptor implements Filter {
    private final DynamicSecurityMetadataSource dynamicSecurityMetadataSource;
    //    private final List<String> ignoredUrls;
    private final DynamicSecurityFilterProperties filterProperties;

    /**
     * 设置jwt过滤器constructor.
     *
     * @param dynamicAcc                    决定者
     * @param dynamicSecurityMetadataSource 安全信息
     */
    public JwtDynamicSecurityFilter(AccessDecisionManager dynamicAcc,
                                    DynamicSecurityMetadataSource dynamicSecurityMetadataSource,
                                    DynamicSecurityFilterProperties filterProperties
    ) {
        setAccessDecisionManager(dynamicAcc);
        this.dynamicSecurityMetadataSource = dynamicSecurityMetadataSource;
        this.filterProperties = filterProperties;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        InterceptorStatusToken token = null;
        //OPTIONS请求直接放行
        if (request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            return;
        }
        //白名单请求直接放行
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String path : filterProperties.getIgnored()) {
            if (pathMatcher.match(path, request.getRequestURI())) {
                fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
                return;
            }
        }

        // 默认需要验证
        if (filterProperties.getAuthenticated()) {
            //此处会调用AccessDecisionManager中的decide方法进行鉴权操作
            token = super.beforeInvocation(fi);
        // 默认不需要验证，验证需要验证的请求
        } else {
            for (String path : filterProperties.getInclude()) {
                if (pathMatcher.match(path, request.getRequestURI())) {
                    token = super.beforeInvocation(fi);
                    break;
                }
            }
        }
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return dynamicSecurityMetadataSource;
    }

}
