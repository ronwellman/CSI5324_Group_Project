import React from "react";
import axios, { HttpStatusCode } from 'axios';
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css'
import '../styles/styles.css'

const Logout = (setLoggedInState) => {

	axios.get('/api/logout', {"bearer": localStorage.getItem["access_token"]})
	.then( (response) => {
		if (response.status === HttpStatusCode.Ok) {
			localStorage.setItem["access_token"] = response.data.access_token;
			localStorage.setItem["refresh_token"] = response.data.refresh_token;
			const loginState = () => setLoggedInState(false);
		}
	})
	.catch( function (error) {
		console.error("Error: " + error);
	});
}

export default Logout;