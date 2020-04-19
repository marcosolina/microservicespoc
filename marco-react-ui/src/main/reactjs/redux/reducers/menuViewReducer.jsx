import { CHANGE_MENU_VIEW } from '../actionTypes.jsx';

const initialState = {

};

/**
 * Menu view Reducer used with Redux
 * @param {*} state 
 * @param {*} action 
 */
const menuViewReducer = function (state = initialState, action) {
	delete state.action;
	switch (action.type) {
		case CHANGE_MENU_VIEW:
			state = { ...state, ...action.payload };
			state.action = action.type;
			break;
		default:
			break;
	}

	return state;
}

export default menuViewReducer;