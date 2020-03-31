package com.stock.dto;

public class PurchaseRequestDto {
	
	private Long stockId;
	private Integer purchaseQuantity;
	
	public Integer getPurchaseQuantity() {
		return purchaseQuantity;
	}
	public void setPurchaseQuantity(Integer purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}
	
	public Long getStockId() {
		return stockId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	public PurchaseRequestDto() {
		super();
	}
	@Override
	public String toString() {
		return "PurchaseRequestDto [stockId=" + stockId + ", purchaseQuantity=" + purchaseQuantity + "]";
	}
	

}
