package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.service.OrderService;

/**
 * ordersテーブルを操作するcontroller.
 * 
 * @author nayuta, okahikari
 */
@Controller
@RequestMapping("/order-item")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@ModelAttribute
	public OrderForm setUpForm() {
		return new OrderForm();
	}

	@RequestMapping("/index")
	public String index(Model model) {
		return "order_confirm.html";
	}

	/**
	 * 注文をする.
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping("/order")
	public String order(@Validated OrderForm form ,BindingResult result, Model model, @AuthenticationPrincipal LoginUser loginUser) {
		if(result.hasErrors()) {
			return index(model);
		}
		// orderに情報変更後のorderを格納
		Order order = orderService.upDateOrder(form);
		model.addAttribute(order);
		//後で注文完了画面にいけるようなメソッドにredirectする
		return "redirect:/order-item/finished-order";
	}
	
	/**
	 * 注文完了画面に遷移するメソッド.
	 * 
	 * @return 注文完了画面
	 */
	@RequestMapping("/finished-order")
	public String finishedOrder() {
		return "order_finished";
	}

}