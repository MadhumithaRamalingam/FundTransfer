package com.stock.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.dto.PurchaseDetailsResponseDto;
import com.stock.entity.Purchase;
import com.stock.entity.Stock;
import com.stock.entity.User;
import com.stock.exception.UserNotFoundException;
import com.stock.repository.PurchaseRepository;
import com.stock.repository.UserRepository;
import com.stock.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Override
	public List<PurchaseDetailsResponseDto> getPurchaseDetails(Long userId) throws UserNotFoundException {
		List<Purchase> purchases=purchaseRepository.findByUserUserId(userId);
		List<PurchaseDetailsResponseDto> purchaseDetailsResponseDto=purchases.stream().map(details->{
			PurchaseDetailsResponseDto responseDto=new PurchaseDetailsResponseDto();
			responseDto.setStockName(details.getStock().getStockName());
			BeanUtils.copyProperties(details, responseDto);
			return responseDto;
		}).collect(Collectors.toList());
		return purchaseDetailsResponseDto;
	}
	
	

}
