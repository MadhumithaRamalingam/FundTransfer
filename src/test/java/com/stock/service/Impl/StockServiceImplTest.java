package com.stock.service.Impl;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.stock.dto.PurchaseRequestDto;
import com.stock.dto.StockDetailsResponseDto;
import com.stock.dto.StockList;
import com.stock.entity.Purchase;
import com.stock.entity.Stock;
import com.stock.entity.User;
import com.stock.exception.QuantityNotSufficientException;
import com.stock.exception.StockNotFoundException;
import com.stock.exception.UserNotFoundException;
import com.stock.repository.PurchaseRepository;
import com.stock.repository.StockRepository;
import com.stock.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class StockServiceImplTest {
	
	@InjectMocks
	StockServiceImpl stockServiceImpl;
	
	@Mock
	StockRepository stockRepository;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	PurchaseRepository purchaseRepository;
	
	List<Stock> stockList=null;
	Stock stock=null;
	Stock stock2=null;
	StockDetailsResponseDto responseDto=null;
	User user=null;
	PurchaseRequestDto purchaseRequestDto=null;
	PurchaseRequestDto purchaseRequestDto1=null;
	Purchase purchase=null;
	@Before
	public void before()
	{
		stock=new Stock();
		stock.setStockId(1L);
		stock.setStockName("SBI common stock");
		stock.setQuantity(10);
		stock.setPrice(200.00);
		stock.setStockDate(LocalDate.of(2020, 03, 30));
		
		stockList=new ArrayList<>();
		stockList.add(stock);
		
		responseDto=new StockDetailsResponseDto();
		responseDto.setStatuscode(2000);
		
		user=new User();
		user.setUserId(1L);
		
		purchaseRequestDto=new PurchaseRequestDto();
		purchaseRequestDto.setStockId(1L);
		purchaseRequestDto.setPurchaseQuantity(1);
		
		purchaseRequestDto1=new PurchaseRequestDto();
		purchaseRequestDto1.setStockId(1L);
		purchaseRequestDto1.setPurchaseQuantity(20);
		
		stock2=new Stock();
		stock2.setStockId(1L);
		stock2.setQuantity(9);
		
		purchase=new Purchase();
		Double price=purchaseRequestDto.getPurchaseQuantity() * stock.getPrice();
		purchase.setGrossPrice(price);
		purchase.setPurchseId(1L);
	}
	
	@Test
	public void testgetStockDetailsTest() throws StockNotFoundException
	{
		Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
		StockDetailsResponseDto response=stockServiceImpl.getStockDetails(1L);
		assertEquals(response.getStockId(),stock.getStockId());
	}
	
	@Test(expected = StockNotFoundException.class)
	public void testgetStockDetailsTestNegative() throws StockNotFoundException
	{
		Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
		StockDetailsResponseDto response=stockServiceImpl.getStockDetails(null);
		assertNotEquals(response.getStockId(), stock.getStockId());
		
	}
	
	@Test
	public void testGetStocks()
	{
		Mockito.when(stockRepository.findByStockNameContaining("SBI")).thenReturn(stockList);
		List<StockList> expected=stockServiceImpl.getStocks("SBI");
		assertEquals(stockList.size(), expected.size());
	}
	
	@Test
	public void testBuyStock() throws UserNotFoundException, StockNotFoundException, QuantityNotSufficientException
	{
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
		Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(stock2));
		Mockito.when(stockRepository.save(stock)).thenReturn(stock);
		Mockito.when(purchaseRepository.save(purchase)).thenReturn(purchase);
		assertNotNull(responseDto);
	}

	@Test(expected = UserNotFoundException.class)
	public void testUserNotFound() throws UserNotFoundException, StockNotFoundException, QuantityNotSufficientException {
		Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(user));
		stockServiceImpl.buyStock(1L, purchaseRequestDto);

	}
	
	@Test(expected = QuantityNotSufficientException.class)
	public void testQuantityNotSufficientException() throws UserNotFoundException, StockNotFoundException, QuantityNotSufficientException 
	{
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
		Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(stock2));
		Mockito.when(stockRepository.save(stock)).thenReturn(stock);
		
		stockServiceImpl.buyStock(1L, purchaseRequestDto1);
	}
	
	
	
	

}
