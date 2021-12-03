import axios from "axios";
import React, { useState } from "react";
import { useParams, useLocation, useHistory } from "react-router-dom";

import { Form, Button } from "react-bootstrap";

export default function Bid() {
  const user = JSON.parse(localStorage.getItem("loggedUser"))[0];
  const { id, name, price } = useParams(); // the variable "id" is the key parameter in the url stated in the App.js file
  const history = useHistory();

  const [bid, setBid] = useState(0);

  const placeBid = () => {
    const submitBid = {
      userId: user.userId,
      itemId: id,
      bidAmount: parseFloat(bid).toFixed(2),
    };
    axios.post("http://localhost:3001/placeBid", submitBid).then((res) => {
      console.log(res.data);
    });
    backToHome();
  };

  const backToHome = () => {
    history.push("/home");
  };
  return (
    // <div>
    //   <h1>{id}</h1>
    //   <h2>{name}</h2>
    //   <h3>{price}</h3>
    //   <input
    //     type="number"
    //     min={price}
    //     onChange={(e) => setBid(e.target.value)}
    //   />
    //   <button className="btn btn-primary" onCLick={placeBid}>
    //     Place bid
    //   </button>
    // </div>
    <div>
      <Form>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control type="email" placeholder="Enter email" />
          <Form.Text className="text-muted">
            We'll never share your email with anyone else.
          </Form.Text>
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control type="password" placeholder="Password" />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicCheckbox">
          <Form.Check type="checkbox" label="Check me out" />
        </Form.Group>
        <Button variant="primary" type="submit">
          Submit
        </Button>
      </Form>
    </div>
  );
}
