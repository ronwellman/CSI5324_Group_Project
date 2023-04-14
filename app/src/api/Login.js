import { HttpStatusCode } from "axios";
import api from "./axiosConfig";

// const doLogin = async (email, password) => {
const doLogin = async (email, password, callback) => {
  await api
    .post("/api/login", { email, password })
    .then((response) => {
      if (response.status === HttpStatusCode.Ok) {
        console.log("Successful Login");
        console.log("Access: " + response.data.access_token);
        console.log("Refresh: " + response.data.refresh_token);
        console.log("UserId: " + response.data.userId);
        callback(response);
      }
    })
    .catch((error) => {
      console.error("Error: " + error);

      return null;
    });
};

export default doLogin;
