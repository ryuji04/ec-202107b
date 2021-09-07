package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Inquery;
import com.example.form.InqueryForm;

/**
 * お問い合わせ情報のコントローラークラス
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("inquery")
public class InqueryController {
	
	/**
	 * パスが呼ばれる前にお問い合わせ情報のフォームを作成する.
	 * 
	 * @return お問い合わせ情報のフォーム
	 */
	@ModelAttribute
	public InqueryForm setUpForm() {
		return new InqueryForm();
	}
	
	/**
	 * お問い合わせ画面へ遷移.
	 * 
	 * @return お問い合わせ画面
	 */
	@RequestMapping("")
	public String index() {
		return "inquery";
	}
	
	/**
	 * お問い合わせ情報を入力するメソッド.
	 * 
	 * @param form お問い合わせ情報フォーム
	 * @param result　エラーを格納する
	 * @param model　リクエストスコープ
	 * @return　お問い合わせ完了画面表示
	 */
	@RequestMapping("inquerySubmit")
	public String inquerySubmit(@Validated InqueryForm form,BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			return index();
		}
		
		Inquery inqueryInformation=new Inquery();
		BeanUtils.copyProperties(form, inqueryInformation);
		model.addAttribute("name",inqueryInformation.getName() );
		model.addAttribute("mail",inqueryInformation.getMail() );
		model.addAttribute("tell",inqueryInformation.getTell() );
		model.addAttribute("inquery",inqueryInformation.getInquery() );
		
		return "inquery_finished";
	}
}
