package com.marco.menuservice.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.marco.menuservice.services.implementations.BusinsessLogicImpl;
import com.marco.menuservice.services.implementations.ErrorServiceImpl;
import com.marco.menuservice.services.implementations.MessageServiceImpl;
import com.marco.menuservice.services.implementations.ModellingServiceImpl;
import com.marco.menuservice.services.implementations.RestClientImpl;
import com.marco.menuservice.services.interfaces.BusinsessLogicInt;
import com.marco.menuservice.services.interfaces.ErrorServiceInt;
import com.marco.menuservice.services.interfaces.MessageServiceInt;
import com.marco.menuservice.services.interfaces.ModellingServiceInt;
import com.marco.menuservice.services.interfaces.RestClientInt;
import com.marco.menuservice.utils.Utils;

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
