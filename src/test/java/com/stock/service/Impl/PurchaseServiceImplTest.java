package com.stock.service.Impl;

import static org.junit.Assert.assertNotNull;
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

import com.stock.dto.PurchaseDetailsResponseDto;
import com.stock.entity.Purchase;
import com.stock.entity.Stock;
import com.stock.entity.User;
import com.stock.exception.UserNotFoundException;
import com.stock.repository.PurchaseRepository;
import com.stock.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PurchaseServiceImplTest {
	
	@Mock
	PurchaseRepository purchaseRepository;
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	PurchaseServiceImpl purchaseServiceImpl;
	
	Purchase purchase=null;
	List<Purchase> purchases=null;
	PurchaseDetailsResponseDto purchaseDetailsResponseDto=null;
	Stock stock=null;
	
	User user=null;
	
	@Before
	public void before()
	{
		purchase=new Purchase();
		purchase.setGrossPrice(100.00);
		purchase.setPurchaseDate(LocalDate.of(2020, 01, 04));
		purchase.setPurchaseQuantity(1);
		purchase.setPurchseId(1L);
		
		purchases=new ArrayList<>();
		purchases.add(purchase);
		
		user=new User();
		user.setUserId(1L);
		
		stock=new Stock();
		stock.setStockName("SBI common stock");
		
		purchaseDetailsResponseDto=new PurchaseDetailsResponseDto();
		purchaseDetailsResponseDto.setPurchseId(1L);
		purchaseDetailsResponseDto.setStockName("SBI common stock");
		
		
	}	

	@Test
	public void testGetPurchaseDetails() throws UserNotFoundException
	{
		Mockito.when(purchaseRepository.findByUserUserId(user.getUserId())).thenReturn(purchases);
		List<PurchaseDetailsResponseDto> response=purchaseServiceImpl.getPurchaseDetails(2L);
		
		assertNotNull(response);
	}
	
	@Test
	public void testGetPurchaseDetailsNegative() throws UserNotFoundException
	{
		Mockito.when(purchaseRepository.findByUserUserId(user.getUserId())).thenReturn(purchases);
		purchaseServiceImpl.getPurchaseDetails(2L);
		
	}
	
}
