package com.example.service;

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
	
	public Order searchOrderHistory(Integer id) {
		Order order = orderRepository.findByIdAndStatusHistory(id, 1, 2, 3, 4);
		return order;
	}
}
