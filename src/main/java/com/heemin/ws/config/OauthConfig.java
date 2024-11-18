package com.heemin.ws.config;

import com.heemin.ws.model.service.auth.OauthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(OauthProperties.class)
@Configuration
public class OauthConfig {
    
}
