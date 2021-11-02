import React, {Component} from "react";
import "./App.css";
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import interceptors from "../src/Interceptors";
import Login from "./components/Login";

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <Router>
            <Route exact path="/" component={Login} />
          </Router>
        </header>
      </div>
    );
  }
}

export default App;