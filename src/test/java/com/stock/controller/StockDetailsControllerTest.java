package com.stock.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.stock.constant.Constant;
import com.stock.dto.PurchaseRequestDto;
import com.stock.dto.ResponseDto;
import com.stock.dto.StockDetailsResponseDto;
import com.stock.dto.StockList;
import com.stock.entity.Stock;
import com.stock.exception.QuantityNotSufficientException;
import com.stock.exception.StockNotFoundException;
import com.stock.exception.UserNotFoundException;
import com.stock.service.StockService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class StockDetailsControllerTest {
	
	@InjectMocks
	StockDetailsController stockDetailsController;
	
	@Mock
	StockService stockService;
	
	List<StockList> stockLists=null;
	List<StockList> stockLists2=null;
	
	StockList stockList=null;
	
	Stock stock=null;
	StockDetailsResponseDto responseDto=null;
	
	PurchaseRequestDto purchaseRequestDto=null;
	ResponseDto responseDto1=null;
	
	@Before
	public void before()
	{
		stock=new Stock();
		stock.setStockId(1L);
		stock.setStockName("SBI common stock");
		stock.setQuantity(10);
		stock.setPrice(200.00);
		stock.setStockDate(LocalDate.of(2020, 03, 30));
		
		responseDto=new StockDetailsResponseDto();
		responseDto.setStockId(1L);
		responseDto.setStockName("SBI common stock");
		responseDto.setQuantity(10);
		responseDto.setPrice(200.00);
		responseDto.setStockDate(LocalDate.of(2020, 03, 30));
		responseDto.setStatuscode(200);
		
		stockLists=new ArrayList<>();
		stockList=new StockList();
		stockList.setStockName("SBI");
		stockLists.add(stockList);
		
		purchaseRequestDto=new PurchaseRequestDto();
		purchaseRequestDto.setStockId(1L);
		purchaseRequestDto.setPurchaseQuantity(1);
		
		responseDto1=new ResponseDto();
		responseDto1.setStatuscode(HttpStatus.OK.value());
		
	}
	
	@Test
	public void testGetStockDetails() throws StockNotFoundException
	{
		Mockito.when(stockService.getStockDetails(1L)).thenReturn(responseDto);
		ResponseEntity<StockDetailsResponseDto> response=stockDetailsController.getStockDetails(1L);
		assertEquals(response.getBody().getStockId(), responseDto.getStockId());
	}
	
	@Test
	public void testGetStockPositive()
	{
		Mockito.when(stockService.getStocks("SBI")).thenReturn(stockLists);
		Integer expected=stockDetailsController.getStocks("SBI").getStatusCodeValue();
		assertEquals(200, expected);
	}
	
	@Test
	public void testGetStockNegative()
	{
		Mockito.when(stockService.getStocks("SBI")).thenReturn(stockLists);
		Integer expected=stockDetailsController.getStocks("ICICI").getStatusCodeValue();
		assertEquals(404, expected);
	}
	
	@Test
	public void testBuyStock() throws UserNotFoundException, StockNotFoundException, QuantityNotSufficientException
	{
		Mockito.when(stockService.buyStock(1L, purchaseRequestDto)).thenReturn(responseDto1);
		Integer expected=stockDetailsController.buyStocks(1L, purchaseRequestDto).getStatusCodeValue();
		assertEquals(responseDto1.getStatuscode(), expected);
	}
	

}
