import "bootstrap/dist/css/bootstrap.min.css";
import "../styles/styles.css";
import { useContext, useRef, useState } from "react";
import { GlobalContext } from "../context/GlobalState";
import { useNavigate } from "react-router-dom";
import doCreateFreelancePost from "../api/FreelancePost";
import Select from "react-select";

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
  const compensationAmountRef = useRef();

  const [checked, setChecked] = useState(true);

  const handleChange = () => {
    setChecked(!checked);
  };

  const compensationOptions = [
    {
      value: "HOURLY",
      label: "HOURLY",
    },
    {
      value: "ONE_TIME",
      label: "ONE_TIME",
    },
  ];

  const [compensationState, updateCompensation] = useState(
    compensationOptions[0]
  );
  const updateCompensationState = (e) => {
    console.log(e);
    updateCompensation(e.value);
  };

  const onSubmit = async (e) => {
    e.preventDefault();

    var listingTitle = listingTitleRef.current.value;
    var description = descriptionRef.current.value;
    var active = checked;
    var compensationType = compensationState;
    var compensationAmount = compensationAmountRef.current.value;

    console.log(
      listingTitle +
        ":" +
        description +
        ":" +
        active +
        ":" +
        compensationType +
        ":" +
        compensationAmount +
        ":" +
        userId
    );

    const newFreelancePost = {
      listingTitle,
      description,
      active,
      compensationType,
      compensationAmount,
      userId,
    };

    doCreateFreelancePost(newFreelancePost, access_token, callback);
  };

  return (
    <div className="container-md border border-primary rounded p-2 login">
      <h2>Create a Freelance Post</h2>
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
        <label className="form-check-label" htmlFor="active">
          Active:
        </label>
        <input
          type="checkbox"
          checked={checked}
          onChange={handleChange}
          className="form-control form-check-input"
        />
        <br />
        <label className="form-label" htmlFor="compensationType">
          Compensation Type:
        </label>
        <Select
          className="form-control"
          options={compensationOptions}
          onChange={updateCompensationState}
          name="compensationType"
        />
        <br />
        <label className="form-label" htmlFor="compensationAmount">
          Compensation Amount:
        </label>
        <input
          type="text"
          placeholder="compensationAmount"
          className="form-control login_text"
          ref={compensationAmountRef}
        />
        <br />
        <button className="btn btn-primary mb-3">Create</button>
      </form>
    </div>
  );
};

export default FreelancePost;
