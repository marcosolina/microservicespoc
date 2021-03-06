import { CHANGE_HOME_VIEW } from '../actionTypes.jsx';

const initialState = {

};

/**
 * Home page menu Reducer used with Redux
 * @param {*} state 
 * @param {*} action 
 */
const homePageReducer = function (state = initialState, action) {
	delete state.action;
	switch (action.type) {
		case CHANGE_HOME_VIEW:
			state = { ...state, ...action.payload };
			state.action = action.type;
			break;
		default:
			break;
	}

	return state;
}

export default homePageReducer;