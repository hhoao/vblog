package com.hhoa.blog.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hhoa
 * @since 2022/8/30
 **/

@SpringBootApplication(scanBasePackages = {"com.hhoa.blog"})
public class BlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class);
    }
}
