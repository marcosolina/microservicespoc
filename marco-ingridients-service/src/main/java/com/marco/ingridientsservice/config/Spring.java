package com.marco.ingridientsservice.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.marco.ingridientsservice.services.implementations.BusinsessLogicImpl;
import com.marco.ingridientsservice.services.implementations.ErrorServiceImpl;
import com.marco.ingridientsservice.services.implementations.MessageServiceImpl;
import com.marco.ingridientsservice.services.implementations.ModellingServiceImpl;
import com.marco.ingridientsservice.services.implementations.RestClientImpl;
import com.marco.ingridientsservice.services.interfaces.BusinsessLogicInt;
import com.marco.ingridientsservice.services.interfaces.ErrorServiceInt;
import com.marco.ingridientsservice.services.interfaces.MessageServiceInt;
import com.marco.ingridientsservice.services.interfaces.ModellingServiceInt;
import com.marco.ingridientsservice.services.interfaces.RestClientInt;
import com.marco.ingridientsservice.utils.Utils;

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
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/messages/errorCodes", "classpath:/messages/messages");
        messageSource.setDefaultLocale(Utils.LOCALE_DEFAULT);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
