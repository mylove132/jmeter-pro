package com.lzh.jmeter.security.config;

import com.lzh.jmeter.commons.redis.service.RedisService;
import com.lzh.jmeter.security.handle.AuthenticationInterceptor;
import com.lzh.jmeter.security.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JmeterWebApplicationConfig implements WebMvcConfigurer {

    private final RedisService redisService;

    private final TokenService tokenService;

    @Value("${config.excludePath}")
    private String excludePath;

    public JmeterWebApplicationConfig(RedisService redisService, TokenService tokenService) {
        this.redisService = redisService;
        this.tokenService = tokenService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor(redisService, tokenService)).
                addPathPatterns("/**").
                excludePathPatterns(excludePath.split(","));
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
