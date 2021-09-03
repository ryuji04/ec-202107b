package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.service.ShowitemCartService;

@Controller
@RequestMapping("/show-item-cart")
public class ShowItemCartController {
	@Autowired
	private ShowitemCartService service;

	@RequestMapping("/cart")
	public String showItemCart(Integer userId, Model model) {
		System.out.println(userId);

		// status=0の商品を取得
		Order order = service.showItemCart(userId, 0);

		model.addAttribute("order", order);

		return "cart_list.html";
	}
}
