package com.stock.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stock.constant.Constant;
import com.stock.dto.PurchaseRequestDto;
import com.stock.dto.ResponseDto;
import com.stock.dto.StockDetailsResponseDto;
import com.stock.dto.StockList;
import com.stock.dto.StockResponseDto;
import com.stock.exception.QuantityNotSufficientException;
import com.stock.exception.StockNotFoundException;
import com.stock.exception.UserNotFoundException;
import com.stock.service.StockService;

@RestController
public class StockDetailsController {
	
	@Autowired
	StockService stockService;
	
	Logger log=LoggerFactory.getLogger(StockDetailsController.class);
	
	@GetMapping("/stocks/{stockId}")
	public ResponseEntity<StockDetailsResponseDto> getStockDetails(@RequestParam Long stockId)throws StockNotFoundException
	{
		StockDetailsResponseDto response=stockService.getStockDetails(stockId);
		log.info("displaying the details of stock");
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/stocks")
	public ResponseEntity<StockResponseDto> getStocks(@RequestParam String stockName)
	{
		StockResponseDto responseDto=new StockResponseDto();
		
		List<StockList> stockList=stockService.getStocks(stockName);
		if(stockList.isEmpty())
		{
			responseDto.setStatuscode(4000);
			return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
		}
		responseDto.setStockLists(stockList);
		responseDto.setStatuscode(Constant.SuccessStatusCode);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
		
	}
	
	@PostMapping("/users/{userId}/stocks")
	public ResponseEntity<ResponseDto> buyStocks(@RequestParam Long userId,@RequestBody PurchaseRequestDto purchaseRequestDto) throws UserNotFoundException, StockNotFoundException, QuantityNotSufficientException
	{
		ResponseDto responseDto=stockService.buyStock(userId, purchaseRequestDto);
		log.info("Stock purchased successfully");
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
		
	}

}
