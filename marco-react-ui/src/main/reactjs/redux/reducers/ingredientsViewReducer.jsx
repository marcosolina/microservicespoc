import { CHANGE_IGREDIENTS_VIEW } from '../actionTypes.jsx';

const initialState = {

};

/**
 * Ingredients reducer used with Redux
 * @param {*} state 
 * @param {*} action 
 */
const ingredientsViewReducer = function (state = initialState, action) {
	delete state.action;
	switch (action.type) {
		case CHANGE_IGREDIENTS_VIEW:
			state = { ...state, ...action.payload };
			state.action = action.type;
			break;
		default:
			break;
	}

	return state;
}

export default ingredientsViewReducer;