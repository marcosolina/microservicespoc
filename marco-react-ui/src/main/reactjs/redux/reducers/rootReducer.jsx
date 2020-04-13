import { combineReducers } from "redux";
import homePageReducer from './homePage.jsx';
import dishesViewReducer from './dishesViewReducer.jsx';

export default combineReducers({ 
    homePageReducer,
    dishesViewReducer
});