package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
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
	public void add(AddItemCartForm form) {
		Order order = new Order();
		// AddItemCartformだけだとorderが既にあるかどうかの判定に必要なuserIdとかstatusとかが渡ってこないしinsertもできん
		// controllerから注文トッピングだけじゃなく注文商品自体の情報も引っ張ってくる必要あり?
		
		// もしfindByUserIdAndStatus()メソッドでOrderが見つからなかったらOrderもinsertする
		if( orderRepository.findByUserIdAndStatus(userId,status)) {
			orderRepository.insert(order);
		} else {
			// 見つかったらOrderToppingとOrderItemのみinsertする
			orderItemRepository.insert(orderItem);
			orderToppingRepository.insert(orderTopping);
		}
	}
}
