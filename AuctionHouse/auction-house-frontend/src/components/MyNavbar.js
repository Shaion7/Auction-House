import React from "react";
import { useHistory } from "react-router-dom";
import { Container, Navbar, Nav } from "react-bootstrap";

export default function MyNavbar() {
  const user = JSON.parse(localStorage.getItem("loggedUser"));
  const history = useHistory();
  const logout = () => {
    localStorage.clear();
    history.push("/");
  };

  const goToHome = () => {
    history.push("/home");
  };

  const goToSellItemPage = () => {
    history.push("/sellItem");
  };

  const goToAllItems = () => {
    history.push("/allItems");
  };

  const goToUserHistory = () => {
    history.push("/history");
  };

  return (
    <div>
      <Navbar bg="dark" variant="dark">
        <Container>
          <Navbar.Brand>{`Hello, ${user.firstName} ${user.lastName}!`}</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link onClick={goToHome}>Home</Nav.Link>
            <Nav.Link onClick={goToSellItemPage}>Sell Items</Nav.Link>
            <Nav.Link onClick={goToAllItems}>Browse Items</Nav.Link>
            <Nav.Link onClick={goToUserHistory}>My History</Nav.Link>
            <Nav.Link onClick={logout}>Logout</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
      <div style={{ marginBottom: "50px" }}></div>
    </div>
  );
}
