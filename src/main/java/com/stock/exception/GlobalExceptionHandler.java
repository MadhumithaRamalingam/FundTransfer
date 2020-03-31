package com.stock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.stock.dto.ResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(StockNotFoundException.class)
	ResponseEntity<ResponseDto> stockNotFoundException(StockNotFoundException exception)
	{
		ResponseDto response=new ResponseDto();
		response.setMessage(exception.getMessage());
		response.setStatuscode(4000);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	ResponseEntity<ResponseDto> userNotFoundException(UserNotFoundException exception)
	{
		ResponseDto response=new ResponseDto();
		response.setMessage(exception.getMessage());
		response.setStatuscode(4000);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler(QuantityNotSufficientException.class)
	ResponseEntity<ResponseDto> quantityNotSufficientException(QuantityNotSufficientException exception)
	{
		ResponseDto response=new ResponseDto();
		response.setMessage(exception.getMessage());
		response.setStatuscode(4000);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
