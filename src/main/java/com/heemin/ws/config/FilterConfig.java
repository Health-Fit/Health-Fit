package com.heemin.ws.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heemin.ws.filter.JwtAuthorizationFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> jwtFilter(ObjectMapper objectMapper, RedisTemplate redisTemplate) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtAuthorizationFilter(objectMapper, redisTemplate));
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
