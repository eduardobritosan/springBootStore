import React, {Component} from "react";
import axios from "axios";

class Login extends Component {
  constructor() {
    super();

    this.state = {
      username: "",
      password: ""
    };
    this.handleFormSubmit = this.handleFormSubmit.bind(this);
  }

  handleFormSubmit = event => {
    event.preventDefault();

    const endpoint = "http://localhost:8082/authenticate";

    const username = this.state.username;
    const password = this.state.password;

    const user_object = {
      username: username,
      password: password
    };

    axios.post(endpoint, user_object).then(res => {
      localStorage.setItem("Authorization", res.data.jwtToken);
      return this.handleProductList();
    });
  };

  handleProductList() {
    axios.get("http://localhost:8082/products").then(res => {
      if (res.data === "success") {
        this.props.history.push("/products");
      } else {
        alert("Authentication failure");
      }
    });
  }

  render() {
    return (
      <div>
        <div className="wrapper">
          <form className="form-signin" onSubmit={this.handleFormSubmit}>
            <h2 className="form-signin-heading">Please login</h2>
            <div className="form-group">
              <input type="text"
                className="form-control"
                placeholder="User name"
              />
            </div>
            <div className="form-group">
              <input type="password"
                className="form-control"
                placeholder="password"
              />
            </div>
            <button className="btn btn-lg btn-primary btn-block" type="submit">
              Login
            </button>
          </form>
        </div>
      </div>
    );
  }
}
export default Login;