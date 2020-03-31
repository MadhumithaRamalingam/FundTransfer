package com.stock.service;

import java.util.List;

import com.stock.dto.PurchaseRequestDto;
import com.stock.dto.ResponseDto;
import com.stock.dto.StockDetailsResponseDto;
import com.stock.dto.StockList;
import com.stock.exception.QuantityNotSufficientException;
import com.stock.exception.StockNotFoundException;
import com.stock.exception.UserNotFoundException;

public interface StockService {
	
	public List<StockList> getStocks(String stockName);
	
	public StockDetailsResponseDto getStockDetails(Long stockId)throws StockNotFoundException;
	
	public ResponseDto buyStock(Long userId,PurchaseRequestDto purchaseRequestDto)throws UserNotFoundException,StockNotFoundException,QuantityNotSufficientException;

}
