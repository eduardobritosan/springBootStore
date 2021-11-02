import React, {useRef, useState} from "react";
import axios from "axios";
import Form from 'react-validation/build/form';
import Input from 'react-validation/build/input';

const Login = (props) => {

  const form = useRef();

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const onChangeUsername = (e) => {
    const username = e.target.value;
    setUsername(username);
  }

  const onChangePassword = (e) => {
    const password = e.target.value;
    setPassword(password);
  }

  const handleFormSubmit = event => {
    event.preventDefault();

    const endpoint = "http://localhost:8082/authenticate";

    const user_object = {
      username: username,
      password: password
    };

    axios.post(endpoint, user_object).then(res => {
      localStorage.setItem("Authorization", res.data.jwtToken);
      return handleProductList();
    });
  };

  const handleProductList = () => {
    axios.get("http://localhost:8082/products").then(res => {
      if (res.status === 200) {
        props.history.push("/products");
      }
    },
      (error) => {
        const resMessage =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
      });
  }


  return (
    <div>
      <div className="wrapper">
        <Form onSubmit={handleFormSubmit} ref={form}>
          <h2 className="form-signin-heading">Please login</h2>
          <div className="form-group">
            <label htmlFor="username">Username</label>
            <Input type="text"
              className="form-control"
              placeholder="Username"
              name="username"
              value={username}
              onChange={onChangeUsername}
            />
          </div>
          <div className="form-group">
            <Input type="password"
              className="form-control"
              placeholder="Password"
              name="password"
              value={password}
              onChange={onChangePassword}
            />
          </div>
          <button className="btn btn-lg btn-primary btn-block" type="submit">
            Login
          </button>
        </Form>
      </div>
    </div>
  );
}

export default Login;