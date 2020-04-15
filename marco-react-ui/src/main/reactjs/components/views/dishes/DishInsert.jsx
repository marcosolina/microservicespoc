import React, { Component } from 'react';

class DishsInsert extends Component {
	constructor(props) {
		super(props);
		this.state = {
            name: "",
            calories: ""
		};
    }

    onChangeInput(stateProp, event) {
        let newValue = {};
        newValue[stateProp] = event.target.value;
		this.setState(newValue);
    }
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