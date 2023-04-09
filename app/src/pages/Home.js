import React from "react";
import authToken from "../api/Token";
import 'bootstrap/dist/css/bootstrap.min.css'
import '../styles/styles.css'

const Home = () => {
	if (localStorage.access_token) {
		authToken(localStorage.access_token);
	}

	return (
		<>
		
		</>
	)
}

export default Home;