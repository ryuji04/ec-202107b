package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

@Service
@Transactional
public class ShowOrderHistoryService {

	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> searchOrderHistory(Integer id) {
		List<Order> orderList = orderRepository.findByIdAndStatusHistory(id, 1, 2, 3, 4);
		return orderList;
	}
}
