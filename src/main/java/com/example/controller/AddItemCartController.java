package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.AddItemCartForm;
import com.example.service.AddItemCartService;

/**
 * カートに商品を追加するコントローラークラス.
 * 
 * @author okahikari
 *
 */
@Controller
@RequestMapping("/add-item-cart")
public class AddItemCartController {

	@Autowired
	private HttpSession session;

	@Autowired
	private AddItemCartService addItemCartService;

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public AddItemCartForm setUpForm() {
		return new AddItemCartForm();
	}

	/**
	 * カートに商品を追加する.
	 * 
	 * @param form カートに商品を追加する際に使用するフォーム
	 * @return カート内一覧表示画面
	 */
	@RequestMapping("/add-item")
	public String add(AddItemCartForm form) {
		// sessionからUser情報とってきてuserのidを取り出す
		User user = (User) session.getAttribute("user");
		if (user == null) {
			// sessionにuser情報が入っていなかったら仮のsessionIDを発行してuseridとしてセットする
			// int dummyId = Integer.parseInt(session.getId());
			int dummyId = 1;
			addItemCartService.add(form, dummyId, 0);
		} else {
			addItemCartService.add(form, user.getId(), 0);
			System.out.println(user.getId());
		}
		return "redirect:/show-item-cart/cart";
	}
}
