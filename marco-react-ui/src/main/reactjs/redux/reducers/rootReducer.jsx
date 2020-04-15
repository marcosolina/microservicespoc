import { combineReducers } from "redux";
import homePageReducer from './homePage.jsx';
import dishesViewReducer from './dishesViewReducer.jsx';
import pricesViewReducer from './pricesViewReducer.jsx';

export default combineReducers({ 
    homePageReducer,
    dishesViewReducer,
    pricesViewReducer
});