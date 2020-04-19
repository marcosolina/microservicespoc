import React, { Component } from 'react';
import DishList from './DishList.jsx';
import DishInsert from './DishInsert.jsx';
import { connect } from "react-redux";
import {changeDishView} from '../../../redux/actions.jsx';
import { CHANGE_DISHES_VIEW } from '../../../redux/actionTypes.jsx';

/**
 * Function to map the Dishes Redux State to properties
 * @param {Redux state} state 
 */
const mapStateToProps = function (state) {
	return state.dishesViewReducer;
}

/**
 * Function to map the Redux functions that I want to dispatch
 * 
 * @param {*} dispatch 
 */
function mapDispatchToProps(dispatch) {
  return {
    changeDishView: viewName => dispatch(changeDishView(viewName))
  };
}

/**
 * This component represent the main Dish view
 */
class DishsView extends Component {
	constructor(props) {
		super(props);
		this.state = {
			viewToDisplay: ""
		};
	}

	/**
	 * Standard React JS function
	 * 
	 * @param {*} nextProps 
	 * @param {*} prevState 
	 */
	static getDerivedStateFromProps(nextProps, prevState) {
		if (nextProps.action) {
			switch (nextProps.action) {
				case CHANGE_DISHES_VIEW:
					return { viewToDisplay: nextProps.viewName };
				default:
					return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Function that will manage the click of the buttons
	 * @param {*} viewName 
	 */
	onClick(viewName){
		this.props.changeDishView(viewName);
	}

	render(){

		let body = <p>Choose an option</p>;
		/*
		 * Selecting the view depending on the user 
		 * selection 
		 */
		switch(this.state.viewToDisplay){
			case "list":
				body = <DishList/>
				break;
			case "Insert":
				body = <DishInsert/>
				break;
		}

		return(
			<span>
				<div className="d-flex justify-content-center">
					<div className="btn-group" role="group" aria-label="Mia Menu">
						<button type="button" className="btn btn-secondary" onClick={this.onClick.bind(this, "list")}>List</button>
						<button type="button" className="btn btn-secondary" onClick={this.onClick.bind(this, "Insert")}>Insert</button>
					</div>
				</div>
				{body}
			</span>
		)
	};
}

/*
* Connecting this component to Redux
*/
export default connect(mapStateToProps, mapDispatchToProps)(DishsView);