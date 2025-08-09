import React, { useState, useEffect } from "react";
import BarcodeScannerComponent from "react-qr-barcode-scanner";
import axios from "axios";
import "./Scanner.css";
import { useNavigate } from "react-router-dom";

function Scanner({ setUserEmail }) {
  const [barcode, setBarcode] = useState("");
  const [message, setMessage] = useState("");
  const [scannedCount, setScannedCount] = useState(0);
  const [scannedBarcodes, setScannedBarcodes] = useState([]);
  const [userEmail, setUserEmailState] = useState("");

  const navigate = useNavigate();

  const [lastScannedTime, setLastScannedTime] = useState(0);

const handleScan = async (result) => {
  const now = Date.now();

  if (result?.text && now - lastScannedTime > 5000) {
    setLastScannedTime(now);
    setBarcode(result.text);
    setScannedCount((prev) => prev + 1);
      setScannedBarcodes((prev) => [...prev, result.text]);
    try {
      const res = await axios.post("http://localhost:8080/api/scan-barcode", {
  barcode: result.text,
});
      setMessage(res.data.message);

      if (res.data.userEmail) {
  setUserEmailState(res.data.userEmail);
  if (setUserEmail) setUserEmail(res.data.userEmail);
}
    } catch (error) {
      console.error("Axios error:", error);
      if (error.response) {
        setMessage(`Server error: ${error.response.data}`);
      } else if (error.request) {
        setMessage("No response from server.");
      } else {
        setMessage("Error: " + error.message);
      }
    }
  }
};


const stopScanning = async () => {
  console.log("📧 Email to send:", userEmail);
  console.log("📦 Scanned barcodes:", scannedBarcodes);
  console.log("🌱 Total points:", scannedCount * 10);

  if (!userEmail) {
    alert("No email found. Did you scan at least one barcode?");
    return;
  }

  try {
    console.log("Sending summary email request to backend...");
    const res = await axios.post("http://localhost:8080/api/send-summary-email", {
      email: userEmail,
      totalPoints: scannedCount * 10,
      barcodes: scannedBarcodes,
    });
    console.log("Backend response:", res.data);
  } catch (error) {
    console.error("Failed to send summary email:", error);
  }

  navigate("/dashboard");
};

  return (
    <div className="scanner-container">
      <h1 className="scanner-title">Scan Your Plastic Cover</h1>

      <div className="camera-box">
        <BarcodeScannerComponent
          width={500}
          height={400}
          onUpdate={(err, result) => {
            console.log("🧪 onUpdate running");
            if (err) {
              console.error("Scanner error:", err.message);
              return;
            }
            if (result) {
              handleScan(result);
            }
          }}
        />
      </div>

      {barcode && <p className="status-text">📦 Detected: {barcode}</p>}
      {message && <p className="success-message">{message}</p>}
      {scannedCount > 0 && (
        <p className="status-text">Covers Scanned: {scannedCount}</p>
      )}

      <button onClick={stopScanning} className="stop-button">
        Stop & View Dashboard
      </button>

    </div>
  );
}

export default Scanner;
