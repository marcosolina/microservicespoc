import React, { Component } from 'react';

/**
 * This component manages the insertion of a new 
 * price
 */
class PriceInsert extends Component {
	constructor(props) {
		super(props);
		this.state = {
            dishName: "",
            price: ""
		};
    }

	/**
	 * This updates the state of the new price
	 * @param {*} stateProp 
	 * @param {*} event 
	 */
    onChangeInput(stateProp, event) {
        let newValue = {};
        newValue[stateProp] = event.target.value;
		this.setState(newValue);
	}
	
	/**
	 * It performs the HTTP request to insert
	 * a new price
	 */
    insertPrice(){
		fetch("/reactui/prices", {
            method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(this.state)
        }).then((resp) => {
			return resp;
		}).then((respMessage) => {
			alert("Http Status: " + respMessage.status);
		}).catch((error) => {
			alert(error.message);
		});
	}

	render(){		
		return(
			<table border="1">
				<tbody>
					<tr>
                        <td><input type="text" placeholder="Dish Name" value={this.state.dishName} onChange={this.onChangeInput.bind(this, "dishName")}/></td>
                        <td><input type="text" placeholder="Price" value={this.state.price} onChange={this.onChangeInput.bind(this, "price")}/></td>
                        <td><button type="button" onClick={this.insertPrice.bind(this)}>Insert</button></td>
                    </tr>
				</tbody>
			</table>
		)
	};

}

export default PriceInsert;