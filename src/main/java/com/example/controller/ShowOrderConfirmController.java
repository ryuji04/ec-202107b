package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.User;
import com.example.service.ShowitemCartService;

@Controller
@RequestMapping("/order-confirm")
public class ShowOrderConfirmController {

	@Autowired
	private ShowitemCartService showitemCartService;
	
	@RequestMapping("")
	public String searchById(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		User user = loginUser.getUser();

		// status=0の商品を取得
		Order order = showitemCartService.showItemCart(user.getId(), 0);

		model.addAttribute("order", order);

		return "order_confirm";
	}
}
