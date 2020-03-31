package com.stock.dto;

import java.time.LocalDate;

public class StockDetailsResponseDto {
	
	private Long stockId;
	private String stockName;
	private Double price;
	private Integer quantity;
	private LocalDate stockDate;
	
	private Integer statuscode;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDate getStockDate() {
		return stockDate;
	}

	public void setStockDate(LocalDate stockDate) {
		this.stockDate = stockDate;
	}

	public Integer getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(Integer statuscode) {
		this.statuscode = statuscode;
	}

	public StockDetailsResponseDto() {
		super();
	}

	@Override
	public String toString() {
		return "StockDetailsResponseDto [stockId=" + stockId + ", stockName=" + stockName + ", price=" + price
				+ ", quantity=" + quantity + ", stockDate=" + stockDate + ", statuscode=" + statuscode + "]";
	}
	
	

}
