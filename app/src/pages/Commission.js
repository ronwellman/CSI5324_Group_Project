import "bootstrap/dist/css/bootstrap.min.css";
import "../styles/styles.css";
import { useContext, useRef, useState } from "react";
import { GlobalContext } from "../context/GlobalState";
import { useNavigate } from "react-router-dom";
import doCreateCommission from "../api/Commission";

const FreelancePost = () => {
  const navigate = useNavigate();
  const { userId, access_token, loggedIn } = useContext(GlobalContext);
  if (!loggedIn) {
    navigate("/login");
  }

  const callback = () => {
    navigate("/dashboard");
  };

  const listingTitleRef = useRef();
  const descriptionRef = useRef();
  const budgetRef = useRef();
  const deadlineRef = useRef();

  const onSubmit = async (e) => {
    e.preventDefault();

    var listingTitle = listingTitleRef.current.value;
    var description = descriptionRef.current.value;
    var deadline = deadlineRef.current.value;
    var budget = budgetRef.current.value;

    console.log(
      listingTitle +
        ":" +
        description +
        ":" +
        deadline +
        ":" +
        budget +
        ":" +
        userId
    );

    const newCommission = {
      listingTitle,
      description,
      deadline,
      budget,
      userId,
    };

    doCreateCommission(newCommission, access_token, callback);
  };

  return (
    <div className="container-md border border-primary rounded p-2 login">
      <h2>Request a Commission</h2>
      <br />
      <form onSubmit={onSubmit}>
        <label className="form-label" htmlFor="listingTitle">
          Title:
        </label>
        <input
          type="text"
          placeholder="listingTitle"
          className="form-control login_text"
          ref={listingTitleRef}
          required
        />
        <br />
        <label className="form-label" htmlFor="description">
          Description:
        </label>
        <input
          type="text"
          placeholder="description"
          className="form-control login_text"
          ref={descriptionRef}
          required
        />
        <br />
        <label className="form-label" htmlFor="deadline">
          Deadline (ex: 2023-04-28T00:00:00):
        </label>
        <input
          type="text"
          placeholder="dead;ome"
          className="form-control login_text"
          ref={deadlineRef}
        />
        <br />
        <label className="form-label" htmlFor="budget">
          Budget:
        </label>
        <input
          type="text"
          placeholder="budget"
          className="form-control login_text"
          ref={budgetRef}
        />
        <br />
        <button className="btn btn-primary mb-3">Create</button>
      </form>
    </div>
  );
};

export default FreelancePost;
