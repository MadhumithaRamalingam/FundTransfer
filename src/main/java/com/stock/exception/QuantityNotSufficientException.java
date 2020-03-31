package com.stock.exception;

public class QuantityNotSufficientException extends Exception{

	private static final long serialVersionUID = 1L;
private static final Integer statuscode=4000;
	
	public QuantityNotSufficientException(Integer quantity)
	{
		super("Quantity: "+quantity+" is not found");
	}
	
	public static Integer getStatuscode() {
		return statuscode;
	}

}
