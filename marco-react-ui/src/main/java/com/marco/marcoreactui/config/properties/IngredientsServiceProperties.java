package com.marco.marcoreactui.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Standard Spring properties class
 * 
 * @author msolina
 *
 */
@Configuration
@ConfigurationProperties("rest.request.service.ingredients")
public class IngredientsServiceProperties {
	private String host;
	private String protocol;
	private String findRecipeByDishName;
	private String findAllRecipes;
	private String deleteRecipe;
	private String insertRecipe;

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

	public String getFindRecipeByDishName(String dishName) {
		return findRecipeByDishName + "/" + dishName;
	}

	public void setFindRecipeByDishName(String findRecipeByDishName) {
		this.findRecipeByDishName = findRecipeByDishName;
	}

	public String getFindAllRecipes() {
		return findAllRecipes;
	}

	public void setFindAllRecipes(String findAllRecipes) {
		this.findAllRecipes = findAllRecipes;
	}

	public String getDeleteRecipe(String dishName) {
		return deleteRecipe + "/" + dishName;
	}

	public void setDeleteRecipe(String deleteRecipe) {
		this.deleteRecipe = deleteRecipe;
	}

	public String getInsertRecipe() {
		return insertRecipe;
	}

	public void setInsertRecipe(String insertRecipe) {
		this.insertRecipe = insertRecipe;
	}

}
