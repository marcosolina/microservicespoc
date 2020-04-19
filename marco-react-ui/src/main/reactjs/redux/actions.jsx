import {
	CHANGE_HOME_VIEW, 
	CHANGE_DISHES_VIEW, 
	CHANGE_PRICES_VIEW,
	CHANGE_IGREDIENTS_VIEW} from './actionTypes.jsx';

export const changeHomeView = viewName => ({
	type: CHANGE_HOME_VIEW,
	payload:{
		viewName: viewName
	}
});

export const changeDishView = viewName => ({
	type: CHANGE_DISHES_VIEW,
	payload:{
		viewName: viewName
	}
});

export const changePriceView = viewName => ({
	type: CHANGE_PRICES_VIEW,
	payload:{
		viewName: viewName
	}
});

export const changeIngredientView = viewName => ({
	type: CHANGE_IGREDIENTS_VIEW,
	payload:{
		viewName: viewName
	}
});