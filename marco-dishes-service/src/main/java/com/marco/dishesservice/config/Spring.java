package com.marco.dishesservice.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import com.marco.dishesservice.services.implementations.BusinsessLogicImpl;
import com.marco.dishesservice.services.implementations.ErrorServiceImpl;
import com.marco.dishesservice.services.implementations.MessageServiceImpl;
import com.marco.dishesservice.services.implementations.ModellingServiceImpl;
import com.marco.dishesservice.services.interfaces.BusinsessLogicInt;
import com.marco.dishesservice.services.interfaces.ErrorServiceInt;
import com.marco.dishesservice.services.interfaces.MessageServiceInt;
import com.marco.dishesservice.services.interfaces.ModellingServiceInt;
import com.marco.dishesservice.utils.Utils;

/**
 * Standard Spring boot configuration class
 * 
 * @author msolina
 *
 */
@Configuration
public class Spring {

    @Bean
    public BusinsessLogicInt getBusinsessLogicInt() {
        return new BusinsessLogicImpl();
    }

    @Bean
    public ErrorServiceInt getDishErrorServiceInt() {
        return new ErrorServiceImpl();
    }

    @Bean
    public MessageServiceInt getMessageServiceInt() {
        return new MessageServiceImpl();
    }

    @Bean
    public ModellingServiceInt getModellingServiceInterface() {
        return new ModellingServiceImpl();
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
        return messageSource;
    }
}
