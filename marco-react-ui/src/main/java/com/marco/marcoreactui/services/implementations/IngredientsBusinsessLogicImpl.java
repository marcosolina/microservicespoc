package com.marco.marcoreactui.services.implementations;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.marco.marcoreactui.config.properties.IngredientsServiceProperties;
import com.marco.marcoreactui.dto.ingredients.ApiDishRecipe;
import com.marco.marcoreactui.dto.ingredients.ApiRecipes;
import com.marco.marcoreactui.services.interfaces.IngredientsBusinsessLogicInt;
import com.marco.marcoreactui.services.interfaces.RestClientInt;
import com.marco.marcoreactui.utils.ReactUiConstants;

public class IngredientsBusinsessLogicImpl implements IngredientsBusinsessLogicInt {

	@Autowired
	private RestClientInt wsClient;

	@Autowired
	private IngredientsServiceProperties props;

	@Override
	public List<ApiDishRecipe> getAllRecipes() {
		URL url;
		try {
			url = new URL(props.getProtocol(), props.getHost(), props.getFindAllRecipes());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			// TODO manage the error in a better way
			return null;
		}
		ClientResponse resp = wsClient.performGetRequest(Optional.of(ReactUiConstants.TOKEN_INGREDIENTS_REGISTRATION_ID), url, Optional.empty(), Optional.empty());
		if (resp.statusCode() == HttpStatus.OK) {
			ApiRecipes recipes = wsClient.getBodyFromResponse(resp, ApiRecipes.class);
			return recipes.getRecipes();
		}
		return new ArrayList<>();
	}

	@Override
	public boolean insertRecipe(ApiDishRecipe recipe) {
		URL url;
		try {
			url = new URL(props.getProtocol(), props.getHost(), props.getInsertRecipe());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			// TODO manage the error in a better way
			return false;
		}
		ClientResponse resp = wsClient.performPostRequest(Optional.of(ReactUiConstants.TOKEN_INGREDIENTS_REGISTRATION_ID), url, Optional.empty(), Optional.empty(), Optional.of(recipe));
		return resp != null && resp.statusCode() == HttpStatus.CREATED;
	}

	@Override
	public boolean deleteRecipe(String dishName) {
		URL url;
		try {
			url = new URL(props.getProtocol(), props.getHost(), props.getDeleteRecipe(dishName));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			// TODO manage the error in a better way
			return false;
		}
		ClientResponse resp = wsClient.performDeleteRequest(Optional.of(ReactUiConstants.TOKEN_INGREDIENTS_REGISTRATION_ID), url, Optional.empty(), Optional.empty(), Optional.empty());
		return resp != null && resp.statusCode() == HttpStatus.NO_CONTENT;
	}

}
