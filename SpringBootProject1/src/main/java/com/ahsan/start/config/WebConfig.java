package com.ahsan.start.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfig {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("src/main/webapp/"); // Set the prefix to the directory where JSPs are located
        resolver.setSuffix(".jsp"); // Set the suffix to .jsp
        return resolver;
    }
}
