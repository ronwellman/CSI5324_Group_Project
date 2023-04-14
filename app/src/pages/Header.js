import React, { useContext, useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../styles/styles.css";

import { Outlet, Link } from "react-router-dom";
import { GlobalContext } from "../context/GlobalState";

const Header = () => {
  const { loggedIn } = useContext(GlobalContext);

  // updates the login banner (switches between login and logout)
  useEffect(() => {
    const interval = setInterval(() => bannerUpdate(loggedIn), 1000);

    return () => clearInterval(interval);
  }, [loggedIn]);

  const bannerUpdate = (loggedIn) => {
    if (loggedIn) {
      setBanner(
        <>
          <div className="col">
            <Link to="/">Home</Link>
          </div>
          <div className="col">
            <Link to="/dashboard">Dashboard</Link>
          </div>
          <div className="col">
            <Link to="/freelancePost">New FreelancePost</Link>
          </div>
          <div className="col">
            <Link to="/commission">New Commission</Link>
          </div>
          <div className="col">
            <Link to="/logout">Logout</Link>
          </div>
        </>
      );
    } else {
      setBanner(
        <>
          <div className="col">
            <Link to="/register">Register</Link>
          </div>
          <div className="col">
            <Link to="/login">Login</Link>
          </div>
        </>
      );
    }
  };

  console.log("Logged in state: " + loggedIn);

  const [banner, setBanner] = useState(
    <>
      <div className="col">
        <Link to="/register">Register</Link>
      </div>
      <div className="col">
        <Link to="/login">Login</Link>
      </div>
    </>
  );

  return (
    <>
      <div className="container-fluid">
        <div className="row banner">
          <div className="col-4">
            <div className="logo">Task Hiring Service</div>
          </div>
          {banner}
        </div>
      </div>
      <Outlet />
    </>
  );
};

export default Header;
