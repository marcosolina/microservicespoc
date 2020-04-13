import React, { Component } from 'react';
import Accordion from '../../basic/Accordion.jsx';
import AccordionRow from '../../basic/AccordionRow.jsx';
import Button from '../../basic/button.jsx';

import RoomsList from './RoomList.jsx';

class SettingsPage extends Component {
	constructor(props){
		super(props);
		this.state = {
			rows: this.createArraySettings(),
			btnClicked: null
		}
	}

	createArraySettings(){
		let accordionRows = [
			<AccordionRow key="1" id="rooms"title="Rooms" accordionId="accordionSettings">
				<div className="d-flex justify-content-between">
					<span>This section you will be able edit the list of rooms</span>
					<Button id="btnGoToRoomsList" style="btn-outline-info btn-sm" onClick={this.openView.bind(this)} >
						<i className="fa fa-arrow-circle-o-right" aria-hidden="true"></i>
					</Button>
				</div>
			</AccordionRow>
		];

		return accordionRows;
	}

	openView(btnId){
		this.setState({btnClicked: btnId});
	}

	render(){
		
		let display = null;
		switch(this.state.btnClicked){
			case "btnGoToRoomsList":
				display = <RoomsList />;
				break;
			default:
				display = <Accordion id="accordionSettings">
							{this.state.rows}
						</Accordion>;
			break;
		}


		return(
			display
		);
	}

}

export default SettingsPage;