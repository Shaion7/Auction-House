import Login from "./components/Login";
import Signup from "./components/Signup";
import Home from "./components/Home";
import SellItem from "./components/SellItem";
import UserItems from "./components/UserItems";
import Items from "./components/Items";

import "./App.css";

import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import History from "./components/History";

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route exact path="/" component={Login} />
          <Route path="/signup" component={Signup} />
          <Route path="/home" component={Home} />
          <Route path="/sellItem" component={SellItem} />
          <Route path="/myItems" component={UserItems} />
          <Route path="/allItems" component={Items} />
          <Route path="/history" component={History} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
