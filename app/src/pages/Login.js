import React, { useState } from 'react'
import axios, { HttpStatusCode } from 'axios';
import Logout from './Logout';
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css'
import '../styles/styles.css'


const Login = (setLoggedInState) => {

	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');

	const handleLogin = async (event) => {
		event.preventDefault();
		console.log("handleLogin called: " + email + " " + password);
		event.preventDefault();
	
		await axios.post('/api/login', { email, password })
		.then( (response) => {
			if (response.status === HttpStatusCode.Ok) {
				localStorage.setItem["access_token"] = response.data.access_token;
				localStorage.setItem["refresh_token"] = response.data.refresh_token;
				const loginState = () => setLoggedInState(<Link to="/Logout">Logout</Link>);
			}
		})
		.catch( function (error) {
			console.error("Error: " + error);
		});
	  };

	return (
		<div className='container-md border border-primary rounded p-2 login'>
			<h2>Please Login</h2>
			<br />
			<form onSubmit={handleLogin}>
				<label className="form-label" >Email:</label>
				<input type='text' 
					placeholder='Email' 
					className='form-control login_text' 
					onChange={(e) => setEmail(e.target.value)}
					autoComplete='email'
					required />
				<br />

				<label className="form-label">Password:</label>
				<input type='password' 
					placeholder='Password'
					className='form-control login_text'
					onChange={(e) => setPassword(e.target.value)}
					autoComplete='current-password'
					required />
				<br />

				<button type='submit' className='btn btn-primary mb-3'>Login</button>
			</form>

			<div>Need an account: <Link to="/Register">Register</Link></div>
		</div>	
	);
}

export default Login;
