package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@Autowired
	private OrderService orderService;
	
	@ModelAttribute
	public OrderForm setUpOrderForm() {
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
	 * @return 注文完了画面
	 */
	@RequestMapping("/order")
	public String order(@Validated OrderForm form, BindingResult result, Model model) {
		Order order = orderService.findById(form.getId());
		
		if(result.hasErrors()) {
			return index(model);
		}
		
		// orderに情報変更後のorderを格納
		order = orderService.upDateOrder(form);
		model.addAttribute(order);
		return finishedOrder();
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