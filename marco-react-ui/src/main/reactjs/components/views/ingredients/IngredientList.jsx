import React, { Component } from 'react';

class IngredientList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            ingredients: []
        };
    }

    componentDidMount() {
        this.retrieveListOfIngredients();
    }

    retrieveListOfIngredients() {
        fetch("/reactui/ingredients").then((resp) => {
            return resp.json();
        }).then((jsonResp) => {
            this.setState({ ingredients: jsonResp });
        }).catch((error) => {
            alert(error.message);
        });
    }

    deleteIngredient(dishName, ingredient){
		
    }
    
    addToExisting(dishName, event){
        let newValue = event.target.value;

        let ingredients = this.state.ingredients;
        for (var i = 0; i < ingredients.length; i++) {
            let ing = ingredients[i];
            if (ing.dishName === dishName) {
                ing.newValue = newValue;
                break;
            }
        };

        this.setState(ingredients);
    }

    insertIngredient(dishName, ingredient){
		fetch("/reactui/ingredients", {
            method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
                dishName: dishName,
                ingredientName: ingredient
            })
        }).then((resp) => {
			return resp;
		}).then((respMessage) => {
			alert("Http Status: " + respMessage.status);
		}).catch((error) => {
			alert(error.message);
		});
    }


    render() {

        const ingredients = this.state.ingredients;

        return (
            <table border="1">
                <tbody>
                    {
                        ingredients.map(ingredient => {
                            return <tr key={ingredient.dishName}>
                                <td>{ingredient.dishName}</td>
                                <td>
                                    <ul>
                                        {ingredient.ingredients.map(ing => {
                                            return <li key={ing}>
                                                    {ing}
                                                    <button type="button" className="btn btn-secondary" onClick={this.deleteIngredient.bind(this, ingredient.dishName, ing)}>Delete</button>
                                                    </li>
                                        })}
                                        <li>
                                            <input type="text" placeholder="Ingredient" value={ingredient.newValue || ''} onChange={this.addToExisting.bind(this, ingredient.dishName)}/>
                                            <button type="button" className="btn btn-secondary" onClick={this.insertIngredient.bind(this, ingredient.dishName, ingredient.newValue)}>Add</button>
                                        </li>
                                    </ul>
                                </td>
                            </tr>;
                        })
                    }
                </tbody>
            </table>
        )
    };
}

export default IngredientList;