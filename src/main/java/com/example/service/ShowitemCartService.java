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

/**
 * ordersテーブルを操作するservice.
 * 
 * @author nayuta
 */
/**
 * @author nayuta
 *
 */
@Service
@Transactional
public class ShowitemCartService {
	@Autowired
	private OrderRepository repository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ToppingRepository toppingRepository;

	/**
	 * statusが0のデータを取得するメソッド.
	 * 
	 * @param userId ユーザーID
	 * @return statusが0のorder
	 */
	public Order showItemCart(Integer userId, Integer status) {
		Order order = new Order();
		order = repository.findByUserIdAndStatus(userId, status);
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
