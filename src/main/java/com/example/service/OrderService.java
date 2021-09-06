package com.example.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.form.OrderForm;
import com.example.repository.ItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ToppingRepository;

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
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;

	/**
	 * order情報を変更するメソッド.
	 * 
	 * @param form
	 * @return 宛先名や宛先住所、状態の情報変更後のorder
	 */
	public Order upDateOrder(OrderForm form) {
		Order order = repository.findById(form.getId());
		BeanUtils.copyProperties(form, order);
		
		//注文日時
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String orderStrDate = dateFormat.format(date);
//		System.out.println(orderStrDate);
//		try {
//			//注文日時をDate型に変換
//			date = dateFormat.parse(orderStrDate);
//			System.out.println(date + "testdate");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		order.setOrderDate(date);
		
		//ユーザー配達希望日時
		String deliveryStrDate = form.getDeliveryDate();
		Date deliveryDate = null;
		try {
			deliveryDate = dateFormat.parse(deliveryStrDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int hour = Integer.parseInt(form.getDeliveryTime());
		
		//Orderの配達時間用のtimestampオブジェクトを用意
		Timestamp timestamp = new Timestamp(deliveryDate.getTime());
		timestamp.setHours(hour);
		order.setDeliveryTime(timestamp);
		
		//日時のエラー追加
		if( (timestamp.getTime() - date.getTime()) < 10800000 ) {
			//result.rejectValue("deliveryDate", "", "今から3時間後の日時をご入力ください");
		}
		
		//注文合計金額の格納
		int totalPrice = 0;
		for( OrderItem item : order.getOrderItemList()) {
			item.setItem(itemRepository.findById(item.getItemId()));
			for(OrderTopping orderTopping : item.getOrderToppingList()) {
				orderTopping.setTopping(toppingRepository.findById(orderTopping.getToppingId()));
			}
			totalPrice += item.getSubTotal();
		}
		order.setTotalPrice(totalPrice);

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
	
	/**
	 * Orderを取得するメソッド.
	 * 
	 * @param id ID
	 * @return 注文前のOrder
	 */
	public Order findById(Integer id) {
		Order order = repository.findById(id);
		return order;
	}
}
