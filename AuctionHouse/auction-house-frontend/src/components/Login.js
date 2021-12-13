import React, { useState } from "react";
import { Link, useHistory } from "react-router-dom";
import axios from "axios";

import "./css/Login.css";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const history = useHistory();

  const login = () => {
    axios
      .post("http://localhost:8080/api/getUser", {
        username: username,
      })
      .then((res) => {
        if (res.data === "") {
          alert("Invalid username");
        } else {
          if (res.data.password === password) {
            localStorage.setItem("loggedUser", JSON.stringify(res.data));
            history.push("/home");
          } else {
            alert("Invalid password");
          }
        }
      });
  };

  return (
    <div>
      <div className="container">
        <div className="column">
          <div className="extra-space"></div>
          <h1>Auction House</h1>
          <br />
          <br />
          <div className="card">
            <div className="card-body">
              <form action="">
                <div className="form-group">
                  <label>Username</label>
                  <input
                    className="form-control"
                    placeholder="Enter username"
                    type="text"
                    onChange={(e) => setUsername(e.target.value)}
                  />
                </div>

                <div className="form-group">
                  <label>Password</label>
                  <input
                    className="form-control"
                    placeholder="Enter password"
                    type="text"
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>

                <div className="card-links">
                  <button
                    type="button"
                    className="btn btn-primary"
                    onClick={login}
                  >
                    Login
                  </button>

                  <div className="signup-link">
                    <Link to="/signup" style={{ color: "#ffffff" }}>
                      Create a new account
                    </Link>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
