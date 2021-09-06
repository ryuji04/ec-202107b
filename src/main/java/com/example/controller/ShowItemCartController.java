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
 * カート内表示機能を操作するcontroller.
 * 
 * @author nayuta, okahikari
 */
@Controller
@RequestMapping("/show-item-cart")
public class ShowItemCartController {
	@Autowired
	private ShowitemCartService service;

	/**
	 * @param model
	 * @param loginUser
	 * @return
	 */
	@RequestMapping("/cart")
	public String showItemCart(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		User user;
		if (loginUser.getUser() != null) {
			user = loginUser.getUser();
		} else {
			return "/login-user/to-login";
		}

		// status=0の商品を取得
		Order order = service.showItemCart(user.getId(), 0);
		if (order == null) {
			model.addAttribute("blankMessage", "商品が1件もありません");
		} else {
			int totalPrice = 0;
			for (OrderItem item : order.getOrderItemList()) {
				totalPrice += item.getSubTotal();
			}
			order.setTotalPrice(totalPrice);
			model.addAttribute("order", order);
		}
		// orderのtotalPriceを取得

		return "cart_list.html";
	}
}
