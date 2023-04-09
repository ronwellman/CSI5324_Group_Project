import React from "react"

import { Outlet, Link } from "react-router-dom";

const  Header = ({loggedInState, setLoggedInState}) => {
	return (
		<>
		<div className="row banner">
			<div className="col">
				<div className="logo">Task Hiring Service</div>
			</div>
				<div className="col-md-auto">
					<div><Link to="/">Home</Link></div>
				</div>
				<div className="col-md-auto">
					<div>Link2</div>
				</div>
				<div className="col-md-auto">
					<div>Link3</div>
				</div>
				<div className="col-md-auto">
				{/* { loggedInState ? <div>Logout</div> : <div><Link to="/Login">Login</Link></div> }  */}
				{ loggedInState }
					
				</div>
		</div>

		<Outlet />
		</>
	)
}

export default Header;