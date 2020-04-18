import React, { Component } from 'react';
import IngredientList from './IngredientList.jsx';
import { connect } from "react-redux";
import {changeIngredientView} from '../../../redux/actions.jsx';
import { CHANGE_IGREDIENTS_VIEW } from '../../../redux/actionTypes.jsx';

const mapStateToProps = function (state) {
	return state.ingredientsViewReducer;
}


function mapDispatchToProps(dispatch) {
  return {
    changeIngredientView: viewName => dispatch(changeIngredientView(viewName))
  };
}

class IngredientView extends Component {
	constructor(props) {
		super(props);
		this.state = {
			viewToDisplay: ""
		};
	}

	static getDerivedStateFromProps(nextProps, prevState) {
		if (nextProps.action) {
			switch (nextProps.action) {
				case CHANGE_IGREDIENTS_VIEW:
					return { viewToDisplay: nextProps.viewName };
				default:
					return null;
			}
		} else {
			return null;
		}
	}

	onClick(viewName){
		this.props.changeIngredientView(viewName);
	}

	render(){

		let body = <p>Choose an option</p>;
		switch(this.state.viewToDisplay){
			case "list":
				body = <IngredientList/>
				break;
		}

		return(
			<span>
				<div className="d-flex justify-content-center">
					<div className="btn-group" role="group" aria-label="Mia Menu">
						<button type="button" className="btn btn-secondary" onClick={this.onClick.bind(this, "list")}>List</button>
					</div>
				</div>
				{body}
			</span>
		)
	};
}
export default connect(mapStateToProps, mapDispatchToProps)(IngredientView);