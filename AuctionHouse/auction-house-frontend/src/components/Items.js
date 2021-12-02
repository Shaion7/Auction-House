import axios from "axios";
import React, { useState, useEffect } from "react";
import MyNavbar from "./MyNavbar";

import { Container, Row, Col, Table, Modal, Button } from "react-bootstrap";
import { useHistory } from "react-router-dom";

// const [modalShow, setModalShow] = useState(false);

export default function Items() {
  const user = JSON.parse(localStorage.getItem("loggedUser"));
  const history = useHistory();

  const [items, setItems] = useState([]);

  const [modalShow, setModalShow] = useState(false);
  const [data, setData] = useState({});

  useEffect(() => {
    getAllItems();
    console.log("useEffect running");
  }, [modalShow]);

  const getAllItems = () => {
    axios
      .post("http://localhost:8080/api/getAllItemOnSale", {
        username: user.username,
      })
      .then((res) => {
        console.log(res);
        setItems(res.data);
      });
  };

  return (
    <div>
      <MyNavbar />
      <Container>
        <Row>
          <Col>
            <h2>Browse All Items On Sale</h2>
            <div>Click a row to bid on an item</div>
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
                {items.map((item) => (
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
                    <td>{item.timeLimit}</td>
                  </tr>
                ))}
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
