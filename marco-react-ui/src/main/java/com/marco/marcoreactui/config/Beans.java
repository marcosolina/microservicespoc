package com.marco.marcoreactui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marco.marcoreactui.services.implementations.DishesBusinsessLogicImpl;
import com.marco.marcoreactui.services.implementations.IngredientsBusinsessLogicImpl;
import com.marco.marcoreactui.services.implementations.MenusBusinsessLogicImpl;
import com.marco.marcoreactui.services.implementations.PricesBusinsessLogicImpl;
import com.marco.marcoreactui.services.implementations.RestClientImpl;
import com.marco.marcoreactui.services.interfaces.DishesBusinsessLogicInt;
import com.marco.marcoreactui.services.interfaces.IngredientsBusinsessLogicInt;
import com.marco.marcoreactui.services.interfaces.MenusBusinsessLogicInt;
import com.marco.marcoreactui.services.interfaces.PricesBusinsessLogicInt;
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

	@Bean
	public PricesBusinsessLogicInt getPricesBusinsessLogicInt() {
		return new PricesBusinsessLogicImpl();
	}

	@Bean
	public IngredientsBusinsessLogicInt getIngredientsBusinsessLogicInt() {
		return new IngredientsBusinsessLogicImpl();
	}

	@Bean
	public MenusBusinsessLogicInt getMenusBusinsessLogicInt() {
		return new MenusBusinsessLogicImpl();
	}
}
