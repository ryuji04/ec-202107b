package com.example.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.LoginUser;
import com.example.form.SortItemForm;
import com.example.service.ShowItemService;

@Controller
@RequestMapping("/")
public class ShowItemController {

	@ModelAttribute
	public SortItemForm setUpForm() {
		return new SortItemForm();
	}

	@Autowired
	private ShowItemService showItemService;

	/**
	 * 商品を全件表示する.
	 * 
	 * @param model モデル
	 * @return 商品情報のリスト
	 */
	@RequestMapping("")
	public String showList(Model model, SortItemForm form,
			@org.springframework.security.core.annotation.AuthenticationPrincipal LoginUser loginUser) {

		List<List<Item>> itemList = showItemService.showList();
		model.addAttribute("itemList", itemList);
		if (loginUser != null) {
			model.addAttribute("userName", loginUser.getUser().getName() + "さんこんにちは");
		}

		// 並び替えに機能を実装する為の記述をします
		Map<Integer, String> itemMap = new LinkedHashMap<>();
		itemMap.put(0, "---");
		itemMap.put(1, "料金の高い順");
		itemMap.put(2, "料金の安い順");

		model.addAttribute("itemMap", itemMap);

		System.out.println("itemMap" + itemMap);

		if ("1".equals(form.getArrangeItem())) {
			itemList = showItemService.arrangeInDesc();
			model.addAttribute("itemList", itemList);
		} else if ("2".equals(form.getArrangeItem())) {
			itemList = showItemService.arrangeInAsc();
			model.addAttribute("itemList", itemList);
		} else {
			itemList = showItemService.showList();
			model.addAttribute("itemList", itemList);
		}
		System.out.println("itemMap" + itemMap);
		return "item_list_coffee";
	}

	/**
	 * 名前検索により商品情報を表示する.
	 * 
	 * @param name  商品名
	 * @param model モデル
	 * @return 商品情報のリスト
	 */
	@RequestMapping("/like-name")
	public String searchByLikeName(String name, Model model) {
		List<List<Item>> itemList = showItemService.searchByLikeName(name);
		if (itemList.size() == 0) {
			model.addAttribute("blankMessage", "該当する商品がありません");
			itemList = showItemService.showList();
		}
		model.addAttribute("itemList", itemList);
		return "item_list_coffee";
	}

}
