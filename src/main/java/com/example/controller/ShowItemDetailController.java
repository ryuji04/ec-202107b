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
@RequestMapping("/show-item-detail")
public class ShowItemDetailController {
	// ShowItemDetailServiceをインスタン
	@Autowired
	private ShowItemDetailService showItemDetailService;

	/**
	 * 商品の詳細情報取得.
	 * 
	 * @param id 商品ID
	 * @param model Requestスコープ
	 * @return 商品の詳細情報
	 */
	@RequestMapping("/detail")
	public String showItemDetail(Integer id, Model model) {
		Item itemDetail = showItemDetailService.showItemDetailService(id);

		model.addAttribute("itemDetail", itemDetail);

		return "item_detail.html";
	}
	
	
}
