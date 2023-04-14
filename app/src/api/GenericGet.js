import api from "./axiosConfig";
import { HttpStatusCode } from "axios";

const genericGet = async (url, access_token, callback) => {
  const config = {
    headers: { Authorization: `Bearer ${access_token}` },
  };

  await api
    .get(url, config, {
      headers: { "Content-Type": "application/json" },
    })
    .then((response) => {
      console.log(response.data);
      if (response.status === HttpStatusCode.Created) {
        console.log("Successful GET to: " + url);
      }
      callback(response);
    })
    .catch((err) => {
      console.log(err);
    });
};

export default genericGet;
