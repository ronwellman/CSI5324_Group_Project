// import { useState, useEffect, useReducer } from "react";
// import { getUser } from "./auth.js";
// import { GlobalContext, initialState } from "./GlobalState.js";
// import AppReducer from "./AppReducer.js";

// export const AuthProvider = ({ children }) => {
//   const [state, dispatch] = useReducer(AppReducer, initialState);

//   function login(username, password) {
//     dispatch({
//       type: "LOGIN",
//       payload: { username, password },
//     });
//   }

//   function logout() {
//     dispatch({
//       type: "LOGOUT",
//       payload: null,
//     });
//   }

//   //   const [user, setUser] = useState(null);
//   //   useEffect(() => {
//   //     const currentUser = getUser();
//   //     setUser(currentUser);
//   //   }, []);

//   return (
//     <GlobalContext.Provider
//       VALUE={{
//         user: state.user,
//         loggedIn: state.loggedIn,
//         bearer: state.bearer,
//         refresh: state.refresh,
//         login,
//         logout,
//       }}
//     >
//       {children}
//     </GlobalContext.Provider>
//   );
// };
