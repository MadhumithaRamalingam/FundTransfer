package com.stock.exception;

import com.stock.entity.Stock;

public class StockNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	private static final Integer statuscode=4000;
	
	public StockNotFoundException(Long stockId)
	{
		super("StockId: "+stockId+" is not found");
	}
	
	public static Integer getStatuscode() {
		return statuscode;
	}
}
