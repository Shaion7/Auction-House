import React, { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";

import { Container, Row, Col, Table } from "react-bootstrap";

import "./css/Home.css";
import MyNavbar from "./MyNavbar";
import axios from "axios";

export default function Home() {
  const user = JSON.parse(localStorage.getItem("loggedUser"));
  const history = useHistory();

  // all the items that are in item table
  const [userItemsOnSale, setUserItemsOnSale] = useState([]);

  const [userBids, setUserBids] = useState([]);
  const [reset, setReset] = useState(false);

  useEffect(() => {
    getAllUserItemsOnSale();
    getUserBids();
    setReset(false);
  }, [reset]);

  //Wont need this because this would be displayed in the home page already
  // const goToMyItemsOnSale = () => {
  //   history.push("/myItems");
  // };

  const getAllUserItemsOnSale = () => {
    const username = {
      username: user.username,
    };
    axios
      .post("http://localhost:8080/api/getItemsOnSaleForUser", username)
      .then((res) => {
        console.log(res);
        setUserItemsOnSale(res.data);
      });
  };

  const getUserBids = () => {
    const username = {
      username: user.username,
    };
    axios
      .post("http://localhost:8080/api/getUserBids", username)
      .then((res) => {
        console.log(res);
        setUserBids(res.data);
      });
  };

  const deleteItem = (id) => {
    axios
      .delete("http://localhost:8080/api/removeItem", { data: { itemId: id } })
      .then((res) => {
        setReset(true);
        console.log(res);
      })
      .catch((err) => console.log(err));
  };

  return (
    <div>
      <MyNavbar />
      <Container>
        <Row>
          <Col>
            <h2>Items I Bidded On</h2>
            <Table striped bordered hover variant="dark">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Initial Price</th>
                  <th>Description</th>
                  <th>Time Limit</th>
                </tr>
              </thead>
              <tbody>
                {userBids.map((item) => (
                  <tr key={item.itemId}>
                    <td>{item.name}</td>
                    <td>{item.category}</td>
                    <td>{item.condition}</td>
                    <td>{item.location}</td>
                    <td>{`$${parseFloat(item.bidAmount).toFixed(2)}`}</td>
                  </tr>
                ))}
              </tbody>
            </Table>
          </Col>
          <Col>
            <h2>My Items On Sale</h2>
            <Table striped bordered hover variant="dark">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Initial Price</th>
                  <th>Description</th>
                  <th>Category</th>
                  <th>Condition</th>
                  <th>Location</th>
                  <th>Time Limit</th>
                </tr>
              </thead>
              <tbody>
                {userItemsOnSale.map((item) => (
                  <tr key={item.itemId}>
                    <td>{item.name}</td>
                    <td>{`$${parseFloat(item.initialPrice).toFixed(2)}`}</td>
                    <td>{item.description}</td>
                    <td>{item.category}</td>
                    <td>{item.condition}</td>
                    <td>{item.location}</td>
                    <td>{item.timeLimit}</td>
                    <td>
                      <button
                        type="button"
                        className="btn btn-danger"
                        onClick={() => deleteItem(item.itemId)}
                      >
                        Remove
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          </Col>
        </Row>
      </Container>
    </div>

    // category: "Sports Equipment"
    // condition: "Not used"
    // description: "testing"
    // initialPrice: 300
    // itemId: 32
    // location: "San Jose"
    // name: "Bench Press"
    // timeLimit: "10"
    // <div className="homepage">
    //   <h1>{`Hello, ${user.username}`}</h1>
    // <button type="button" className="btn btn-danger" onClick={logout}>
    //   Logout
    // </button>

    // <button className="btn btn-primary" onClick={goToSellItemPage}>
    //   Sell Item
    // </button>

    // <button className="btn btn-primary" onClick={goToMyItemsOnSale}>
    //   My Items On Sale
    // </button>

    // <button className="btn btn-primary" onClick={goToMyBids}>
    //   Items I Bidded On
    // </button>

    //   <div className="home-items-table">
    //     <table>
    //       <thead>
    //         <tr>
    //           <th>Item Id</th>
    //           <th>Name</th>
    //           <th>Price</th>
    //           <th>Description</th>
    //         </tr>
    //       </thead>
    //       <tbody>
    //         {items.map((item) => (
    //           <tr
    //             key={item.itemId}
    //             onClick={() => goToBid(item.itemId, item.name)}
    //           >
    //             <td>{item.itemId}</td>
    //             <td>{item.name}</td>
    //             <td>{item.initialPrice}</td>
    //             <td>{item.description}</td>
    //           </tr>
    //         ))}
    //       </tbody>
    //     </table>
    //   </div>
    // </div>
  );
}
