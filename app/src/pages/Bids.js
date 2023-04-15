import "bootstrap/dist/css/bootstrap.min.css";
import "../styles/styles.css";
import { useLocation, useNavigate } from "react-router-dom";
import { useContext, useRef, useState } from "react";
import { GlobalContext } from "../context/GlobalState";
import { doAcceptBid } from "../api/Bid";

const Bids = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { bids } = location.state;

  const { loggedIn, userId, access_token } = useContext(GlobalContext);

  if (!loggedIn) {
    navigate("/login");
  }

  const bidCallback = (response) => {
    console.log(response.data);
    navigate("/dashboard");
  };

  const acceptBid = (e, bidId) => {
    e.preventDefault();

    doAcceptBid(bidId, access_token, bidCallback);
  };

  return (
    <div className="container-md border border-primary rounded p-2 login">
      <h2>Bids</h2>
      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Comp Type</th>
            <th>Comp Amt</th>
            <th>Accept</th>
          </tr>
        </thead>
        <tbody>
          {bids?.map((bid) => (
            <tr key={bid.id}>
              <td>{bid.id}</td>
              <td>{bid.compensationType}</td>
              <td>{bid.compensationAmount}</td>
              <td>
                <a
                  onClick={(e) => acceptBid(e, bid.id)}
                  href="/accept_bid"
                  target="_blank"
                  rel="noreferrer"
                >
                  Accept
                </a>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Bids;
