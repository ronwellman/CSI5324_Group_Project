import React from 'react'
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css'
import '../styles/styles.css'


const Login = () => {

	return (
		<div className='container-md border border-primary rounded p-2 login'>
			<h2>Please Login</h2>
			<br />
			<form>
				<label className="form-label" >Email:</label>

				<input type='text' placeholder='Email' className='form-control login_text' id='inputEmail' required />
				<br />
				<label className="form-label">Password:</label>

				<input type='password' placeholder='Password'  className='form-control login_text' id='inputPassword' required />
				<br />
				<button type='submit' className='btn btn-primary mb-3' id='loginBtn'>Login</button>
			</form>

			<div>Need to <Link to="/Register">Register</Link></div>
		</div>	
	);
}

export default Login;
