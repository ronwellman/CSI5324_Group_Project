import React, { useState } from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route, Link} from "react-router-dom";
import Header from "./pages/Header";
import Login from './pages/Login';
import Logout from './pages/Logout';
import Home from './pages/Home';
import Register from './pages/Register';
// import App from './component/App.js';
import 'bootstrap/dist/css/bootstrap.min.css';
import './styles/styles.css'

export default function App() {
  const [loggedInState, setLoggedInState] = useState(<Link to="/Login">Login</Link>);

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Header loggedInState={loggedInState} />}>
          <Route index element={<Home />} />
          <Route path="login" element={<Login setLoggedInState={setLoggedInState}/>} />
          <Route path='logout' element={<Logout />} />
          <Route path="register" element={<Register />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}


const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  // <React.StrictMode>
    <App />
  // </React.StrictMode>
);

