import React, { useContext, useEffect, useState } from "react";
import { GlobalContext } from "../context/GlobalState";
import { Link, useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "../styles/styles.css";
import { doGetCommissions, doGetMyCommissions } from "../api/Commission";
import { doGetFreelancePosts } from "../api/FreelancePost";
import doHireFreelancer from "../api/HireFreelancer";
import { doAcceptBid } from "../api/Bid";

const Dashboard = () => {
  const navigate = useNavigate();
  const { email, userId, loggedIn, access_token } = useContext(GlobalContext);

  if (!loggedIn) {
    navigate("/login");
  }

  const [myPosts, setMyPosts] = useState([]);
  const [myCommissions, setMyCommissions] = useState([]);
  const [myCommissionBids, setMyCommissionBids] = useState([]);
  const [myBids, setMyBids] = useState([]);
  const [activePosts, setActivePosts] = useState([]);
  const [commissions, setCommissions] = useState([]);

  ///////////////////////////////////////////
  // Callbacks
  ///////////////////////////////////////////

  const myPostsCallback = (response) => {
    console.log(response.data);
  };

  const myCommissionsCallback = (response) => {
    setMyCommissions(response.data);
    // this only spiratically works
    var bids = [];
    console.log("before");
    for (var commission of myCommissions) {
      console.log("l1");
      for (var bid of commission.bids) {
        console.log("yep");
        bid["commissionId"] = commission.id;
        bids.push(bid);
      }
    }
    console.log("updating");
    setMyCommissionBids(bids);
  };

  const myBidsCallback = (response) => {
    console.log(response.data);
  };

  const activePostsCallback = (response) => {
    setActivePosts(response.data);
  };

  const commissionsCallback = (response) => {
    setCommissions(response.data);
  };

  const hireCallback = (response) => {
    console.log(response);
  };

  const hireFreelancer = (e, freelancePostId) => {
    e.preventDefault();
    const data = {
      freelancePostId: freelancePostId,
      userId: userId,
    };

    doHireFreelancer(data, access_token, hireCallback);
  };

  const acceptBid = (e, bidId) => {
    e.preventDefault();

    doAcceptBid(bidId, access_token, hireCallback);
  };

  ///////////////////////////////////////////
  // Effects
  ///////////////////////////////////////////

  // useEffect(() => {
  //   doGetCommissions(access_token, commissionsCallback);
  // }, []);

  // useEffect(() => {
  //   doGetCommissions(access_token, commissionsCallback);
  // }, []);

  useEffect(() => {
    doGetFreelancePosts(access_token, activePostsCallback);
  }, []);

  useEffect(() => {
    doGetCommissions(access_token, commissionsCallback);
  }, []);

  useEffect(() => {
    doGetMyCommissions(userId, access_token, myCommissionsCallback);
  }, []);

  return (
    <div>
      <h2>My Freelance Posts</h2>
      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Payee</th>
            <th>Memo</th>
            <th>Account</th>
            <th>Amount</th>
          </tr>
        </thead>
        {/* <tbody>
          {expenses.map((expense) => (
            <tr key={expense.id}>
              <td>{expense.id}</td>
              <td>{expense.payee}</td>
              <td>{expense.memo}</td>
              <td>{expense.account}</td>
              <td>{expense.amount}</td>
              <td>
                <ButtonGroup>
                  <Button className="btn-expense" variant="primary" >Edit</Button>
                  <Button className="btn-expense" variant="warning" >Delete</Button>
                </ButtonGroup>
              </td>
            </tr>
          ))}
        </tbody> */}
      </table>
      <h2>My Commissions</h2>
      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Budget</th>
            <th>Deadline</th>
          </tr>
        </thead>
        <tbody>
          {myCommissions.map((commission) => (
            <tr key={commission.id}>
              <td>{commission.id}</td>
              <td>{commission.listingTitle}</td>
              <td>{commission.budget}</td>
              <td>{commission.deadline}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <h2>Bids</h2>
      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>commissionId</th>
            <th>Comp Type</th>
            <th>Comp Amt</th>
            <th>Accept</th>
          </tr>
        </thead>
        <tbody>
          {myCommissionBids.map((bid) => (
            <tr key={bid.id}>
              <td>{bid.id}</td>
              <td>{bid.commissionId}</td>
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
      <h2>Active Posts</h2>
      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Comp Type</th>
            <th>Comp Amt</th>
            <th>Hire</th>
          </tr>
        </thead>
        <tbody>
          {activePosts.map((activePost) => (
            <tr key={activePost.id}>
              <td>{activePost.id}</td>
              <td>{activePost.listingTitle}</td>
              <td>{activePost.compensationType}</td>
              <td>{activePost.compensationAmount}</td>
              <td>
                <a
                  onClick={(e) => hireFreelancer(e, activePost.id)}
                  href="/hire"
                  target="_blank"
                  rel="noreferrer"
                >
                  Hire
                </a>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <h2>Open Commissions</h2>
      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Budget</th>
            <th>Deadline</th>
            <th>BID</th>
          </tr>
        </thead>
        <tbody>
          {commissions.map((commission) => (
            <tr key={commission.id}>
              <td>{commission.id}</td>
              <td>{commission.listingTitle}</td>
              <td>{commission.budget}</td>
              <td>{commission.deadline}</td>
              <td>
                <Link
                  to={"/bid"}
                  state={{
                    commissionId: commission.id,
                  }}
                >
                  Bid
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Dashboard;
