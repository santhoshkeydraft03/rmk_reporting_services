package com.keydraft.reporting_software.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {
    private String secretKey;
    private long expiration = 86400000; // 1 day in milliseconds
    private String tokenPrefix = "Bearer ";
    private String headerString = "Authorization";
}