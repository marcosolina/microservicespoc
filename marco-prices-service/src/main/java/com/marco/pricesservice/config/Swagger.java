package com.marco.pricesservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This is the Swagger configuration class
 * 
 * @see
 * <ul>
 *  <li><a href="https://www.youtube.com/watch?v=gduKpLW_vdY">How To add Swagger</a></li>
 *  <li><a href="https://www.youtube.com/watch?v=8s9I1G4tXhA">How To configure Swagger</a></li>
 *  <li><a href="https://dzone.com/articles/spring-boot-2-restful-api-documentation-with-swagg">Spring Boot 2 RESTful API Documentation With Swagger 2 Tutorial</a></li>
 *  <li><a href="http://http://192.168.99.102:8761/eureka{:portnumber}/{context path}/swagger-ui.html">Swagger Page for this project (replace the place holders)</a></li>
 * </ul>
 * 
 * @author msolina
 *
 */
@Configuration
@EnableSwagger2
public class Swagger {
    /**
     * Configure swagger
     * @return
     */
    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("com.marco.pricesservice.controllers"))
            .paths(PathSelectors.regex("/.*"))
            .build().apiInfo(apiEndPointsInfo());
    }
    
    
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Marco Prices service POC")
            .description("This is a personal test project to play with Spring Boot and the Microservice architecture")
            .contact(new Contact("Marco Solina", "https://marco.selfip.net", "marcosolina@gmail.com"))
            .version("1.0.0")
            .build();
    }
}
