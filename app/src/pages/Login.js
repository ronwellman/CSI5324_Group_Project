import React, { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "../styles/styles.css";
import { GlobalContext } from "../context/GlobalState";
import doLogin from "../api/Login";

const Login = () => {
  const navigate = useNavigate();

  const { setEmail, setLoggedIn, setBearer, setRefresh } =
    useContext(GlobalContext);

  const [email, updateEmail] = useState("");
  const [password, updatePassword] = useState("");

  // couldn't get the async axios promise working correctly so a callback instead...
  const callback = (response) => {
    setBearer(response.data.access_token);
    setRefresh(response.data.refresh_token);
    setEmail(email);
    setLoggedIn(true);
    navigate("/Dashboard");
  };

  const handleLogin = (event) => {
    event.preventDefault();
    doLogin(email, password, callback);
  };

  return (
    <div className="container-md border border-primary rounded p-2 login">
      <h2>Please Login</h2>
      <br />
      <form onSubmit={handleLogin}>
        <label className="form-label">Email:</label>
        <input
          type="text"
          placeholder="Email"
          className="form-control login_text"
          onChange={(e) => updateEmail(e.target.value)}
          autoComplete="email"
          required
        />
        <br />

        <label className="form-label">Password:</label>
        <input
          type="password"
          placeholder="Password"
          className="form-control login_text"
          onChange={(e) => updatePassword(e.target.value)}
          autoComplete="current-password"
          required
        />
        <br />

        <button type="submit" className="btn btn-primary mb-3">
          Login
        </button>
      </form>

      <div>
        Need an account: <Link to="/Register">Register</Link>
      </div>
    </div>
  );
};

export default Login;
