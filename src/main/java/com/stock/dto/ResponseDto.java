package com.stock.dto;

public class ResponseDto {
	
	private String message;
	private Integer statuscode;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(Integer statuscode) {
		this.statuscode = statuscode;
	}
	public ResponseDto() {
		super();
	}
	@Override
	public String toString() {
		return "ResponseDto [message=" + message + ", statuscode=" + statuscode + "]";
	}
	

}
