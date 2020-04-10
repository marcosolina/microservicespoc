package com.marco.securityservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Standard Spring class to retrieve some properties
 * 
 * @author msolina
 *
 */
@Configuration
@ConfigurationProperties("com.marco.securityservice.security")
public class AppProperties {
    private String privateKey;
    private String resourceId;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

}
