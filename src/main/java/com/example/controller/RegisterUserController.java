package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.RegisterUserForm;
import com.example.service.RegisterUserService;

@Controller
@RequestMapping("/register-user")
public class RegisterUserController {
	
	@Autowired
	private RegisterUserService registerUserService;
	
	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public RegisterUserForm setUpRegisterUserForm() {
		return new RegisterUserForm();
	}
	
	/**
	 * 管理者登録画面を出力します.
	 * 
	 * @return 管理者登録画面
	 */
	@RequestMapping("/to-insert")
	public String toInsert() {
		return "register_user";
	}

	/**
	 * 管理者情報を登録します.
	 * 
	 * @param form ユーザー登録情報フォーム
	 * @return ログイン画面へリダイレクト
	 */
	@RequestMapping("/insert")
	public String insert(@Validated RegisterUserForm form, BindingResult result) {
		
		// パスワード確認
		if(!form.getPassword().equals(form.getConfirmPassword())){
			result.rejectValue("password", "", "パスワードが一致していません");
			result.rejectValue("confirmPassword", "", "");
		}

		// メールアドレスが重複している場合の処理
		User existUser = registerUserService.findByEmail(form.getEmail());
		if(existUser != null){
			result.rejectValue("email", "", "そのメールアドレスは既に登録されています");
		}
		
		// エラーが一つでもあれば入力画面に戻る
		if (result.hasErrors()) {
			  return toInsert();
		}
		
		User user = new User();
		// フォームからドメインにプロパティ値をコピー
		BeanUtils.copyProperties(form, user);
		
		// 登録処理
		registerUserService.insert(user);
		return "redirect:/login-user";
	}
}