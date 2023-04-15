import React, { useContext, useEffect, useState, Filter } from "react";
import { GlobalContext } from "../context/GlobalState";
import { Link, useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "../styles/styles.css";
import { doGetCommissions, doGetMyCommissions } from "../api/Commission";
import {
  doGetFreelancePosts,
  doDisableFreelancePost,
  doEnableFreelancePost,
} from "../api/FreelancePost";
import doHireFreelancer from "../api/HireFreelancer";
import { doGetFreelancePostsByUser } from "../api/FreelancePost";

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
    setMyPosts(response.data);
  };

  const myPostsCallbackLogger = (response) => {
    myPosts.push(response.data);
    console.log(response.data);
  };

  const myCommissionsCallback = (response) => {
    setMyCommissions(response.data);
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

  const toggleActive = (e, freelancePostId, active) => {
    e.preventDefault();
    console.log("PostId: " + freelancePostId + " " + active);
    if (active) {
      doDisableFreelancePost(
        freelancePostId,
        access_token,
        myPostsCallbackLogger
      );
    } else {
      doEnableFreelancePost(
        freelancePostId,
        access_token,
        myPostsCallbackLogger
      );
    }
  };

  ///////////////////////////////////////////
  // Effects
  ///////////////////////////////////////////

  // useEffect(() => {
  //   doGetCommissions(access_token, commissionsCallback);
  // }, []);

  useEffect(() => {
    doGetFreelancePostsByUser(userId, access_token, myPostsCallback);
  }, []);

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
            <th>Title</th>
            <th>Comp Type</th>
            <th>Comp Amt</th>
            <th>Active</th>
            <th>Toggle</th>
          </tr>
        </thead>
        <tbody>
          {myPosts.map((post) => (
            <tr key={post.id}>
              <td>{post.id}</td>
              <td>{post.listingTitle}</td>
              <td>{post.compensationType}</td>
              <td>{post.compensationAmount}</td>
              <td>{post.active ? "ACTIVE" : "DISABLED"}</td>
              <td>
                <a
                  onClick={(e) => toggleActive(e, post.id, post.active)}
                  href="/"
                  target="_blank"
                  rel="noreferrer"
                >
                  Toggle
                </a>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <h2>My Commissions</h2>
      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Budget</th>
            <th>Deadline</th>
            <th>Bids</th>
          </tr>
        </thead>
        <tbody>
          {myCommissions.map((commission) => (
            <tr key={commission.id}>
              <td>{commission.id}</td>
              <td>{commission.listingTitle}</td>
              <td>{commission.budget}</td>
              <td>{commission.deadline}</td>
              <td>
                <Link
                  to={"/bids"}
                  state={{
                    bids: commission.bids,
                  }}
                >
                  {commission.bids.length}
                </Link>
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
