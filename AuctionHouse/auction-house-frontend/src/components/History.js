import React from "react";
import MyNavbar from "./MyNavbar";
import { Container, Row, Col, Table } from "react-bootstrap";

export default function History() {
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
                <tr>
                  <td>Speaker</td>
                  <td>40</td>
                  <td>It has bluetooth</td>
                </tr>
                <tr>
                  <td>Notebook</td>
                  <td>20</td>
                  <td>Wide ruled 200 pages</td>
                </tr>
                <tr>
                  <td>Air Conditioner</td>
                  <td>200</td>
                  <td>Cool</td>
                </tr>
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
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Phone Case</td>
                  <td>30</td>
                  <td>Nice case</td>
                </tr>
                <tr>
                  <td>Keychain</td>
                  <td>10</td>
                  <td>Holds your keys</td>
                </tr>
              </tbody>
            </Table>
          </Col>
        </Row>
      </Container>
    </div>
  );
}
