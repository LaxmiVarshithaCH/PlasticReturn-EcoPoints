package com.walmart.plasticreturn.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReturnHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String barcode;
    private LocalDateTime returnDate;
    private int pointsAwarded;

    @ManyToOne
    private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public int getPointsAwarded() {
		return pointsAwarded;
	}

	public void setPointsAwarded(int pointsAwarded) {
		this.pointsAwarded = pointsAwarded;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ReturnHistory [id=" + id + ", barcode=" + barcode + ", returnDate=" + returnDate + ", pointsAwarded="
				+ pointsAwarded + ", user=" + user + "]";
	}
}
