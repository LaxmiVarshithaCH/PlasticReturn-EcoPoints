import React, { useEffect, useState } from "react";
import "./Dashboard.css";
import axios from "axios";

const Dashboard = ({ userEmail }) => {
  const [user, setUser] = useState(null);
  const [history, setHistory] = useState([]);

  useEffect(() => {
    if (!userEmail) return;

    axios.get(`http://localhost:8080/api/user?email=${userEmail}`)
      .then((res) => setUser(res.data))
      .catch((err) => console.error("Error fetching user:", err));

    axios.get(`http://localhost:8080/api/return-history?email=${userEmail}`)
      .then((res) => setHistory(res.data))
      .catch((err) => console.error("Error fetching history:", err));
  }, [userEmail]);

  return (
    <div className="dashboard-container">
      <h1>Eco Dashboard 🌱</h1>

      {user ? (
        <div className="user-card">
          <h2>{user.name}</h2>
          <p>Email: {user.email}</p>
          <p className="points">EcoPoints: {user.ecoPoints}</p>
        </div>
      ) : (
        <p>Loading user...</p>
      )}

      <h3>Return History</h3>
      <table className="history-table">
        <thead>
          <tr>
            <th>Barcode</th>
            <th>Return Date</th>
            <th>Points</th>
          </tr>
        </thead>
        <tbody>
          {history.map((item) => (
            <tr key={item.id}>
              <td>{item.barcode}</td>
              <td>{new Date(item.returnDate).toLocaleString()}</td>
              <td>{item.pointsAwarded}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Dashboard;
