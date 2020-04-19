import {
	CHANGE_HOME_VIEW, 
	CHANGE_DISHES_VIEW, 
	CHANGE_PRICES_VIEW,
	CHANGE_IGREDIENTS_VIEW,
	CHANGE_MENU_VIEW} from './actionTypes.jsx';

/***********************************
 * Actions to dispatch with Redux
 ***********************************/


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

export const changeMenuView = viewName => ({
	type: CHANGE_MENU_VIEW,
	payload:{
		viewName: viewName
	}
});