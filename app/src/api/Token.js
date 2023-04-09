import axios from "axios";

const authToken = (token) => {
  if (token) {
    axios.defaults.headers.common["bearer"] = `${token}`;
  } else {
    delete axios.defaults.headers.common["Authorization"];
  }
};

export default authToken;