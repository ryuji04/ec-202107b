package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemService;

@Controller
@RequestMapping("show-item")
public class ShowItemController {

	@Autowired
	private ShowItemService showItemService;

	@RequestMapping("all")
	public String showList(Model model) {
		List<List<Item>> itemList = showItemService.showList();
		model.addAttribute("itemList", itemList);

		return "item_list_coffee";
	}

	@RequestMapping("like-name")
	public String searchByLikeName(String name, Model model) {
		List<List<Item>> itemList = showItemService.searchByLikeName(name);
		if(itemList.size() == 0) {
			model.addAttribute("blankMessage", "該当する商品がありません");
			itemList = showItemService.showList();
		}
		model.addAttribute("itemList", itemList);
		return "item_list_coffee";
	}
}
