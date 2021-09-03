package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.service.ShowitemCartService;

@Controller
@RequestMapping("/show-item-cart")
public class ShowItemCartController {
	@Autowired
	private ShowitemCartService service;

	@Autowired
	private HttpSession session;

	@RequestMapping("/cart")
	public String showItemCart(Model model) {
		User user = (User) session.getAttribute("user");

		// status=0の商品を取得
		Order order = service.showItemCart(user.getId(), 0);

		model.addAttribute("order", order);

		return "cart_list.html";
	}
}
