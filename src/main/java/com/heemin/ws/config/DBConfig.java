package com.heemin.ws.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.heemin.ws.model.dao")
public class DBConfig {
}
