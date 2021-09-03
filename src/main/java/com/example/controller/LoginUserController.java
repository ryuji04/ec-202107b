package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUserForm;
import com.example.domain.User;
import com.example.service.LoginUserService;

/**
 * ログインをする為のコントローラークラス.
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("/login-user")
public class LoginUserController {

	@ModelAttribute
	public LoginUserForm setUpForm() {
		return new LoginUserForm();
	}

	@Autowired
	private LoginUserService loginUserService;

	@Autowired
	private HttpSession session;

	/**
	 * ログイン画面へ遷移.
	 * 
	 * @return　ログイン画面
	 */
	@RequestMapping("")
	public String toLogin() {
		return "login";
	}

	/**
	 * ログインチェックをするメソッド.
	 * 
	 * @param form ログイン情報のフォームクラス
	 * @param model エラー文を格納するためのリクエストスコープ
	 * 
	 * @return　メールアドレスとパスワードが正常⇒商品一覧に遷移
	 * 　　　　　　　メールアドレスまたはパスワードが異なる⇒ログイン画面に遷移
	 */
	@RequestMapping("login")
	public String loginUser(LoginUserForm form,Model model) {

		User user = loginUserService.login(form.getEmail(), form.getPassword());
		
		session.setAttribute("user", user);
		
		if (user == null) {
			model.addAttribute("error", "メールアドレスまたはパスワードが間違っています");
			return toLogin();
		}

		return "redirect:/show-item/all";

	}

	/**
	 * ログアウトをするメソッド.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/login-user";
	}
}
