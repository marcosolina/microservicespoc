import { createStore } from "redux";
import rootReducer from './reducers/rootReducer.jsx';

/**
 * Creating the Redux store
 */
export default createStore(rootReducer);