package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.AddItemCartService;

/**
 * カートに商品を追加するコントローラークラス.
 * 
 * @author okahikari
 *
 */
@Controller
@RequestMapping("/add-cart")
public class AddItemCartController {
	
	@Autowired
	private AddItemCartService addItemCartService;
	
	/**
	 * カートに商品を追加する.
	 * 
	 * @param form カートに商品を追加する際に使用するフォーム
	 * @return カート内一覧表示画面
	 */
}
