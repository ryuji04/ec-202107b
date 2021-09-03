package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.service.OrderService;

/**
 * ordersテーブルを操作するcontroller.
 * 
 * @author nayuta
 */
@Controller
@RequestMapping("/order-item")
public class OrderController {
	@Autowired
	private OrderService service;

	@RequestMapping("/index")
	public String index() {
		return "order_confirm.html";
	}

	/**
	 * 注文最終確認画面.
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping("/order")
	public String order(OrderForm form, Model model) {
		// orderに情報変更後のorderを格納
		Order order = service.upDateOrder(form);

		model.addAttribute(order);

		return "redirect:order_finished.html";
	}

}