package com.hhoa.vblog.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jwt配置属性.
 *
 * @author hhoa
 * @since 2022/5/5
 **/

@Data
@ConfigurationProperties("jwt")
public class JwtSecurityProperties {
    /**
     * token头部.
     */
    public String tokenHeader = "Authorization";

    /*
     * JWT需要设置的密钥(必须)。
     */
    private String secret;

    /**
     * token过期时间.
     */
    private Long expiration = 604800L;

    /**
     * 载荷.
     */
    private String tokenHead = "Bearer";

    /**
     * 刷新时间.
     */
    private Integer refreshTime = 30;

    private DynamicSecurityFilterProperties filter;

}
