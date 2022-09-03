package com.hhoa.blog.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author hhoa
 * @date 2022/5/5
 **/

@Data
@ConfigurationProperties("jwt")
public class JwtSecurityProperties {
    public String tokenHeader = "Authorization";
    private String secret;
    private Long expiration = 604800L;
    private String tokenHead = "Bearer";
    private Integer refreshTime = 30;
    private IgnoreUrlsConfig ignored = new IgnoreUrlsConfig();

    @Data
    public static class IgnoreUrlsConfig {
        private List<String> urls;
    }
}
