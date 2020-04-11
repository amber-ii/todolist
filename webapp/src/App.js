import React from 'react';
import logo from './logo.svg';
import './App.css';
class App extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      todos: []
    }
  }
  componentDidMount() {
    this.getAllTodo()
  }

  async getAllTodo() {
    try {
      const res = await fetch('/todos')
      const json = await res.json()
      console.log(json)
      this.setState(
        {
          todos: json
        }
      )
    } catch (err) {
      console.log(err)
    }
  }
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
        </p>
    <p>{JSON.stringify(this.state.todos)}</p>
    
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
        </a>
        </header>
      </div>
    );
  }
}
export default App;
