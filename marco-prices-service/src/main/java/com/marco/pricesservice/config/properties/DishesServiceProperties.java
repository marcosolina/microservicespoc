package com.marco.pricesservice.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("rest.request.service.dishes")
public class DishesServiceProperties {
    private String host;
    private String protocol;
    private String findDishByName;
    private String findAllDishes;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getFindDishByName(String dishName) {
        return findDishByName + "/" + dishName;
    }

    public void setFindDishByName(String findDishByName) {
        this.findDishByName = findDishByName;
    }

    public String getFindAllDishes() {
        return findAllDishes;
    }

    public void setFindAllDishes(String findAllDishes) {
        this.findAllDishes = findAllDishes;
    }

}
