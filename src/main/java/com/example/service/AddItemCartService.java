package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.form.AddItemCartForm;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;

/**
 * 追加注文商品情報を操作するサービス.
 * 
 * @author okahikari
 *
 */
@Service
@Transactional
public class AddItemCartService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	
	/**
	 * カートに商品を追加する.
	 * 
	 * @param form 注文商品追加時に使用するフォーム
	 */
	public void add(AddItemCartForm form, Integer userId, Integer status) {
		//OrderがあってもなくてもOrderItemはインサートする必要あるからインスタンス化しておく
		OrderItem orderItem = new OrderItem();
		
		Order returnOrder = orderRepository.findByUserIdAndStatus(userId, status);
		
		// もしfindByUserIdAndStatus()メソッドでOrderが見つからなかったらOrderインスタンス化してinsertする
		if( returnOrder == null) {
			//Orderのinsert
			Order order = new Order();
			order.setUserId(userId);
			order.setStatus(status);
			//OrderItemのgetSubTotal()を使う
			order.setTotalPrice(0);
			orderRepository.insert(order);
			
			//OrderItemのinsert
			orderItem.setItemId(form.getItemId());
			orderItem.setOrderId(order.getId());
			orderItem.setQuantity(form.getQuantity());
			orderItem.setSize(form.getSize());
			orderItemRepository.insert(orderItem);
			
			//OrderToppingのinsert
			List<Integer> toppingList = form.getToppingList();
			for( Integer topping : toppingList ) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setOrderItemId(orderItem.getId());
				orderTopping.setToppingId(topping);
				orderToppingRepository.insert(orderTopping);
			}
			
		} else {
			//Order見つかったらOrderToppingとOrderItemのみinsertする
			
			//OrderItemのinsert
			orderItem.setOrderId(returnOrder.getId());
			orderItem.setItemId(form.getItemId());
			orderItem.setQuantity(form.getQuantity());
			orderItem.setSize(form.getSize());
			OrderItem returnOrderItem = orderItemRepository.insert(orderItem);
			
			//OrderToppingのinsert
			List<Integer> toppingList = form.getToppingList();
			for( Integer topping : toppingList ) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setOrderItemId(returnOrderItem.getId());
				orderTopping.setToppingId(topping);
				orderToppingRepository.insert(orderTopping);
			}
		}
	}
}
