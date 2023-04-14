import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "../styles/styles.css";
import doLogout from "../api/Logout";
import { GlobalContext } from "../context/GlobalState";

const Logout = (setLoggedInState) => {
  const navigate = useNavigate();

  const { setEmail, setLoggedIn, setBearer, setRefresh, access_token } =
    useContext(GlobalContext);

  const callback = () => {
    setEmail(null);
    setLoggedIn(false);
    setBearer(null);
    setRefresh(null);
    navigate("/");
  };

  doLogout(callback, access_token);
};

export default Logout;
