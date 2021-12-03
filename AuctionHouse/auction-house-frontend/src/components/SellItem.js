import React, { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import axios from "axios";
import MyNavbar from "./MyNavbar";

export default function SellItem() {
  const user = JSON.parse(localStorage.getItem("loggedUser"));

  const [itemName, setItemName] = useState("");
  const [category, setCategory] = useState("");
  const [description, setDescription] = useState("");
  const [condition, setCondition] = useState("");
  const [price, setPrice] = useState(0);
  const [timeLimit, setTimeLimit] = useState(0);
  const [location, setLocation] = useState("");

  const history = useHistory();

  const handleBackButtonClick = () => {
    history.push("/home");
  };

  const handleSubmitButtonClick = () => {
    submitItem();
  };

  const submitItem = () => {
    //console.log("userId: " + user.userId);
    axios
      .post("http://localhost:8080/api/postItemOnSale/", {
        name: itemName,
        initialPrice: price,
        description: description,
        timeLimit: timeLimit,
        category: category,
        condition: condition,
        location: location,
        userId: user.userId,
      })
      .then((res) => {
        console.log(res);
        history.push("/home");
      });
  };

  // const getItemAndAddToSells = () => {
  //   const item = {
  //     itemName: itemName,
  //     category: category,
  //     description: description,
  //     condition: condition,
  //     bid: bid,
  //   };
  //   axios.post("http://localhost:3001/getSubmittedItem", item).then((res) => {
  //     console.log(res.data);
  //     const sells = {
  //       userId: user.userId,
  //       itemId: res.data[0].itemId,
  //     };
  //     axios.post("http://localhost:3001/addToSells", sells).then((res) => {
  //       console.log(res);
  //     });
  //   });
  // };

  return (
    <div>
      <MyNavbar />
      <div className="container">
        <div className="column">
          <h1>What do you want to sell?</h1>
          <div className="card">
            <div className="card-body">
              <form action="">
                <div className="form-group">
                  <label>Item Name</label>
                  <input
                    className="form-control"
                    placeholder=""
                    type="text"
                    onChange={(e) => setItemName(e.target.value)}
                  />
                </div>

                <div className="form-group">
                  <label>Initial Price</label>
                  <input
                    className="form-control"
                    placeholder=""
                    type="text"
                    onChange={(e) => setPrice(e.target.value)}
                  />
                </div>

                <div className="form-group">
                  <label>Description</label>
                  <input
                    className="form-control"
                    placeholder=""
                    type="text"
                    onChange={(e) => setDescription(e.target.value)}
                  />
                </div>

                <div className="form-group">
                  <label>Time Limit</label>
                  <input
                    className="form-control"
                    placeholder=""
                    type="text"
                    onChange={(e) => setTimeLimit(e.target.value)}
                  />
                </div>

                <div className="form-group">
                  <label>Category</label>
                  <input
                    className="form-control"
                    placeholder=""
                    type="text"
                    onChange={(e) => setCategory(e.target.value)}
                  />
                </div>

                <div className="form-group">
                  <label>Condition</label>
                  <input
                    className="form-control"
                    placeholder=""
                    type="text"
                    onChange={(e) => setCondition(e.target.value)}
                  />
                </div>

                <div className="form-group">
                  <label>Location</label>
                  <input
                    className="form-control"
                    placeholder=""
                    type="text"
                    onChange={(e) => setLocation(e.target.value)}
                  />
                </div>

                <div className="card-links">
                  <button
                    type="button"
                    className="btn btn-primary"
                    onClick={handleSubmitButtonClick}
                  >
                    Submit
                  </button>
                  <button
                    type="button"
                    className="btn btn-danger"
                    onClick={handleBackButtonClick}
                  >
                    Back
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
