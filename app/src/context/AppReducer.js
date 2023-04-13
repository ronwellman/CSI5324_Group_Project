// import axios, { HttpStatusCode } from "axios";

// const AppReducer = (state, action) => {
//   var { email, password } = action.payload;

//   switch (action.type) {
//     case "LOGIN":
//       return doLogin(state, email, password);
//     case "LOGOUT":
//       return doLogout(state);
//     default:
//       console.log("invalid action in reducer");
//       return;
//   }
// };

// async function doLogin(state, email, password) {
//   console.log("Login attempt: " + email + " " + password);
//   await axios
//     .post("/api/login", { email, password })
//     .then((response) => {
//       if (response.status === HttpStatusCode.Ok) {
//         console.log("Successful Login");
//         console.log("Access: " + response.data.access_token);
//         console.log("Refresh: " + response.data.refresh_token);
//         return {
//           bearer: response.data.access_token,
//           refresh: response.data.refresh_token,
//           email: email,
//           loggedIn: true,
//         };
//       }
//     })
//     .catch(function (error) {
//       console.error("Error: " + error);
//       return false;
//     });
// }

// async function doLogout(state) {
//   await axios
//     .post("/api/logout", {})
//     .then((response) => {
//       if (response.status === HttpStatusCode.Ok) {
//         console.log("Successful Logout");
//       }
//     })
//     .catch(function (error) {
//       console.error("Error: " + error);
//     });

//   return {
//     bearer: null,
//     refresh: null,
//     email: null,
//     loggedIn: false,
//   };
// }

// export default AppReducer;
