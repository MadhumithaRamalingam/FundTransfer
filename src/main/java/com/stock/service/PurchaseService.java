package com.stock.service;

import java.util.List;

import com.stock.dto.PurchaseDetailsResponseDto;
import com.stock.exception.UserNotFoundException;

public interface PurchaseService {
	
	public List<PurchaseDetailsResponseDto> getPurchaseDetails(Long userId)throws UserNotFoundException;

}
