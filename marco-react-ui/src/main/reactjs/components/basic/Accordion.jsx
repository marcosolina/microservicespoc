import React, { Component } from 'react';
import PropTypes from 'prop-types';

class Accordion extends Component {
	constructor(props){
		super(props);
	}

	render(){
		return(
			<div className="accordion" id={this.props.id}>
				{this.props.children}
			</div>
		);
	}
}

Accordion.propTypes = {
	id: PropTypes.string.isRequired,
}

export default Accordion;