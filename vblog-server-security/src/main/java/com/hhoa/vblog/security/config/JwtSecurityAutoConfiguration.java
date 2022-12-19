package com.hhoa.vblog.security.config;


import com.hhoa.vblog.security.component.AuthenticationTokenFilter;
import com.hhoa.vblog.security.component.DynamicSecurityMetadataSource;
import com.hhoa.vblog.security.component.DynamicSecurityService;
import com.hhoa.vblog.security.component.JwtDynamicSecurityFilter;
import com.hhoa.vblog.security.component.RestAuthenticationEntryPoint;
import com.hhoa.vblog.security.component.RestfulAccessDeniedHandler;
import com.hhoa.vblog.security.util.DefaultJwtTokenServiceImpl;
import com.hhoa.vblog.security.util.JwtTokenService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;


/**
 * 对SpringSecurity配置类的扩展，支持自定义白名单资源路径和查询用户逻辑.
 *
 * @author hhoa
 */
@EnableWebSecurity
@Configuration
@EnableConfigurationProperties(JwtSecurityProperties.class)
public class JwtSecurityAutoConfiguration {
    private final JwtSecurityProperties jwtSecurityProperties;
    private UserDetailsService userDetailsService;
    private DynamicSecurityService dynamicSecurityService;
    private AuthenticationTokenFilter authenticationTokenFilter;
    private PasswordEncoder passwordEncoder;


    private JwtTokenService jwtTokenService;
    private List<AccessDecisionVoter<?>> voters;

    public JwtSecurityAutoConfiguration(
            JwtSecurityProperties jwtSecurityProperties,
            @Autowired(required = false)
            JwtTokenService jwtTokenService) {
        this.jwtSecurityProperties = jwtSecurityProperties;
        this.jwtTokenService = jwtTokenService;
    }

    private void configureJwtTokenService() {
        this.jwtTokenService.setTokenHead(jwtSecurityProperties.getTokenHead());
        this.jwtTokenService.setExpiration(jwtSecurityProperties.getExpiration());
        Assert.hasText(jwtSecurityProperties.getSecret());
        this.jwtTokenService.setSecret(jwtSecurityProperties.getSecret());
        this.jwtTokenService.setRefreshTime(jwtSecurityProperties.getRefreshTime());
    }

    /**
     * custom filterChain.
     *
     * @param httpSecurity httpSecurity
     * @return custom filterChain
     * @throws Exception Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry
                = httpSecurity.authorizeRequests();
        // 不需要保护的资源路径允许访问
        if (jwtSecurityProperties.getIgnored().getUrls() != null) {
            for (String url : jwtSecurityProperties.getIgnored().getUrls()) {
                registry.antMatchers(url).permitAll();
            }
        }
        // 允许跨域的OPTIONS请求
        registry.antMatchers(HttpMethod.OPTIONS)
                .permitAll();
        // 其他任何请求都需要身份认证
        registry.and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                // 关闭跨站请求防护及不使用session
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 自定义权限拒绝处理类
                .and()
                .exceptionHandling()
                .accessDeniedHandler(getAccessDeniedHandler())
                .authenticationEntryPoint(getAuthenticationEntryPoint())
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(),
                        UsernamePasswordAuthenticationFilter.class);

        //有动态权限配置时添加动态权限校验过滤器
        if (dynamicSecurityService != null) {
            registry.and().addFilterBefore(getDynamicSecurityFilter(),
                    FilterSecurityInterceptor.class);
        }
        return httpSecurity.build();
    }

    /**
     * 如果容器中没有JwtTokenService,将自动创建一个.
     *
     * @return DefaultJwtTokenService
     */
    @Bean
    @Order
    @ConditionalOnMissingBean(JwtTokenService.class)
    public JwtTokenService jwtTokenService() {
        this.jwtTokenService = new DefaultJwtTokenServiceImpl();
        configureJwtTokenService();
        return this.jwtTokenService;
    }

    private AuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        if (this.authenticationTokenFilter == null) {
            this.authenticationTokenFilter = new AuthenticationTokenFilter(userDetailsService(),
                    this.jwtSecurityProperties,
                    this.jwtTokenService);
        }
        return this.authenticationTokenFilter;
    }

    @Autowired(required = false)
    public void dynamicSecurityService(DynamicSecurityService dynamicSecurityService) {
        this.dynamicSecurityService = dynamicSecurityService;
    }

    @Autowired(required = false)
    public void setDecisionVoters(List<AccessDecisionVoter<?>> accessDecisionVoterList) {
        this.voters = accessDecisionVoterList;
    }

    private JwtDynamicSecurityFilter getDynamicSecurityFilter() {
        return new JwtDynamicSecurityFilter(//new DynamicAccessDecisionManager(voters),
                new AffirmativeBased(voters),
                new DynamicSecurityMetadataSource(dynamicSecurityService),
                jwtSecurityProperties.getIgnored().getUrls());
    }

    @Autowired(required = false)
    public void passwordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired(required = false)
    public void userDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    protected UserDetailsService userDetailsService() {
        assert userDetailsService != null;
        return userDetailsService;
    }



    public AuthenticationEntryPoint getAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    public AccessDeniedHandler getAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }
}
