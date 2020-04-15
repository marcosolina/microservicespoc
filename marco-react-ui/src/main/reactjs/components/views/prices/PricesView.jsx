import React, { Component } from 'react';
import PriceList from './PriceList.jsx';
import PriceInsert from './PriceInsert.jsx';
import { connect } from "react-redux";
import {changePriceView} from '../../../redux/actions.jsx';
import { CHANGE_PRICES_VIEW } from '../../../redux/actionTypes.jsx';

const mapStateToProps = function (state) {
	return state.dishesViewReducer;
}


function mapDispatchToProps(dispatch) {
  return {
    changePriceView: viewName => dispatch(changePriceView(viewName))
  };
}

class PriceView extends Component {
	constructor(props) {
		super(props);
		this.state = {
			viewToDisplay: ""
		};
	}

	static getDerivedStateFromProps(nextProps, prevState) {
		if (nextProps.action) {
			switch (nextProps.action) {
				case CHANGE_PRICES_VIEW:
					return { viewToDisplay: nextProps.viewName };
				default:
					return null;
			}
		} else {
			return null;
		}
	}

	onClick(viewName){
		this.props.changePriceView(viewName);
	}

	render(){

		let body = <p>Choose an option</p>;
		switch(this.state.viewToDisplay){
			case "list":
				body = <PriceList/>
				break;
			case "Insert":
				body = <PriceInsert/>
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
export default connect(mapStateToProps, mapDispatchToProps)(PriceView);