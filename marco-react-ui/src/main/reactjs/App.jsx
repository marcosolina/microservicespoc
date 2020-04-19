import React, {Component} from 'react';
import ReactDOM from 'react-dom';

import { Provider, connect } from "react-redux";

import store from './redux/store.jsx';
import { CHANGE_HOME_VIEW } from './redux/actionTypes.jsx';
import HomeMenu from './components/HomeMenu.jsx';
import DishesView from './components/views/dishes/DishesView.jsx';
import PricesView from './components/views/prices/PricesView.jsx';
import IngredientsView from './components/views/ingredients/IngredientsView.jsx';
import MenuView from './components/views/menu/MenuView.jsx';

/**
 * Standard Redux function to manage the store
 * @param {*} state 
 */
const mapStateToProps = function (state) {
	return state.homePageReducer;
}

/**
 * Main class of this React JS application
 */
class App extends Component{
	constructor(props) {
		super(props);
		this.state = {
			viewToDisplay: "home"
		}
	}
	
	/**
	 * Standard React JS function
	 * @param {*} nextProps 
	 * @param {*} prevState 
	 */
	static getDerivedStateFromProps(nextProps, prevState) {
		if (nextProps.action) {
			switch (nextProps.action) {
				case CHANGE_HOME_VIEW:
					return { viewToDisplay: nextProps.viewName };
				default:
					return null;
			}
		} else {
			return null;
		}
	}
	
	render(){

		let body = null;
		/*
		 * Choosing the appropriate view 
		 * depending on the user selection
		 */
		switch(this.state.viewToDisplay){
			case "home":
				body = <p>Choose a view</p>
				break;
			case "dishes":
				body = <DishesView/>
				break;
			case "prices":
				body = <PricesView/>
				break
			case "ingredients":
				body = <IngredientsView/>
				break;
			case "menu":
				body = <MenuView/>
				break;
		}

		return(
				<div>
					<HomeMenu/>
					{body}
				</div>
		);
	}
}

/**
 * Connection this component to Redux
 */
const ConnectedApp = connect(mapStateToProps)(App);

/**
 * Attaching this component to the DOM
 */
ReactDOM.render(
	<Provider store={store}>
		<ConnectedApp />
	</Provider>
	, document.getElementById("reactapp"));
