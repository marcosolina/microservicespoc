import React, { Component } from 'react';

/**
 * This component will create the view used
 * to display the list of ingredients for the 
 * different dishes
 */
class IngredientList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            ingredients: [],
            newRecipe:{
                dishName: "",
                ingredients: [],
                newIngredient: ""
            }
        };
    }

    /**
     * Standard React JS function
     */
    componentDidMount() {
        this.retrieveListOfIngredients();
    }

    /**
     * It will perform the HTTP request to retrieve
     * the list of dishes and their ingredients
     */
    retrieveListOfIngredients() {
        fetch("/reactui/ingredients").then((resp) => {
            return resp.json();
        }).then((jsonResp) => {
            this.setState({ ingredients: jsonResp });
        }).catch((error) => {
            alert(error.message);
        });
    }

    /**
     * Function used to update the state when 
     * the user is providing the name of the
     * new ingredient
     * @param {*} event 
     */
    editNewIngredient(event){
        let newRecipe = this.state.newRecipe;
        newRecipe.newIngredient = event.target.value;
        this.setState({newRecipe: newRecipe});
    }

    /**
     * Function used to update the state when 
     * the user is providing the name of the
     * new dish that he would like to provide
     * the ingredients for
     * @param {*} event 
     */
    editNewDishName(event){
        let newRecipe = this.state.newRecipe;
        newRecipe.dishName = event.target.value;
        this.setState({newRecipe: newRecipe});
    }

    /**
     * It will add the new ingredient to the list
     * of ingredients of the new dish
     */
    addNewIngredient(){
        let newRecipe = this.state.newRecipe;
        newRecipe.ingredients.push(newRecipe.newIngredient);
        newRecipe.newIngredient = "";
        this.setState({newRecipe: newRecipe});
    }

    /**
     * It will clear the status of the new Recipe
     */
    clearNewRecipe(){
        let newRecipe= {
            dishName: "",
            ingredients: [],
            newIngredient: ""
        };
        this.setState({newRecipe: newRecipe});
    }

    /**
     * It performs the HTTP request to insert the new
     * recipe
     */
    insertNewRecipe(){
		fetch("/reactui/ingredients", {
            method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(this.state.newRecipe)
        }).then((resp) => {
			return resp;
		}).then((respMessage) => {
            alert("Http Status: " + respMessage.status);
            if(respMessage.ok){
                this.clearNewRecipe();
                this.retrieveListOfIngredients();
            }
		}).catch((error) => {
			alert(error.message);
		});
    }

    /**
     * It performs the HTTP request to delete the
     * recipe for the specific dish
     * @param {*} dishName 
     */
    deleteRecipe(dishName){
		fetch("/reactui/ingredients/" + dishName, {
            method: 'DELETE',
        }).then((resp) => {
			return resp;
		}).then((respMessage) => {
            alert("Http Status: " + respMessage.status);
            this.retrieveListOfIngredients();
		}).catch((error) => {
			alert(error.message);
		});
    }


    render() {

        const ingredients = this.state.ingredients;
        const newRecipe = this.state.newRecipe;

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
                                                    </li>
                                        })}
                                    </ul>
                                </td>
                                <td>
                                    <button type="button" className="btn btn-secondary" onClick={this.deleteRecipe.bind(this, ingredient.dishName)}>Delete</button>
                                </td>
                            </tr>;
                        })
                    }
                    <tr>
                        <td>
                            <input type="text" placeholder="Dish Name" value={newRecipe.dishName} onChange={this.editNewDishName.bind(this)}/>
                        </td>
                        <td>
                            <ul>
                                {
                                    newRecipe.ingredients.map(ing => {
                                        return <li key={ing}>
                                            {ing}
                                        </li>
                                    })
                                }
                                <li>
                                    <input type="text" placeholder="Ingredient" value={newRecipe.newIngredient} onChange={this.editNewIngredient.bind(this)}/>
                                    <button type="button" className="btn btn-secondary" onClick={this.addNewIngredient.bind(this)}>Add</button>
                                </li>
                            </ul>
                        </td>
                        <td>
                            <button type="button" className="btn btn-secondary" onClick={this.insertNewRecipe.bind(this)}>Save</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        )
    };
}

export default IngredientList;