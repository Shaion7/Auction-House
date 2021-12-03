import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import axios from "axios";

import "./css/Login.css";

export default function Signup() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const history = useHistory();

  const register = () => {
    if (username === "" || password === "") {
      alert("Username and password are required");
    } else {
      axios
        .post("http://localhost:8080/api/postUser", {
          username: username,
          password: password,
          firstName: firstName,
          lastName: lastName,
        })
        .then((res) => {
          console.log(res);
        });
      goToLogin();
    }
  };

  const goToLogin = () => {
    history.push("/");
  };

  const handleRegisterButtonClick = () => {
    register();
  };

  return (
    <div>
      <div className="container">
        <div className="column">
          <div className="extra-space"></div>
          <h1>Register</h1>
          <br />
          <br />
          <div className="card">
            <div className="card-body">
              <form action="">
                <div className="form-group">
                  <label>Username*</label>
                  <input
                    className="form-control"
                    placeholder="Enter username"
                    type="text"
                    onChange={(e) => setUsername(e.target.value)}
                  />
                </div>

                <div className="form-group">
                  <label>Password*</label>
                  <input
                    className="form-control"
                    placeholder="Enter password"
                    type="text"
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>

                <div className="form-group">
                  <label>First Name</label>
                  <input
                    className="form-control"
                    placeholder="Enter first name"
                    type="text"
                    onChange={(e) => setFirstName(e.target.value)}
                  />
                </div>

                <div className="form-group">
                  <label>Last Name</label>
                  <input
                    className="form-control"
                    placeholder="Enter last name"
                    type="text"
                    onChange={(e) => setLastName(e.target.value)}
                  />
                </div>

                <div className="card-links">
                  <button
                    type="button"
                    className="btn btn-primary"
                    onClick={handleRegisterButtonClick}
                  >
                    Register
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
