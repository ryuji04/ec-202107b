package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	@RequestMapping("")
	public String index() {
		return "inquery";
	}
	
	@RequestMapping("inquerySubmit")
	public String inquerySubmit(InqueryForm form,Model model) {
		Inquery inqueryInformation=new Inquery();
		BeanUtils.copyProperties(form, inqueryInformation);
		model.addAttribute("name",inqueryInformation.getName() );
		model.addAttribute("mail",inqueryInformation.getMail() );
		model.addAttribute("tell",inqueryInformation.getTell() );
		model.addAttribute("inquery",inqueryInformation.getInquery() );
		
		return "inquery_finished";
	}
}
