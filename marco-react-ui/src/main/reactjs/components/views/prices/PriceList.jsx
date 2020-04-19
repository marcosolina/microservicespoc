import React, { Component } from 'react';

/**
 * This component generates the Price list view
 */
class PriceList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            prices: []
        };
    }

    /**
     * This is a standard React JS function
     */
    componentDidMount() {
        this.retrieveListOfPrices();
    }

    /**
     * It performs the HTTP request to retireve the
     * list of prices
     */
    retrieveListOfPrices() {
        fetch("/reactui/prices").then((resp) => {
            return resp.json();
        }).then((jsonResp) => {
            this.setState({ prices: jsonResp });
        }).catch((error) => {
            alert(error.message);
        });
    }

    /**
     * It updates the state of the specific price
     * @param {*} dishName 
     * @param {*} event 
     */
    onChangeInput(dishName, event) {
        let newValue = event.target.value;

        let prices = this.state.prices;
        for (var i = 0; i < prices.length; i++) {
            let price = prices[i];
            if (price.dishName === dishName) {
                price.price = newValue;
                break;
            }
        };

        this.setState(prices);
    }

    /**
     * It performs the HTTP request to update
     * or delete a price
     * @param {*} dishName 
     * @param {*} httpMethod 
     */
    execFetch(dishName, httpMethod) {

        let apiPrice = this.state.prices.find(price => {
            return price.dishName === dishName;
        });

        fetch("/reactui/prices", {
            method: httpMethod,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(apiPrice)
        }).then((resp) => {
            return resp.status;
        }).then((respMessage) => {
            if(httpMethod == "DELETE"){
                this.retrieveListOfPrices();
            }
            alert("Http Status: " + respMessage);
        }).catch((error) => {
            alert(error.message);
        });
    }

    render() {

        const prices = this.state.prices;

        return (
            <table border="1">
                <tbody>
                    {
                        prices.map(price => {
                            return <tr key={price.dishName}>
                                <td>{price.dishName}</td>
                                <td><input type="text" placeholder="Price" value={price.price} onChange={this.onChangeInput.bind(this, price.dishName)} /></td>
                                <td><button type="button" onClick={this.execFetch.bind(this, price.dishName, "PUT")}>Update</button></td>
                                <td><button type="button" onClick={this.execFetch.bind(this, price.dishName, "DELETE")}>Delete</button></td>
                            </tr>;
                        })
                    }
                </tbody>
            </table>
        )
    };
}

export default PriceList;