package com.hhoa.vblog.portal.config;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.jwt.JWT;
import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.portal.service.UmsAccountCacheService;
import com.hhoa.vblog.portal.service.UmsAccountService;
import com.hhoa.vblog.portal.service.UmsResourceService;
import com.hhoa.vblog.security.component.DynamicSecurityService;
import com.hhoa.vblog.security.config.JwtSecurityProperties;
import com.hhoa.vblog.security.util.DefaultJwtTokenServiceImpl;
import com.hhoa.vblog.security.util.JwtTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 安全配置.
 *
 * @author hhoa
 * @since 2022 /5/5
 */
@Configuration
@RequiredArgsConstructor
public class AdminJwtSecurityConfig {
    /**
     * 自定义UserDetailsService用来自定义获取用户、更新用户等操作.
     *
     * @param administratorService the administrator service
     * @return userDetailsService user details service
     */
    @Bean
    public static UserDetailsService userDetailsService(UmsAccountService administratorService) {
        return administratorService::getAccountDetails;
    }

    /**
     * 资源认证选举者, 用于认证资源访问请求.
     *
     * @return 选举者 access decision voter
     */
    @Bean
    @SuppressWarnings("all")
    public AccessDecisionVoter resourceAccessDecisionVoter() {
        return new AccessDecisionVoter() {
            @Override
            public boolean supports(ConfigAttribute attribute) {
                return true;
            }

            @Override
            public boolean supports(Class clazz) {
                return true;
            }

            @Override
            @SuppressWarnings("unchecked")
            public int vote(Authentication authentication, Object object, Collection collection) {
                // 当接口未被配置资源时直接放行
                if (CollUtil.isEmpty(collection)) {
                    return AccessDecisionVoter.ACCESS_ABSTAIN;
                }
                for (ConfigAttribute configAttribute : (Collection<ConfigAttribute>) collection) {
                    //将访问所需资源或用户拥有资源进行比对
                    String needAuthority = configAttribute.getAttribute();
                    for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                        if (needAuthority.trim().equals(grantedAuthority.getAuthority())) {
                            return AccessDecisionVoter.ACCESS_GRANTED;
                        }
                    }
                }
                return AccessDecisionVoter.ACCESS_DENIED;
            }
        };
    }

    /**
     * 动态权限服务配置.
     */
    @Configuration
    @Aspect
    @RequiredArgsConstructor
    public static class AdminDynamicSecurityServiceConfig {
        private final UmsResourceService resourceService;
        private Map<AntPathRequestMatcher, ConfigAttribute> dataSource;

        /**
         * Gets data source.
         *
         * @return the data source
         */
        public Map<AntPathRequestMatcher, ConfigAttribute> getDataSource() {
            refreshDataSource();
            return dataSource;
        }

        /**
         * 资源权限变动动态刷新DataSource.
         */
        @Pointcut("execution(* com.hhoa.vblog.portal.service.impl."
                + "UmsResourceServiceImpl.delete*(..))"
                + "|| execution(* com.hhoa.vblog.portal.service.impl."
                + "UmsResourceServiceImpl.update*(..))"
                + "|| execution(* com.hhoa.vblog.portal.service.impl."
                + "UmsResourceServiceImpl.add*(..))")
        public void alterDataSource() {
        }

        /**
         * 刷新DataSource.
         */
        @AfterReturning("alterDataSource()")
        public void refreshDataSource() {
            if (this.dataSource == null) {
                this.dataSource = new ConcurrentHashMap<>();
            }
            this.dataSource.clear();
            refreshResourcesDataSource();
        }

        /**
         * 刷新资源.
         */
        private void refreshResourcesDataSource() {
            List<UmsResource> allResources = resourceService.getAllResources();

            for (UmsResource retResource : allResources) {
                //url:method 需要resourceId:resourceName 权限
                this.dataSource.put(
                        new AntPathRequestMatcher(retResource.getUrl(), retResource.getMethod()),
                        new SecurityConfig(retResource.getId() + ":" + retResource.getName()));
            }
        }

        /**
         * Dynamic security service dynamic security service.
         *
         * @return the dynamic security service
         */
        @Bean
        public DynamicSecurityService dynamicSecurityService() {
            return this::getDataSource;
        }
    }

    /**
     * 编码器配置.
     *
     * @return 编码器 password encoder
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * token服务.
     *
     * @param administratorCacheService token缓存服务
     * @param jwtSecurityProperties     jwt安全配置属性
     * @return jwtToken服务 jwt token service
     */
    @Bean
    public static JwtTokenService jwtTokenService(UmsAccountCacheService administratorCacheService,
                                                  JwtSecurityProperties jwtSecurityProperties) {
        DefaultJwtTokenServiceImpl defaultJwtTokenService = new DefaultJwtTokenServiceImpl() {
            @Override
            public String generateToken(Object subject) {
                Map<String, Object> claims = new HashMap<>(2);
                claims.put(CLAIM_KEY_CREATED, new Date());
                claims.put(JWT.SUBJECT, subject);
                return Jwts.builder()
                        .setClaims(claims)
                        .signWith(SignatureAlgorithm.HS512, getSecret())
                        .compact();
            }


            @Override
            public boolean isTokenExpired(String token) {
                String username = getSubjectFromToken(token);
                return administratorCacheService.hasKey(username);
            }

            @Override
            public boolean validateToken(String token) {
                return isTokenExpired(token);
            }

            @Override
            public String refreshHeadToken(String oldToken) {
                if (tokenRefreshJustBefore(oldToken, getRefreshTime())) {
                    return oldToken;
                }
                String username = getSubjectFromToken(oldToken);
                administratorCacheService.expire(username);
                return oldToken;
            }
        };
        BeanUtils.copyProperties(jwtSecurityProperties, defaultJwtTokenService);
        return defaultJwtTokenService;
    }
}