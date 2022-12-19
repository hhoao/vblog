package com.hhoa.vblog.admin.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置文件.
 *
 * @author hhoa
 * @since 2022/5/5
 **/
@Configuration
@MapperScan(basePackages = {"com.hhoa.vblog.admin.dao", "com.hhoa.vblog.mgb.mapper"})
public class AdminMyBatisConfig {
}
