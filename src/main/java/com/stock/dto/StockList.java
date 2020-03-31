package com.stock.dto;

public class StockList {

	private Long stockId;
	private String stockName;
	
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
	public StockList() {
		super();
	}
	@Override
	public String toString() {
		return "StockResponseDto [stockId=" + stockId + ", stockName=" + stockName + "]";
	}
	
	
	
}
