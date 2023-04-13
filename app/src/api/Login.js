import axios, { HttpStatusCode } from "axios";

// const doLogin = async (email, password) => {
const doLogin = async (email, password, callback) => {
  axios
    .post("/api/login", { email, password })
    .then((response) => {
      if (response.status === HttpStatusCode.Ok) {
        console.log("Successful Login");
        console.log("Access: " + response.data.access_token);
        console.log("Refresh: " + response.data.refresh_token);
        callback(response);
      }
    })
    .catch((error) => {
      console.error("Error: " + error);

      return null;
    });
};

export default doLogin;
