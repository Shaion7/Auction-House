import axios from "axios";
import React, { useState, useEffect } from "react";

export default function UserItems() {
  const user = JSON.parse(localStorage.getItem("loggedUser"))[0];

  const [userItems, setUserItems] = useState([]);

  useEffect(() => {
    getUserItems();
  }, []);

  const getUserItems = () => {
    axios
      .post("http://localhost:3001/getUserItems", {
        userId: user.userId,
      })
      .then((res) => {
        console.log(res.data);
        setUserItems(res.data);
      });
  };

  return (
    <div>
      <div>
        <table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Price</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>
            {userItems.map((item) => (
              <tr key={item.itemId}>
                <td>{item.name}</td>
                <td>{item.initialPrice}</td>
                <td>{item.description}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
