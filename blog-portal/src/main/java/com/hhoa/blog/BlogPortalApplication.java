package com.hhoa.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hhoa
 * @since 2022/8/30
 **/
@SpringBootApplication(scanBasePackages = {"com.hhoa.blog"})
public class BlogPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogPortalApplication.class);
    }
}
