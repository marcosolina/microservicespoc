import React, { Component } from 'react';
import PropTypes from 'prop-types';

class Button extends Component{
	constructor(props){
		super(props);
	}

	onClick(){
		if(this.props.onClick){
			this.props.onClick(this.props.id);
		}
	}

	render(){
		return(
			<button id={this.props.id} type="button" className={"btn " + (this.props.style || "btn-outline-info")} onClick={this.onClick.bind(this)}>
				{this.props.children}
			</button>
		);
	}
}

Button.propTypes = {
	id: PropTypes.string.isRequired,
	onClick: PropTypes.func
}

export default Button;