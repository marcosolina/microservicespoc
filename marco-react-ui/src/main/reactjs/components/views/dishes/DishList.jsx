import React, { Component } from 'react';

class DishsList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            dishes: []
        };
    }

    componentDidMount() {
        this.retrieveListOfDishes();
    }

    retrieveListOfDishes() {
        fetch("/reactui/dishes").then((resp) => {
            return resp.json();
        }).then((jsonResp) => {
            this.setState({ dishes: jsonResp });
        }).catch((error) => {
            alert(error.message);
        });
    }

    onChangeInput(dishName, event) {
        let newValue = event.target.value;

        let dishes = this.state.dishes;
        for (var i = 0; i < dishes.length; i++) {
            let dish = dishes[i];
            if (dish.name === dishName) {
                dish.calories = newValue;
                break;
            }
        };

        this.setState(dishes);
    }


    execFetch(dishName, httpMethod) {

        let apiDish = this.state.dishes.find(dish => {
            return dish.name === dishName;
        });

        fetch("/reactui/dishes", {
            method: httpMethod,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(apiDish)
        }).then((resp) => {
            return resp.status;
        }).then((respMessage) => {
            if(httpMethod == "DELETE"){
                this.retrieveListOfDishes();
            }
            alert("Http Status: " + respMessage);
        }).catch((error) => {
            alert(error.message);
        });
    }

    render() {

        const dishes = this.state.dishes;

        return (
            <table border="1">
                <tbody>
                    {
                        dishes.map(dish => {
                            return <tr key={dish.name}>
                                <td>{dish.name}</td>
                                <td><input type="text" placeholder="Calories" value={dish.calories} onChange={this.onChangeInput.bind(this, dish.name)} /></td>
                                <td><button type="button" onClick={this.execFetch.bind(this, dish.name, "PUT")}>Update</button></td>
                                <td><button type="button" onClick={this.execFetch.bind(this, dish.name, "DELETE")}>Delete</button></td>
                            </tr>;
                        })
                    }
                </tbody>
            </table>
        )
    };
}

export default DishsList;