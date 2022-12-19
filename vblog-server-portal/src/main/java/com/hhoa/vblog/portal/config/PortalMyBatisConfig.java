package com.hhoa.vblog.portal.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置文件.
 *
 * @author hhoa
 * @since 2022/5/5
 **/
@Configuration
@MapperScan(basePackages = {"com.hhoa.vblog.portal.dao", "com.hhoa.vblog.mgb.mapper"})
public class PortalMyBatisConfig {
}
