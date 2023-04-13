import axios, { HttpStatusCode } from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
import "../styles/styles.css";

const doLogout = async (callback, access_token) => {
  const config = {
    headers: { Authorization: `Bearer ${access_token}` },
  };
  axios
    .post("/api/logout", config)
    .then((response) => {
      if (response.status === HttpStatusCode.Ok) {
        console.log("Successful Logout");
      }
    })
    .catch(function (error) {
      console.error("Error: " + error);
    });

  // always logout regardless of success or fail
  callback();
};

export default doLogout;
