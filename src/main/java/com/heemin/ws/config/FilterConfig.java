package com.heemin.ws.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heemin.ws.filter.JwtAuthorizationFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> jwtFilter(ObjectMapper objectMapper) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtAuthorizationFilter(objectMapper));
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
