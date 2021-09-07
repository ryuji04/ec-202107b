package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ログインをする為のコントローラークラス.
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("/login-user")
public class LoginUserController {


	/**
	 * ログイン画面へ遷移.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/to-login")
	public String toLogin(Model model, @RequestParam(required = false) String error) {

		System.err.println("login error:" + error);
		if (error != null) {
			System.err.println("login failed");
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です");
		}
		return "login";
	}

	/**
	 * 遷移元のURLからログイン後の遷移先を指定.
	 * 
	 * @param request リクエスト
	 * @return 遷移先のパス
	 */
	@RequestMapping("/referer-check")
	public String refererCheck(HttpServletRequest request) {
		String url = request.getHeader("referer");
		if ("http://localhost:8080/show-item-cart/cart".equals(url)) {
			return "redirect:/order/confirm";
		} else {
			return "redirect:/";
		}
	}
	

}
