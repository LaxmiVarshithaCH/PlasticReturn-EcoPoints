import React from "react";
import { useNavigate } from "react-router-dom";
import "./LandingPage.css";

const LandingPage = () => {
  const navigate = useNavigate();

  const goToScanPage = () => {
    navigate("/scan");
  };

  return (
    <div className="landing-container">
      <h1 className="title">♻️ EcoReturn</h1>
      <p className="subtitle">Scan & Earn EcoPoints by returning plastic covers</p>
      <button className="scan-button" onClick={goToScanPage}>
        Start Scanning
      </button>
    </div>
  );
};

export default LandingPage;
