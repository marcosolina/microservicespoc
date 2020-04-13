package com.marco.marcoreactui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marco.marcoreactui.services.implementations.DishesBusinsessLogicImpl;
import com.marco.marcoreactui.services.implementations.RestClientImpl;
import com.marco.marcoreactui.services.interfaces.DishesBusinsessLogicInt;
import com.marco.marcoreactui.services.interfaces.RestClientInt;

@Configuration
public class Beans {

	@Bean
	public RestClientInt getRestClientInt() {
		return new RestClientImpl();
	}
	
	@Bean
	public DishesBusinsessLogicInt getDishesBusinsessLogicInt() {
		return new DishesBusinsessLogicImpl();
	}
}