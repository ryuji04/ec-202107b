package com.example.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;
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
	public Order upDateOrder(OrderForm form, Model model) {
		Order order = repository.findById(form.getId());
		BeanUtils.copyProperties(form, order);

		// 注文日時
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		order.setOrderDate(date);

		// 注文日時用のtimestampオブジェクトを用意
		Timestamp orderTime = new Timestamp(date.getTime());

		// ユーザー配達希望日時
		String deliveryStrDate = form.getDeliveryDate();
		Date deliveryDate = null;
		try {
			deliveryDate = dateFormat.parse(deliveryStrDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int hour = Integer.parseInt(form.getDeliveryTime());

		// Orderの配達時間用のtimestampオブジェクトを用意
		Timestamp timestamp = new Timestamp(deliveryDate.getTime());
		timestamp.setHours(hour);
		order.setDeliveryTime(timestamp);

		// 日時のエラー追加
		if ((timestamp.getTime() - orderTime.getTime()) < 10800000) {
			model.addAttribute("deliveryDateError", "今から3時間後の日時をご入力ください");
		}

//		注文合計金額の格納
		order.setTotalPrice(order.getCalcTotalPrice());
		System.out.println(order.getCalcTotalPrice());
//		int totalPrice = 0;
//		for( OrderItem orderItem : order.getOrderItemList()) {
//			totalPrice += orderItem.getSubTotal();
//			System.out.println("orderItem: " + orderItem);
//			System.out.println("totalPrice: " + totalPrice);
//		}
//		int itemPrice = 0;
//		int totalToppingPrice = 0;
//		for (OrderItem orderItem : order.getOrderItemList()) {
//			if (orderItem.getSize() == 'M') {
//				itemPrice = orderItem.getItem().getPriceM();
//			}
//			if (orderItem.getSize() == 'L') {
//				itemPrice = orderItem.getItem().getPriceL();
//			}
//			for (OrderTopping orderTopping : orderItem.getOrderToppingList()) {
//				Topping topping = orderTopping.getTopping();
//				if (orderItem.getSize() == 'M') {
//					totalToppingPrice += topping.getPriceM();
//				}
//				if (orderItem.getSize() == 'L') {
//					totalToppingPrice += topping.getPriceL();
//				}
//			}
//			totalPrice += (itemPrice + totalToppingPrice) * orderItem.getQuantity();
//			System.out.println("totalPrice: " + totalPrice);
//		}

//		int tax = (int) (totalPrice * 0.1);
//		order.setTotalPrice(totalPrice + tax);

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
