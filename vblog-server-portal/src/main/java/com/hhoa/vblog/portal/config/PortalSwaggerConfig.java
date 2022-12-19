package com.hhoa.vblog.portal.config;

import com.hhoa.vblog.common.config.BaseSwaggerConfig;
import com.hhoa.vblog.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置.
 *
 * @author hhoa
 * @since 2022/5/11
 **/
@Configuration
public class PortalSwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.demo.vblog.portal.controller")
                .title("RETrack后台系统")
                .description("任何值得到达的地方，都没有捷径!")
                .contactName("hhoao")
                .contactUrl("")
                .contactEmail("huanghaohhoa@163.com")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
