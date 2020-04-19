import React, { Component } from 'react';

/**
 * Component used to provide the "Insert" Dish view
 */
class DishsInsert extends Component {
	constructor(props) {
		super(props);
		this.state = {
            name: "",
            calories: ""
		};
    }

	/**
	 * Function called when an input is changed. 
	 * It will update the component state
	 * @param {*} stateProp 
	 * @param {*} event 
	 */
    onChangeInput(stateProp, event) {
        let newValue = {};
        newValue[stateProp] = event.target.value;
		this.setState(newValue);
	}
	
	/**
	 * It will perform the HTTP request to insert a new dish
	 */
    insertDish(){
		fetch("/reactui/dishes", {
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
                        <td><input type="text" placeholder="Dish Name" value={this.state.name} onChange={this.onChangeInput.bind(this, "name")}/></td>
                        <td><input type="text" placeholder="Calories" value={this.state.calories} onChange={this.onChangeInput.bind(this, "calories")}/></td>
                        <td><button type="button" onClick={this.insertDish.bind(this)}>Insert</button></td>
                    </tr>
				</tbody>
			</table>
		)
	};

}

export default DishsInsert;