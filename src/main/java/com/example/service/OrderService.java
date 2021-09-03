package com.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.repository.OrderRepository;

/**
 * ordersテーブルを操作するService.
 * 
 * @author nayuta,okahikari
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
		Order order = repository.findById(form.getId());
		BeanUtils.copyProperties(form, order);
		//
		
		// 支払い方法によってstatusを変更
		if (form.getPaymentMethod() == 1) {
			order.setStatus(1);
		} else {
			order.setStatus(2);
		}
		repository.upDate(order);
		// 変更後のorderをreturn
		return order;
	}
}
