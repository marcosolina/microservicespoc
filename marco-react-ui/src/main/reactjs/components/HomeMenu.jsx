import React, { Component } from 'react';
import { connect } from "react-redux";
import {changeHomeView} from '../redux/actions.jsx';

function mapDispatchToProps(dispatch) {
  return {
    changeHomeView: viewName => dispatch(changeHomeView(viewName))
  };
}


class HomeMenu extends Component {
	constructor(props) {
		super(props);
		this.state = {};
	}

	onClick(viewName){
		this.props.changeHomeView(viewName);
	}

	render(){
		return(
			<div className="d-flex justify-content-center">
				<div className="btn-group" role="group" aria-label="Mia Menu">
					<button type="button" className="btn btn-secondary" onClick={this.onClick.bind(this, "home")}>Home</button>
					<button type="button" className="btn btn-secondary" onClick={this.onClick.bind(this, "dishes")}>Dishes</button>
					<button type="button" className="btn btn-secondary" onClick={this.onClick.bind(this, "prices")}>Prices</button>
					<button type="button" className="btn btn-secondary" onClick={this.onClick.bind(this, "ingredients")}>Ingredients</button>
					<button type="button" className="btn btn-secondary" onClick={()=> window.open("/reactui/logout", "_self")}>Log Out</button>
				</div>
			</div>
		);
	}
}


export default connect(null, mapDispatchToProps)(HomeMenu);