package com.stock.dto;

import java.time.LocalDate;

import javax.persistence.Column;

import com.stock.entity.Stock;

public class PurchaseDetailsResponseDto {
	
	private Long purchseId;
	private Integer purchaseQuantity;
	private LocalDate purchaseDate;
	private Double grossPrice;
	
	@Column(name = "stock_stock_name")
	private String stockName;
	
	public Long getPurchseId() {
		return purchseId;
	}
	public void setPurchseId(Long purchseId) {
		this.purchseId = purchseId;
	}
	public Integer getPurchaseQuantity() {
		return purchaseQuantity;
	}
	public void setPurchaseQuantity(Integer purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	

	public PurchaseDetailsResponseDto() {
		super();
	}
	public Double getGrossPrice() {
		return grossPrice;
	}
	public void setGrossPrice(Double grossPrice) {
		this.grossPrice = grossPrice;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	@Override
	public String toString() {
		return "PurchaseDetailsResponseDto [purchseId=" + purchseId + ", purchaseQuantity=" + purchaseQuantity
				+ ", purchaseDate=" + purchaseDate + ", grossPrice=" + grossPrice + ", stockName=" + stockName + "]";
	}
	
	
}
