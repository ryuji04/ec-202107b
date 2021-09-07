package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.User;
import com.example.form.AddItemCartForm;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderToppingRepository;
import com.example.service.AddItemCartService;

/**
 * 注文履歴から商品を購入するコントローラ.
 * 
 * @author hayato.saishu
 *
 */
@Controller
@RequestMapping("/restore")
public class RestoreByHistoryController {

	@Autowired
	private AddItemCartService addItemCartService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired 
	private OrderToppingRepository orderToppingRepository;
	
	@ModelAttribute
	public AddItemCartForm setUpForm() {
		return new AddItemCartForm();
	}
	
	/**
	 * 注文履歴から再度商品を注文する.
	 * 
	 * @param itemId 注文商品ID
	 * @param form　フォーム
	 * @param loginUser　ログインユーザー
	 * @return
	 */
	@RequestMapping("/add-item")
	public String add(Integer itemId, AddItemCartForm form, @AuthenticationPrincipal LoginUser loginUser) {
		User user = loginUser.getUser();
		OrderItem orderItem = orderItemRepository.findById(itemId);
		orderItem.setOrderToppingList(orderToppingRepository.findByOrderItemId(itemId));
		
		form.setItemId(orderItem.getItemId());
		form.setQuantity(orderItem.getQuantity());
		form.setSize(orderItem.getSize());
		List<Integer> toppingList = new ArrayList<>();
		for(OrderTopping orderTopping : orderItem.getOrderToppingList()) {
			toppingList.add(orderTopping.getToppingId());
		}
		form.setToppingList(toppingList);
		
		if (user == null) {
			// sessionにuser情報が入っていなかったら仮のsessionIDを発行してuseridとしてセットする
			// int dummyId = Integer.parseInt(session.getId());
			int dummyId = 1;
			addItemCartService.add(form, dummyId, 0);
		} else {
			addItemCartService.add(form, user.getId(), 0);
		}
		
		
		// 後でショッピングカート内一覧表示するメソッドにredirectするように直す
		return "redirect:/show-item-cart/cart";
	}
	
}
