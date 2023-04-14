import api from "./axiosConfig";
import { HttpStatusCode } from "axios";

const genericPost = async (url, data, access_token, callback) => {
  const config = {
    headers: { Authorization: `Bearer ${access_token}` },
  };

  await api
    .post(url, data, config, {
      headers: { "Content-Type": "application/json" },
    })
    .then((response) => {
      console.log(response.data);
      if (response.status === HttpStatusCode.Created) {
        console.log("Successful Post to: " + url);
      }
      callback(response);
    })
    .catch((err) => {
      console.log(err);
    });
};

export default genericPost;
