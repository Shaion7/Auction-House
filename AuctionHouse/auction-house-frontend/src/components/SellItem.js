import React, { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import axios from "axios";
import MyNavbar from "./MyNavbar";

import Dropdown from "react-dropdown";
import "react-dropdown/style.css";

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
    // console.log("category: " + JSON.stringify(category));
    axios
      .post("http://localhost:8080/api/postItemOnSale/", {
        name: itemName,
        initialPrice: price,
        description: description,
        timeLimit: timeLimit,
        category: category.label,
        condition: condition.label,
        location: location.label,
        userId: user.userId,
      })
      .then((res) => {
        console.log(res);
        history.push("/home");
      });
  };

  const categories = [
    "Sports Equipment",
    "Kitchenware",
    "Furniture",
    "Homeware",
    "Electrical",
    "Technology",
    "Games",
    "Music Instruments",
    "Beach",
    "School",
    "Officeware",
    "Pants",
    "Shoes",
    "Jewelry",
    "Recreation",
  ];
  const conditions = [
    "Unopened",
    "New",
    "No defects",
    "Used but new",
    "Used but small defects",
    "Fully functional",
    "Lightly damaged but functional",
    "Functional but few cosmetic damages",
    "Not used",
    "Needs minor fixing",
    "Original",
    "Old but functional",
    "Old unopened",
    "Slight damages",
    "Old  but no damages",
  ];
  const locations = [
    "San Jose",
    "San Francisco",
    "Sacramento",
    "Oakland",
    "Santa Cruz",
    "Santa Rosa",
    "Fremont",
    "Modesto",
    "Daly City",
    "San Leandro",
    "Berkeley",
    "Fresno",
    "Sannyvale",
    "Milpitas",
    "Union City",
  ];

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
                  <Dropdown
                    options={categories}
                    onChange={setCategory}
                    // value={categories[0]}
                    placeholder="Select a category"
                  />
                </div>

                <div className="form-group">
                  <label>Condition</label>
                  <Dropdown
                    options={conditions}
                    onChange={setCondition}
                    // value={conditions[0]}
                    placeholder="Select a condition"
                  />
                </div>

                <div className="form-group">
                  <label>Location</label>
                  <Dropdown
                    options={locations}
                    onChange={setLocation}
                    // value={locations[0]}
                    placeholder="Select a location"
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
