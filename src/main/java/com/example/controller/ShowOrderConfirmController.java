package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.service.ShowOrderConfirmService;

@Controller
@RequestMapping("order-confirm")
public class ShowOrderConfirmController {

	@Autowired
	private ShowOrderConfirmService showOrderConfirmService;
	
	@RequestMapping("")
	public String searchById(Integer id, Model model) {
		Order order = showOrderConfirmService.searchById(id);
		model.addAttribute("order", order);
		
		return "order_confirm";
	}
}
