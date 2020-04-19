import { combineReducers } from "redux";
import homePageReducer from './homePage.jsx';
import dishesViewReducer from './dishesViewReducer.jsx';
import pricesViewReducer from './pricesViewReducer.jsx';
import ingredientsViewReducer from './ingredientsViewReducer.jsx';
import menuViewReducer from './menuViewReducer.jsx';

export default combineReducers({ 
    homePageReducer,
    dishesViewReducer,
    pricesViewReducer,
    ingredientsViewReducer,
    menuViewReducer
});