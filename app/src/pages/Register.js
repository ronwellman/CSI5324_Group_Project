import React, { useRef } from "react";
import api from "../api/axiosConfig";

const Register = () => {
  const firstNameRef = useRef();
  const lastNameRef = useRef();
  const streetRef = useRef();
  const cityRef = useRef();
  const stateRef = useRef();
  const zipRef = useRef();
  const phoneRef = useRef();
  const emailRef = useRef();
  const passwordRef = useRef();

  const register = async (e) => {
    e.preventDefault();
    var firstName = firstNameRef.current.value;
    var lastName = lastNameRef.current.value;
    var street = streetRef.current.value;
    var city = cityRef.current.value;
    var state = stateRef.current.value;
    var zip = zipRef.current.value;
    var phone = phoneRef.current.value;
    var email = emailRef.current.value;
    var password = passwordRef.current.value;

    const newUser = {
      firstName,
      lastName,
      street,
      city,
      state,
      zip,
      phone,
      email,
      password,
    };

    try {
      await api
        .post("/api/new_user", JSON.stringify(newUser), {
          headers: { "Content-Type": "application/json" },
        })
        .then((response) => {
          console.log(response.data);
        });
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className="container-md border border-primary rounded p-2 login">
      <h2>Register for an Account</h2>
      <br />
      <form onSubmit={register}>
        <label className="form-label" htmlFor="firstName">
          First Name:
        </label>
        <input
          type="text"
          placeholder="firstName"
          className="form-control login_text"
          ref={firstNameRef}
          required
        />
        <br />
        <label className="form-label" htmlFor="lastName">
          Last Name:
        </label>
        <input
          type="text"
          placeholder="lastName"
          className="form-control login_text"
          ref={lastNameRef}
          required
        />
        <br />
        <label className="form-label" htmlFor="street">
          Street Address:
        </label>
        <input
          type="text"
          placeholder="street"
          className="form-control login_text"
          ref={streetRef}
        />
        <br />
        <label className="form-label" htmlFor="city">
          City:
        </label>
        <input
          type="text"
          placeholder="city"
          className="form-control login_text"
          ref={cityRef}
        />
        <br />
        <label className="form-label" htmlFor="state">
          State:
        </label>
        <input
          type="text"
          placeholder="state"
          className="form-control login_text"
          ref={stateRef}
        />
        <br />
        <label className="form-label" htmlFor="zip">
          Zip:
        </label>
        <input
          type="text"
          placeholder="zip"
          className="form-control login_text"
          ref={zipRef}
        />
        <br />
        <label className="form-label" htmlFor="phone">
          Phone:
        </label>
        <input
          type="text"
          placeholder="phone"
          className="form-control login_text"
          ref={phoneRef}
          required
        />
        <br />
        <label className="form-label" htmlFor="email">
          Email:
        </label>
        <input
          type="text"
          placeholder="email"
          className="form-control login_text"
          ref={emailRef}
          required
        />
        <br />
        <label className="form-label" htmlFor="password">
          Password:
        </label>
        <input
          type="password"
          placeholder="password"
          className="form-control login_text"
          ref={passwordRef}
          required
        />
        <br />
        <button className="btn btn-primary mb-3">Register</button>
      </form>
    </div>
  );
};

export default Register;
