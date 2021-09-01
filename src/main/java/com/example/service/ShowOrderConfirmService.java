package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShowOrderConfirmService {

	@Autowired
	private OrderRepository orderRepository;
	
	public Order searchById(Integer id) {
		Order order = orderRepository.findById(id);
	}
}
