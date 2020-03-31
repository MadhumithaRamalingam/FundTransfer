package com.stock.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long purchseId;
	private Integer purchaseQuantity;
	private Double grossPrice;
	private LocalDate purchaseDate;
	
	@ManyToOne
	private User user;
	@OneToOne
	private Stock stock;

	public Long getPurchseId() {
		return purchseId;
	}

	public void setPurchseId(Long purchseId) {
		this.purchseId = purchseId;
	}
	

	public Integer getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setPurchaseQuantity(Integer purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public Double getGrossPrice() {
		return grossPrice;
	}

	public void setGrossPrice(Double grossPrice) {
		this.grossPrice = grossPrice;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Purchase() {
		super();
	}

	@Override
	public String toString() {
		return "Purchase [purchseId=" + purchseId + ", purchaseQuantity=" + purchaseQuantity + ", grossPrice="
				+ grossPrice + ", purchaseDate=" + purchaseDate + ", user=" + user + ", stock=" + stock + "]";
	}

	
}
