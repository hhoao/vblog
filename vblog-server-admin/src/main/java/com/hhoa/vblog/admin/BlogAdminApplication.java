package com.hhoa.vblog.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Blog admin application.
 *
 * @author hhoa
 * @since 2022 /8/30
 */
@SpringBootApplication(scanBasePackages = {"com.hhoa.vblog"})
public class BlogAdminApplication {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class);
    }
}
