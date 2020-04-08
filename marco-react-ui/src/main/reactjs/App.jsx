import React, {Component} from 'react';
import ReactDOM from 'react-dom';

class App extends Component{
	render(){
		return(
				<div>
					<h1>Hello I am a react component running in a spring boot application</h1>
				</div>
		);
	}
}

export default App;

ReactDOM.render(<App />, document.getElementById("reactapp"));
