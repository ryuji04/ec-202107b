package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.repository.OrderRepository;

/**
 * ordersテーブルを操作するService.
 * 
 * @author nayuta
 */
@Service
@Transactional
public class OrderService {
	@Autowired
	private OrderRepository repository;

	/**
	 * order情報を変更するメソッド.
	 * 
	 * @param form
	 * @return 宛先名や宛先住所、状態の情報変更後のorder
	 */
	public Order upDateOrder(OrderForm form) {
		// 注文確認画面に表示されていたOrderを取得
		Order order = repository.findById(form.getId());

		// 支払い方法によってstatusを変更
		if (form.getPaymentMethod() == 1) {
			repository.upDate(order);
			order.setStatus(1);
//			repository.upDateStatus1();
		} else {
			repository.upDate(order);
			order.setStatus(2);
//			repository.upDateStatus2();
		}

		// 変更後のorderをreturn
		return order;
	}
}
