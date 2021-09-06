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
	
	@ModelAttribute
	public InqueryForm setUpForm() {
		return new InqueryForm();
	}
	
	@RequestMapping("")
	public String index() {
		return "inquery";
	}
	
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
