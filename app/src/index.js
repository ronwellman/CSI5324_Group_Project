import React, { useState } from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import { GlobalContext } from "./context/GlobalState";
import Header from "./pages/Header";
import Login from "./pages/Login";

import Home from "./pages/Home";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import Logout from "./pages/Logout";
import Bid from "./pages/Bid";
import Commission from "./pages/Commission";
import FreelancePost from "./pages/FreelancePost";
import Job from "./pages/Job";
import Message from "./pages/Messages";
import Review from "./pages/Review";

import "bootstrap/dist/css/bootstrap.min.css";
import "./styles/styles.css";

export default function App() {
  const [email, setEmail] = useState(null);
  const [loggedIn, setLoggedIn] = useState(false);
  const [access_token, setBearer] = useState(null);
  const [refresh_token, setRefresh] = useState(null);
  const [userId, setUserId] = useState(0);

  const initialState = {
    email: email,
    setEmail,
    loggedIn: loggedIn,
    setLoggedIn,
    access_token: access_token,
    setBearer,
    refresh_token: refresh_token,
    setRefresh,
    userId: userId,
    setUserId,
  };

  return (
    <GlobalContext.Provider value={initialState}>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Header />}>
            <Route index element={<Home />} />
            <Route path="login" element={<Login />} />
            <Route path="logout" element={<Logout />} />
            <Route path="register" element={<Register />} />
            <Route path="dashboard" element={<Dashboard />} />
            <Route path="freelancePost" element={<FreelancePost />} />
            <Route path="commission" element={<Commission />} />
            <Route path="bid" element={<Bid />} />
            <Route path="Job" element={<Job />} />
            <Route path="Review" element={<Review />} />
            <Route path="Message" element={<Message />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </GlobalContext.Provider>
  );
}

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  // <React.StrictMode>

  <App />

  //</React.StrictMode>
);
