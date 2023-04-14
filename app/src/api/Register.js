import { HttpStatusCode } from "axios";
import api from "./axiosConfig";

// const doLogin = async (email, password) => {
const doRegister = async (newUser, callback) => {
  await api
    .post("/api/new_user", JSON.stringify(newUser), {
      headers: { "Content-Type": "application/json" },
    })
    .then((response) => {
      console.log(response.data);
      if (response.status === HttpStatusCode.Created) {
        console.log("Created new User");
      }
      callback();
    })
    .catch((err) => {
      console.log(err);
    });
};

export default doRegister;
