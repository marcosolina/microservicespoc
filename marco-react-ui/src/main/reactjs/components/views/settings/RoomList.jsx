import React, { Component } from 'react';
import Button from '../../basic/button.jsx';


class RoomList extends Component {
	constructor(props) {
		super(props);
		this.state = {
			rooms: {},
			newRoomName: ""
		}
	}

	componentDidMount() {
		this.retrieveListOfRooms();
	}

	onChangeExistingRoom(id, event) {
		let currentRooms = this.state.rooms;
		currentRooms[id].desc = event.target.value;
		this.setState({ rooms: currentRooms });
	}

	onChangeNewRoom(event) {
		this.setState({ newRoomName: event.target.value });
	}

	insertNewRoom(roomId) {
		MarcoUtils.preventClick(true);
		fetch(__URLS.ACTIONS.SAVE_NEW_ROOM, {
			method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({ newRoomName: this.state.newRoomName })
		}).then((resp) => {
			if (resp.ok) {
				return resp.json();
			}
			throw new Error("Network response was not ok");
		}).then((jsonResp) => {
			MarcoUtils.preventClick(false);
			if (jsonResp.status) {
				this.setState({ newRoomName: "" });
				this.retrieveListOfRooms();
			}
		}).catch((error) => {
			MarcoUtils.preventClick(false);
			MarcoUtils.showNotification({ title: "Oops", message: error.message, close: false })
		});
	}

	editRoom(roomId, httpMethod) {
		MarcoUtils.preventClick(true);
		fetch(__URLS.ACTIONS.SAVE_NEW_ROOM, {
			method: httpMethod,
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(this.state.rooms[roomId])
		}).then((resp) => {
			if (resp.ok) {
				return resp.json();
			}
			throw new Error("Network response was not ok");
		}).then((jsonResp) => {
			MarcoUtils.preventClick(false);
			if (jsonResp.status) {
				this.retrieveListOfRooms();
			}
		}).catch((error) => {
			MarcoUtils.preventClick(false);
			MarcoUtils.showNotification({ title: "Oops", message: error.message, close: false })
		});
	}


	retrieveListOfRooms() {
		MarcoUtils.preventClick(true);
		fetch(__URLS.ACTIONS.GET_LIST_ROOMS).then((resp) => {
			if (resp.ok) {
				return resp.json();
			}
			throw new Error("Network response was not ok");
		}).then((jsonResp) => {
			MarcoUtils.preventClick(false);
			if (jsonResp.status && jsonResp.rooms) {
				this.setState({ rooms: jsonResp.rooms });
			}
		}).catch((error) => {
			MarcoUtils.preventClick(false);
			MarcoUtils.showNotification({ title: "Oops", message: error.message, close: false })
		});
	}

	render() {
		const rooms = [];
		for (const room in this.state.rooms) {
			if (this.state.rooms.hasOwnProperty(room)) {
				rooms.push(this.state.rooms[room]);
			}
		}

		return (
			<ul className="list-group">
				{
					rooms.map(room => {
						return <li key={room.id} className="list-group-item">
							<div className="input-group">
								<input
									type="text"
									className="form-control"
									id={room.id}
									placeholder="Room Name"
									value={room.desc}
									onChange={this.onChangeExistingRoom.bind(this, room.id)}
								/>
								<div className="input-group-append">
									<Button id={"updateRoom" + room.id} onClick={this.editRoom.bind(this, room.id, "PUT")}>
										<i className="fa fa-refresh" aria-hidden="true"></i>
									</Button>
									<Button id={"deleteRoom" + room.id} onClick={this.editRoom.bind(this, room.id, "DELETE")} style="btn-outline-danger">
										<i className="fa fa-minus" aria-hidden="true"></i>
									</Button>
								</div>
							</div>
						</li>;
					})
				}
				<li className="list-group-item">
					<div className="input-group">
						<input
							type="text"
							className="form-control"
							id="newRoomName"
							aria-describedby="newRoomlHelp"
							placeholder="Room Name"
							onChange={this.onChangeNewRoom.bind(this)}
							value={this.state.newRoomName} />
						<div className="input-group-append">
							<Button id="insertRoom" onClick={this.insertNewRoom.bind(this)}>
								<i className="fa fa-plus" aria-hidden="true"></i>
							</Button>
						</div>
					</div>
				</li>
			</ul>
		);
	}

}

export default RoomList;