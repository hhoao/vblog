package com.hhoa.vblog.search.config;

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
public class SearchSwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.hhoa.vblog.search")
                .title("vblog search module")
                .description("任何值得到达的地方，都没有捷径!")
                .contactName("hhoao")
                .contactUrl("")
                .contactEmail("huanghaohhoa@163.com")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
