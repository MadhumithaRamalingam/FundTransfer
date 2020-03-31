package com.stock.controller;

import static org.junit.Assert.assertNotNull;

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
import org.springframework.http.ResponseEntity;

import com.stock.dto.PurchaseDetailsResponseDto;
import com.stock.entity.Purchase;
import com.stock.entity.User;
import com.stock.exception.UserNotFoundException;
import com.stock.service.PurchaseService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PurchaseControllerTest {

	@Mock
	PurchaseService purchaseService;
	
	@InjectMocks
	PurchaseController purchaseController;
	
	Purchase purchase=null;
	List<Purchase> purchases=null;
	PurchaseDetailsResponseDto purchaseDetailsResponseDto=null;
	List<PurchaseDetailsResponseDto> responseDto=null;
	
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
		
		purchaseDetailsResponseDto=new PurchaseDetailsResponseDto();
		purchaseDetailsResponseDto.setPurchseId(1L);
		
		responseDto=new ArrayList<>();
		responseDto.add(purchaseDetailsResponseDto);
		
	}
	
	@Test
	public void testGetPurchaseDetails() throws UserNotFoundException
	{
		Mockito.when(purchaseService.getPurchaseDetails(1L)).thenReturn(responseDto);
		ResponseEntity<List<PurchaseDetailsResponseDto>> response=purchaseController.getPurchaseDetails(1L);
		assertNotNull(response);
	}
}
