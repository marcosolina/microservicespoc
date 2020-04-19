import { CHANGE_DISHES_VIEW } from '../actionTypes.jsx';

const initialState = {

};

/**
 * Dishes view reducer used with Redux
 * @param {*} state 
 * @param {*} action 
 */
const dishesViewReducer = function (state = initialState, action) {
	delete state.action;
	switch (action.type) {
		case CHANGE_DISHES_VIEW:
			state = { ...state, ...action.payload };
			state.action = action.type;
			break;
		default:
			break;
	}

	return state;
}

export default dishesViewReducer;