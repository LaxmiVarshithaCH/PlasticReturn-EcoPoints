import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LandingPage from "./pages/LandingPage";
import Scanner from "./pages/Scanner";
import Dashboard from "./pages/Dashboard";

function App() {
  const [scannedUserEmail, setScannedUserEmail] = useState("");

  return (
    <Router>
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/scan" element={<Scanner setUserEmail={setScannedUserEmail} />} />
        <Route path="/dashboard" element={<Dashboard userEmail={scannedUserEmail} />} />
      </Routes>
    </Router>
  );
}

export default App;
