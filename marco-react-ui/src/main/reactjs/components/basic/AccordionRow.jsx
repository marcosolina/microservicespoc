import React, { Component } from 'react';
import PropTypes from 'prop-types';

class AccordionRow extends Component{
	constructor(props){
		super(props);
	}

	render(){

		return(
			<div className="card">
				<div className="card-header" id={"heading" + this.props.id}>
					<h5 className="mb-0">
						<button className="btn btn-link collapsed" type="button" data-toggle="collapse" data-target={"#bodyWrapper" + this.props.id} aria-expanded="false" aria-controls={"bodyWrapper" + this.props.id}>
							{this.props.title}
						</button>
					</h5>
				</div>

				<div id={"bodyWrapper" + this.props.id} className="collapse" aria-labelledby={"heading" + this.props.id} data-parent={"#" + this.props.accordionId}>
					<div className="card-body">
						{this.props.children} 
					</div>
				</div>
			</div>
		);
	}
}

AccordionRow.propTypes = {
	id: PropTypes.string.isRequired,
	accordionId: PropTypes.string.isRequired
}

export default AccordionRow;