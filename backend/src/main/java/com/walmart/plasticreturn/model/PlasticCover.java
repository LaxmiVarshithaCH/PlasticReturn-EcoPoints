package com.walmart.plasticreturn.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PlasticCover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String barcode;

    private LocalDateTime purchaseDate;

    private boolean isReturned = false;

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

	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public boolean isReturned() {
		return isReturned;
	}

	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "PlasticCover [id=" + id + ", barcode=" + barcode + ", purchaseDate=" + purchaseDate + ", isReturned="
				+ isReturned + ", user=" + user + "]";
	}
}
