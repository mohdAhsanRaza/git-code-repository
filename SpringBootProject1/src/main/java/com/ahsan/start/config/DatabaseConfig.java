package com.ahsan.start.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    public String url;

    @Value("${spring.datasource.username}")
	public String username;

    @Value("${spring.datasource.password}")
    public String password;

    // Getters
    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}