package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;
import com.example.repository.ItemRepository;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;
import com.example.repository.ToppingRepository;

@Service
@Transactional
public class ShowOrderHistoryService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	
	public List<Order> searchOrderHistory(Integer id) {
		List<Order> orderList = orderRepository.findByIdAndStatusHistory(id, 1, 2, 3, 4);
		return orderList;
	}
	
	public Order searchOrderHistoryDetail(Integer id) {
		Order order = orderRepository.findById(id);
		if( order != null ) {
			List<OrderItem> orderItemList = orderItemRepository.findByOrderId(order.getId());
			List<OrderTopping> orderToppingList = new ArrayList<>();
		
			for (OrderItem orderItem : orderItemList) {
				orderToppingList = orderToppingRepository.findByOrderItemId(orderItem.getId());
				orderItem.setItem(itemRepository.findById(orderItem.getItemId()));
				orderItem.setOrderToppingList(orderToppingList);

				for (OrderTopping orderTopping : orderItem.getOrderToppingList()) {
					Topping topping = toppingRepository.findById(orderTopping.getToppingId());
					orderTopping.setTopping(topping);
				}
			}
			order.setOrderItemList(orderItemList);
		}
		return order;
	}
}
