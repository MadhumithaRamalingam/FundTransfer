package com.stock.exception;

public class UserNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
private static final Integer statuscode=4000;
	
	public UserNotFoundException(Long userId)
	{
		super("UserId: "+userId+" is not found");
	}
	
	public static Integer getStatuscode() {
		return statuscode;
	}

}
