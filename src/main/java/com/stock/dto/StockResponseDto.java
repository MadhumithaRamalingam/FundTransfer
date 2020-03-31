package com.stock.dto;

import java.util.List;

public class StockResponseDto {
	
	private List<StockList> stockLists;
	private Integer statuscode;
	public List<StockList> getStockLists() {
		return stockLists;
	}
	public void setStockLists(List<StockList> stockLists) {
		this.stockLists = stockLists;
	}
	public Integer getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(Integer statuscode) {
		this.statuscode = statuscode;
	}
	@Override
	public String toString() {
		return "StockResponseDto [stockLists=" + stockLists + ", statuscode=" + statuscode + "]";
	}
	public StockResponseDto() {
		super();
	}

}
