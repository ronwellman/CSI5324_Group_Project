// import { HttpStatusCode } from "axios";
// import api from "./axiosConfig";

import genericPost from "./GenericPost";

const doCreateFreelancePost = (data, access_token, callback) => {
  return genericPost("/api/new_post", data, access_token, callback);
};

// export default doCreateCommission;

// const doLogin = async (email, password) => {
// const doCreateFreelancePost = async (
//   newFreelancePost,
//   access_token,
//   callback
// ) => {
//   const config = {
//     headers: { Authorization: `Bearer ${access_token}` },
//   };

//   await api
//     .post("/api/new_post", newFreelancePost, config, {
//       headers: { "Content-Type": "application/json" },
//     })
//     .then((response) => {
//       console.log(response.data);
//       if (response.status === HttpStatusCode.Created) {
//         console.log("Created Freelance Post: " + response.data.id);
//       }
//       callback(response);
//     })
//     .catch((err) => {
//       console.log(err);
//     });
// };

export default doCreateFreelancePost;
