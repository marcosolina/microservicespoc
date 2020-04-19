import React, { Component } from 'react';
import { connect } from "react-redux";
import {changeHomeView} from '../redux/actions.jsx';

/**
 * Standard REdux function to dispatch events
 * @param {*} dispatch 
 */
function mapDispatchToProps(dispatch) {
  return {
    changeHomeView: viewName => dispatch(changeHomeView(viewName))
  };
}

/**
 * This component defines the Menu display in the application
 */
class HomeMenu extends Component {
	constructor(props) {
		super(props);
		this.state = {};
	}

	/**
	 * This manages the click of the buttons
	 * @param {*} viewName 
	 */
	onClick(viewName){
		this.props.changeHomeView(viewName);
	}

	render(){
		return(
			<div className="d-flex justify-content-center">
				<div className="btn-group" role="group" aria-label="Mia Menu">
					<button type="button" className="btn btn-secondary" onClick={this.onClick.bind(this, "home")}>Home</button>
					&nbsp;
					<button type="button" className="btn btn-secondary" onClick={this.onClick.bind(this, "dishes")}>Dishes</button>
					&nbsp;
					<button type="button" className="btn btn-secondary" onClick={this.onClick.bind(this, "prices")}>Prices</button>
					&nbsp;
					<button type="button" className="btn btn-secondary" onClick={this.onClick.bind(this, "ingredients")}>Ingredients</button>
					&nbsp;
					<button type="button" className="btn btn-secondary" onClick={this.onClick.bind(this, "menu")}>Menu</button>
					&nbsp;
					<button type="button" className="btn btn-secondary" onClick={()=> window.open("/reactui/logout", "_self")}>Log Out</button>
				</div>
			</div>
		);
	}
}

/**
 * It connects the component with Redux
 */
export default connect(null, mapDispatchToProps)(HomeMenu);