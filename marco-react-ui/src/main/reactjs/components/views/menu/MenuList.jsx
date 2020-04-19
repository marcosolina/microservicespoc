import React, { Component } from 'react';

class MenuList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            menus: [],
            newMenu:{
                menuName: "",
                dishes: [],
                newDishName: ""
            }
        };
    }

    componentDidMount() {
        this.retrieveListOfMenu();
    }

    retrieveListOfMenu() {
        fetch("/reactui/menu").then((resp) => {
            return resp.json();
        }).then((jsonResp) => {
            this.setState({ menus: jsonResp });
        }).catch((error) => {
            alert(error.message);
        });
    }

    editNewDishName(event){
        let newMenu = this.state.newMenu;
        newMenu.newDishName = event.target.value;
        this.setState({newMenu: newMenu});
    }

    editNewMenuName(event){
        let newMenu = this.state.newMenu;
        newMenu.menuName = event.target.value;
        this.setState({newMenu: newMenu});
    }

    addNewDishName(){
        let newMenu = this.state.newMenu;
        newMenu.dishes.push(newMenu.newDishName);
        newMenu.newDishName = "";
        this.setState({newMenu: newMenu});
    }

    clearNewMenu(){
        let newMenu ={
            menuName: "",
            dishes: [],
            newDishName: ""
        };
        this.setState({newMenu: newMenu});
    }

    insertNewMenu(){
		fetch("/reactui/menu", {
            method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(this.state.newMenu)
        }).then((resp) => {
			return resp;
		}).then((respMessage) => {
            alert("Http Status: " + respMessage.status);
            if(respMessage.ok){
                this.clearNewMenu();
                this.retrieveListOfMenu();
            }
		}).catch((error) => {
			alert(error.message);
		});
    }

    deleteMenu(menuName){
		fetch("/reactui/menu/" + menuName, {
            method: 'DELETE',
        }).then((resp) => {
			return resp;
		}).then((respMessage) => {
            alert("Http Status: " + respMessage.status);
            this.retrieveListOfMenu();
		}).catch((error) => {
			alert(error.message);
		});
    }


    render() {

        const menus = this.state.menus;
        const newMenu = this.state.newMenu;

        return (
            <table border="1">
                <tbody>
                    {
                        menus.map(menu => {
                            return <tr key={menu.menuName}>
                                <td>{menu.menuName}</td>
                                <td>
                                    <ul>
                                        {menu.dishes.map(dish => {
                                            return <li key={dish}>
                                                     {dish}
                                                    </li>
                                        })}
                                    </ul>
                                </td>
                                <td>
                                    <button type="button" className="btn btn-secondary" onClick={this.deleteMenu.bind(this, menu.menuName)}>Delete</button>
                                </td>
                            </tr>;
                        })
                    }
                    <tr>
                        <td>
                            <input type="text" placeholder="Menu Name" value={newMenu.menuName} onChange={this.editNewMenuName.bind(this)}/>
                        </td>
                        <td>
                            <ul>
                                {
                                    newMenu.dishes.map(dish => {
                                        return <li key={dish}>
                                            {dish}
                                        </li>
                                    })
                                }
                                <li>
                                    <input type="text" placeholder="Dish Name" value={newMenu.newDishName} onChange={this.editNewDishName.bind(this)}/>
                                    <button type="button" className="btn btn-secondary" onClick={this.addNewDishName.bind(this)}>Add</button>
                                </li>
                            </ul>
                        </td>
                        <td>
                            <button type="button" className="btn btn-secondary" onClick={this.insertNewMenu.bind(this)}>Save</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        )
    };
}

export default MenuList;