package com.stock.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stock.dto.PurchaseDetailsResponseDto;
import com.stock.exception.UserNotFoundException;
import com.stock.service.PurchaseService;

@RestController
public class PurchaseController {
	
	@Autowired
	PurchaseService purchaseService;
	
	Logger log=LoggerFactory.getLogger(StockDetailsController.class);
	
	@GetMapping("/users/{userId}/purchase")
	public ResponseEntity<List<PurchaseDetailsResponseDto>> getPurchaseDetails(@RequestParam Long userId) throws UserNotFoundException
	{
		List<PurchaseDetailsResponseDto> detailsResponseDto=purchaseService.getPurchaseDetails(userId);
		log.info("displaying the stock purchase details");
		return new ResponseEntity<>(detailsResponseDto, HttpStatus.OK);
		
	}

}
