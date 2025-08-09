package com.walmart.plasticreturn.dto;

public class ScanResponse {
    private String message;
    private String userEmail;
    private int pointsEarned;

    public ScanResponse() {
    }

    public ScanResponse(String message, String userEmail, int pointsEarned) {
        this.message = message;
        this.userEmail = userEmail;
        this.pointsEarned = pointsEarned;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }
}
