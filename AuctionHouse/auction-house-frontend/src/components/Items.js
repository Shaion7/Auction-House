import axios from "axios";
import React, { useState, useEffect } from "react";
import MyNavbar from "./MyNavbar";

import Dropdown from "react-dropdown";
import { Container, Row, Col, Table, Modal, Button } from "react-bootstrap";
import { useHistory } from "react-router-dom";

import "./css/Items.css";

// const [modalShow, setModalShow] = useState(false);

export default function Items() {
  const user = JSON.parse(localStorage.getItem("loggedUser"));
  const history = useHistory();

  const [items, setItems] = useState([]);

  const [modalShow, setModalShow] = useState(false);
  const [data, setData] = useState({});

  const [category, setCategory] = useState("");
  const [condition, setCondition] = useState("");
  const [location, setLocation] = useState("");

  const categories = [
    "Select a category",
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
    "Select a condition",
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
    "Select a location",
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

  useEffect(() => {
    groupItems();
    console.log("useEffect running");
  }, [modalShow]);

  // const getAllItems = () => {
  //   axios
  //     .post("http://localhost:8080/api/getAllItemOnSale", {
  //       username: user.username,
  //     })
  //     .then((res) => {
  //       console.log(res);
  //       setItems(res.data);
  //     });
  // };

  const groupItems = () => {
    let myCategory = category.label;
    let myCondition = condition.label;
    let myLocation = location.label;

    if (myCategory === undefined || myCategory === "Select a category") {
      myCategory = "";
    }
    if (myCondition === undefined || myCondition === "Select a condition") {
      myCondition = "";
    }
    if (myLocation === undefined || myLocation === "Select a location") {
      myLocation = "";
    }
    axios
      .post(
        "http://localhost:8080/api/browseItemsByFilter",
        { username: user.username },
        {
          headers: {
            category: myCategory,
            condition: myCondition,
            location: myLocation,
          },
        }
      )
      .then((res) => {
        console.log(res);
        setItems(res.data);
      });
  };

  const formatTimestamp = (ts) => {
    console.log(ts);

    /* Split Timestamp into Date and Time */
    const timestamp = ts.split("T");
    const date = timestamp[0];
    const time = timestamp[1];
    console.log("timestamp", timestamp);

    /* Year, Month, Day */
    const year = date.split("-")[0];
    const month = date.split("-")[1];
    const day = date.split("-")[2];

    /* Hours, Mins, Secs */
    const hours = time.split(":")[0];
    const mins = time.split(":")[1];
    const secs = time.split(":")[2].split(".")[0];

    /* Return Formatted Date */
    return (
      month + "/" + day + "/" + year + " " + hours + ":" + mins + ":" + secs
    );
  };

  return (
    <div>
      <MyNavbar />
      <Container>
        <h2>Browse All Items On Sale</h2>
        <div className="instruction">Click a row to bid on an item</div>
        <Row>
          <Col>
            <div className="form-group">
              <label>Category</label>
              <Dropdown
                options={categories}
                onChange={setCategory}
                // value={categories[0]}
                placeholder="Select a category"
              />
            </div>
          </Col>
          <Col>
            <div className="form-group">
              <label>Condition</label>
              <Dropdown
                options={conditions}
                onChange={setCondition}
                // value={categories[0]}
                placeholder="Select a condition"
              />
            </div>
          </Col>
          <Col>
            <div className="form-group">
              <label>Location</label>
              <Dropdown
                options={locations}
                onChange={setLocation}
                // value={categories[0]}
                placeholder="Select a location"
              />
            </div>
          </Col>
          <Col>
            <div className="form-group sort-button">
              <button
                type="button"
                className="btn btn-secondary"
                onClick={groupItems}
              >
                Filter
              </button>
            </div>
          </Col>
        </Row>
        <Row>
          <Col>
            <Table striped bordered hover variant="dark">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Current Bid</th>
                  <th>Description</th>
                  <th>Category</th>
                  <th>Condition</th>
                  <th>Location</th>
                  <th>Time Limit</th>
                </tr>
              </thead>
              <tbody>
                {items.map((item) => {
                  return (
                    <tr
                      key={item.itemId}
                      onClick={() => {
                        setModalShow(true);
                        setData(item);
                      }}
                    >
                      <td>{item.name}</td>
                      <td>{`$${parseFloat(item.initialPrice).toFixed(2)}`}</td>
                      <td>{item.description}</td>
                      <td>{item.category}</td>
                      <td>{item.condition}</td>
                      <td>{item.location}</td>
                      <td>{`${formatTimestamp(item.timeLimit)}`}</td>
                    </tr>
                  );
                })}
              </tbody>
            </Table>
          </Col>
        </Row>
      </Container>

      <MyVerticallyCenteredModal
        show={modalShow}
        onHide={(bid) => {
          if (bid > data.initialPrice) {
            axios
              .put("http://localhost:8080/api/updateBid", {
                itemId: data.itemId,
                userId: user.userId,
                bidAmount: bid,
              })
              .then((res) => {
                alert(
                  "Congratulations! You are now the highest bidder on the following item: " +
                    data.name
                );
                setModalShow(false);
              })
              .catch((err) => console.log(err));
          } else {
            alert("Place a higher bid.");
          }

          //setModalShow(false);
          console.log("bids: " + bid);
        }}
        onClose={() => setModalShow(false)}
        itemData={data}
      />
    </div>
  );
}

function MyVerticallyCenteredModal(props) {
  const [bid, setBid] = useState(0);

  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          {props.itemData.name}
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <form action="">
          <div className="form-group">
            <label>{`Description: ${props.itemData.description}`}</label>
          </div>

          <div className="form-group">
            <label>{`Condition: ${props.itemData.condition}`}</label>
          </div>

          <div className="form-group">
            <label>{`Current Bid: $${parseFloat(
              props.itemData.initialPrice
            ).toFixed(2)}`}</label>
          </div>

          <div className="form-group">
            <label>Your Bid</label>
            <input
              className="form-control"
              placeholder="Enter your bid"
              type="number"
              min={props.itemData.initialPrice + 1}
              onChange={(e) => setBid(e.target.value)}
            />
            <Button
              onClick={() => props.onHide(bid)}
              style={{ marginTop: "10px" }}
            >
              Place Bid
            </Button>
          </div>
        </form>
      </Modal.Body>
      <Modal.Footer>
        <Button onClick={props.onClose}>Close</Button>
      </Modal.Footer>
    </Modal>
  );
}
