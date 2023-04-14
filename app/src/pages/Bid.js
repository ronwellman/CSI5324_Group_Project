import "bootstrap/dist/css/bootstrap.min.css";
import "../styles/styles.css";
import { useLocation, useNavigate } from "react-router-dom";
import doCreateBid from "../api/Bid";
import { useContext, useRef, useState } from "react";
import { GlobalContext } from "../context/GlobalState";
import Select from "react-select";

const Bid = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { commissionId } = location.state;

  const { loggedIn, userId, access_token } = useContext(GlobalContext);

  if (!loggedIn) {
    navigate("/login");
  }

  const callback = () => {
    navigate("/dashboard");
  };

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
    var active = checked;
    var compensationType = compensationState;
    var compensationAmount = compensationAmountRef.current.value;

    console.log(
      compensationType + ":" + compensationAmount + ":" + userId,
      ":" + commissionId
    );

    const newFreelancePost = {
      compensationType,
      compensationAmount,
      userId,
      commissionId,
    };

    doCreateBid(newFreelancePost, access_token, callback);
  };

  return (
    <div className="container-md border border-primary rounded p-2 login">
      <h2>Create a Freelance Post</h2>
      <br />
      <form onSubmit={onSubmit}>
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

export default Bid;
