package com.marco.pricesservice.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import com.marco.pricesservice.servicies.implementations.BusinsessLogicImpl;
import com.marco.pricesservice.servicies.implementations.ErrorServiceImpl;
import com.marco.pricesservice.servicies.implementations.MessageServiceImpl;
import com.marco.pricesservice.servicies.implementations.ModellingServiceImpl;
import com.marco.pricesservice.servicies.implementations.RestClientImpl;
import com.marco.pricesservice.servicies.interfaces.BusinsessLogicInt;
import com.marco.pricesservice.servicies.interfaces.ErrorServiceInt;
import com.marco.pricesservice.servicies.interfaces.MessageServiceInt;
import com.marco.pricesservice.servicies.interfaces.ModellingServiceInt;
import com.marco.pricesservice.servicies.interfaces.RestClientInt;
import com.marco.pricesservice.utils.Utils;

/**
 * Standard Spring configuration
 * 
 * @author msolina
 *
 */
@Configuration
public class Spring {
	
    @Bean
    public RestClientInt getRestClientInt() {
        return new RestClientImpl();
    }
    
    @Bean
    public BusinsessLogicInt getBusinsessLogicInt() {
        return new BusinsessLogicImpl();
    }
    
    @Bean
    public ErrorServiceInt getErrorServiceInt() {
        return new ErrorServiceImpl();
    }
    
    @Bean
    public ModellingServiceInt getModellingServiceInt() {
        return new ModellingServiceImpl();
    }
    
    @Bean
    public MessageServiceInt getMessageServiceInt() {
        return new MessageServiceImpl();
    }
    
    @Bean
	public JwtAuthenticationConverter getJwtAuthenticationConverter() {
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new CustomRealmRoleConverter()); // delegate to custom converter
		return jwtAuthenticationConverter;
	}

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/messages/errorCodes", "classpath:/messages/messages");
        messageSource.setDefaultLocale(Utils.LOCALE_DEFAULT);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
