package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.DeleteItemCartService;

/**
 * カートの商品を削除するコントローラークラス.
 * 
 * @author okahikari
 *
 */
@Controller
@RequestMapping("/delete-item-cart")
public class DeleteItemCartController {

	@Autowired
	private DeleteItemCartService deleteItemCartService;

	/**
	 * カートの商品を削除する.
	 * 
	 * @param orderItemId orderID
	 * @return カート内商品一覧表示画面
	 */
	@RequestMapping("/delete-item")
	public String deleteById(Integer id) {
		deleteItemCartService.deleteById(id);
		return "redirect:/show-item-cart/cart";
	}
}
