package com.walmart.plasticreturn.dto;

import java.util.List;

public class EmailSummaryRequest {
	private String email;
    private int totalPoints;
    private List<String> barcodes;


    public List<String> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<String> barcodes) {
        this.barcodes = barcodes;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
}
