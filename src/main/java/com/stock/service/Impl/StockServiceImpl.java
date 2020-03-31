package com.stock.service.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.constant.Constant;
import com.stock.dto.PurchaseRequestDto;
import com.stock.dto.ResponseDto;
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
import com.stock.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	PurchaseRepository purchaseRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	StockRepository stockRepository;
	
	Logger log=LoggerFactory.getLogger(StockServiceImpl.class);
	@Override
	public StockDetailsResponseDto getStockDetails(Long stockId) throws StockNotFoundException {
		
		Optional<Stock> stock=stockRepository.findById(stockId);
		if(!stock.isPresent())
		{
			throw new StockNotFoundException(stockId);
		}
		StockDetailsResponseDto stockDetailsResponseDto=new StockDetailsResponseDto();
		stockDetailsResponseDto.setStockId(stock.get().getStockId());
		stockDetailsResponseDto.setStockName(stock.get().getStockName());
		stockDetailsResponseDto.setQuantity(stock.get().getQuantity());
		stockDetailsResponseDto.setPrice(stock.get().getPrice());
		stockDetailsResponseDto.setStockDate(stock.get().getStockDate());
		stockDetailsResponseDto.setStatuscode(Constant.SuccessStatusCode);
		log.info("Displaying the stock details");
		return stockDetailsResponseDto;
	}
	@Override
	public List<StockList> getStocks(String stockName) {
		
		List<StockList> stockResponseDto2=new ArrayList<>();
		
		List<Stock> stocks=stockRepository.findByStockNameContaining(stockName);
		stocks.forEach(stockDetails->{
			StockList responseDto=new StockList();
			BeanUtils.copyProperties(stockDetails, responseDto);
			stockResponseDto2.add(responseDto);
		});
		
		return stockResponseDto2;
	}
	
	@Override
	public ResponseDto buyStock(Long userId, PurchaseRequestDto purchaseRequestDto)
			throws UserNotFoundException, StockNotFoundException, QuantityNotSufficientException {
		Optional<User> user=userRepository.findById(userId);
		if(!user.isPresent())
		{
			throw new UserNotFoundException(userId);
		}
		Optional<Stock> stock=stockRepository.findById(purchaseRequestDto.getStockId());
		if(!stock.isPresent())
		{
			throw new StockNotFoundException(purchaseRequestDto.getStockId());
		}
		Integer stockQuantity=stock.get().getQuantity();
		Double stockPrice=stock.get().getPrice();
		Integer purchaseQuantity=purchaseRequestDto.getPurchaseQuantity();
		if(purchaseQuantity>stockQuantity)
		{
			throw new QuantityNotSufficientException(purchaseRequestDto.getPurchaseQuantity());
		}
		
		Double price=purchaseQuantity*stockPrice;
		
		Stock stock2=stockRepository.findByStockId(purchaseRequestDto.getStockId());
		stock2.setQuantity(stock2.getQuantity()-purchaseQuantity);
		stockRepository.save(stock2);
		
		Purchase purchase=new Purchase();
		purchase.setPurchaseQuantity(purchaseQuantity);
		purchase.setPurchaseDate(LocalDate.now());
		purchase.setGrossPrice(price);
		purchase.setUser(user.get());
		purchase.setStock(stock.get());
		purchaseRepository.save(purchase);
		
		ResponseDto responseDto=new ResponseDto();
		responseDto.setMessage(Constant.SuccessApply);
		responseDto.setStatuscode(Constant.SuccessStatusCode);
		log.info("Stock purchased Successfully");
				
		return responseDto;
	}
	


}
