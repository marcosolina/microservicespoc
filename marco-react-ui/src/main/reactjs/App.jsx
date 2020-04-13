import React, {Component} from 'react';
import ReactDOM from 'react-dom';

import { Provider, connect } from "react-redux";

import store from './redux/store.jsx';
import { CHANGE_HOME_VIEW } from './redux/actionTypes.jsx';
import HomeMenu from './components/HomeMenu.jsx';
import DishesView from './components/views/dishes/DishesView.jsx';

const mapStateToProps = function (state) {
	return state.homePageReducer;
}

class App extends Component{
	constructor(props) {
		super(props);
		this.state = {
			viewToDisplay: "home"
		}
	}
	
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
		switch(this.state.viewToDisplay){
			case "home":
				body = <p>Choose a view</p>
				break;
			case "dishes":
				body = <DishesView/>
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

const ConnectedApp = connect(mapStateToProps)(App);

ReactDOM.render(
	<Provider store={store}>
		<ConnectedApp />
	</Provider>
	, document.getElementById("reactapp"));
