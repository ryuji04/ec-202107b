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

//	@ModelAttribute
//	public LoginUserForm setUpForm() {
//		return new LoginUserForm();
//	}

//	@Autowired
//	private LoginUserService loginUserService;
//
//	@Autowired
//	private HttpSession session;

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
			return "redirect:/show-item/all";
		}
	}
	// spring securityにログイン処理を任せるため以下をコメントアウト
//
//	/**
//	 * ログインチェックをするメソッド.
//	 * 
//	 * @param form ログイン情報のフォームクラス
//	 * @param model エラー文を格納するためのリクエストスコープ
//	 * 
//	 * @return　メールアドレスとパスワードが正常⇒商品一覧に遷移
//	 * 　　　　　　　メールアドレスまたはパスワードが異なる⇒ログイン画面に遷移
//	 */
//	@RequestMapping("/login")
//	public String loginUser(LoginUserForm form,Model model) {
//
//		User user = loginUserService.login(form.getEmail(), form.getPassword());
//		
//		session.setAttribute("user", user);
//		
//		if (user == null) {
//			model.addAttribute("error", "メールアドレスまたはパスワードが間違っています");
//			return toLogin();
//		}
//
//		return "/show-item/all";
//
//	}
//
//	/**
//	 * ログアウトをするメソッド.
//	 * 
//	 * @return ログイン画面
//	 */
//	@RequestMapping("/logout")
//	public String logout() {
//		session.invalidate();
//		return "redirect:/login-user";
//	}

// 5xx画面表示のtest用
	@RequestMapping("test")
	public void test() {
		System.out.println("エラー開始");
		int i = 2 % 0;
		System.out.println(i);
		System.out.println("エラー終了");
	}

}
