package com.utsavsharma.smartContactManager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @file WebConfig.java
 *       Author: Utsav Sharma
 *       Date: 23-08-2024
 *       Time: 15:17:18
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        // Configure static resource handling
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        // Configure the view resolver with suffix and prefix for views
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/templates/"); // Adjust based on where your views are stored
        resolver.setSuffix(".html");
        return resolver;
    }
}
