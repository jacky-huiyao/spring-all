package com.hy.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.hy.mybatis.dao")
public class MybatisConfig {

    
}
