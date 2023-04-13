import React, { useContext, useEffect, useState } from "react";
import { GlobalContext } from "../context/GlobalState";
import { Link, useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "../styles/styles.css";

const Dashboard = () => {
  const navigate = useNavigate();
  const { email, loggedIn } = useContext(GlobalContext);
  if (!loggedIn) {
    navigate("/login");
  }
  return (
    <div>
      <h2>My Freelance Posts</h2>
      <table>
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
    </div>
  );
};

export default Dashboard;
