package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemDetailService;

/**
 * 商品の詳細情報を操作するコントローラー.
 * 
 * @author nayuta
 */
@Controller
@RequestMapping("show-item-detail")
public class ShowItemDetailController {
	// ShowItemDetailServiceをインスタンス化
	@Autowired
	private ShowItemDetailService showItemDetailService;

	@RequestMapping("")
	public String showItemDetail(Integer id, Model model) {
		
		System.out.println(id);
		
		Item item = showItemDetailService.showItemDetailService(id);

		model.addAttribute("item", item);

		return "item_detail.html";
	}
}
