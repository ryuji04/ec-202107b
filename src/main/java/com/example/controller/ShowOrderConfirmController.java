package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.User;
import com.example.service.ShowitemCartService;

/**
 * 注文確認画面を操作するcontroller.
 * 
 * @author saisyuhayato
 */
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

		// orderのtotalPriceを取得
		int totalPrice = 0;
		for( OrderItem item : order.getOrderItemList()) {
			totalPrice += item.getSubTotal();
		}
		order.setTotalPrice(totalPrice);

		model.addAttribute("order", order);

		return "order_confirm";
	}
}
