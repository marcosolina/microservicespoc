import {CHANGE_HOME_VIEW, CHANGE_DISHES_VIEW} from './actionTypes.jsx';

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