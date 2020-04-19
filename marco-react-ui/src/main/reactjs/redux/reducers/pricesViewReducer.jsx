import { CHANGE_PRICES_VIEW } from '../actionTypes.jsx';

const initialState = {

};

/**
 * Prices view Reducer used with Redux
 * @param {*} state 
 * @param {*} action 
 */
const pricesViewReducer = function (state = initialState, action) {
	delete state.action;
	switch (action.type) {
		case CHANGE_PRICES_VIEW:
			state = { ...state, ...action.payload };
			state.action = action.type;
			break;
		default:
			break;
	}

	return state;
}

export default pricesViewReducer;