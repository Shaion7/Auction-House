import { useEffect, useState } from "react";
import MyNavbar from "./MyNavbar";
import { Container, Row, Col, Table } from "react-bootstrap";
import axios from "axios";

export default function History() {
  const user = JSON.parse(localStorage.getItem("loggedUser"));
  // const [date, setDate] = useState(new Date());

  const [soldItems, setSoldItems] = useState([]);
  const [expiredItems, setExpiredItems] = useState([]);
  const [reset, setReset] = useState(false);

  useEffect(() => {
    getSoldItems();
    getExpiredItems();
    const interval = setInterval(() => {
      checkIfItemExpired();
    }, 10000);
    setReset(false);
    return () => {
      // setReset(false);
      clearInterval(interval);
    };
  }, [reset]);

  // DATETIME FORMAT IN SQL
  // 2021-12-02 21:28:11

  const checkIfItemExpired = () => {
    axios
      .post("http://localhost:8080/api/handleExpiredAndSoldItems", {
        username: user.username,
      })
      .then((res) => {
        setReset(true);
      });
  };

  const getSoldItems = () => {
    axios
      .post("http://localhost:8080/api/getSoldItems", {
        username: user.username,
      })
      .then((res) => {
        setSoldItems(res.data);
      });
  };

  const getExpiredItems = () => {
    axios
      .post("http://localhost:8080/api/getExpiredItems", {
        username: user.username,
      })
      .then((res) => {
        setExpiredItems(res.data);
      });
  };

  const formatTimestamp = (ts) => {
    /* Split Timestamp into Date and Time */
    const timestamp = ts.split("T");
    const date = timestamp[0];
    const time = timestamp[1];

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
        <Row>
          <Col>
            <h2>Items Sold</h2>
            <Table striped bordered hover variant="dark">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Final Price</th>
                  <th>Description</th>
                </tr>
              </thead>
              <tbody>
                {soldItems.map((item) => (
                  <tr key={item.soldItemId}>
                    <td>{item.itemName}</td>
                    <td>{`$${parseFloat(item.finalPrice).toFixed(2)}`}</td>
                    <td>{item.itemDescription}</td>
                  </tr>
                ))}
              </tbody>
            </Table>
          </Col>
          <Col>
            <h2>Expired Items</h2>
            <Table striped bordered hover variant="dark">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Final Price</th>
                  <th>Description</th>
                  <th>Time Expired</th>
                </tr>
              </thead>
              <tbody>
                {expiredItems.map((item) => (
                  <tr key={item.expiredItemId}>
                    <td>{item.itemName}</td>
                    <td>{`$${parseFloat(item.itemPrice).toFixed(2)}`}</td>
                    <td>{item.itemDescription}</td>
                    <td>{formatTimestamp(item.expiredTime)}</td>
                  </tr>
                ))}
              </tbody>
            </Table>
          </Col>
        </Row>
      </Container>
    </div>
  );
}
