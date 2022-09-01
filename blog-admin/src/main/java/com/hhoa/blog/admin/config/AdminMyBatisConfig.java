package com.hhoa.blog.admin.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置文件
 * @author hhoa
 * @date 2022/5/5
 **/
@Configuration
@MapperScan(basePackages = {"com.hhoa.blog.admin.dao", "com.hhoa.blog.mgb.mapper"})
public class AdminMyBatisConfig {
}
