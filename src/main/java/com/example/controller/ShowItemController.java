package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.LoginUser;
import com.example.service.ShowItemService;

/**
 * 商品一覧の情報を操作するコントローラ.
 * 
 * @author hayato.saishu
 *
 */
@Controller
@RequestMapping("/show-item")
public class ShowItemController {

	@Autowired
	private ShowItemService showItemService;

	/**
	 * 商品を全件表示する.
	 * 
	 * @param model モデル
	 * @return 商品情報のリスト
	 */
	@RequestMapping("/all")
	public String showList(Model model,
			@org.springframework.security.core.annotation.AuthenticationPrincipal LoginUser loginUser) {
		List<List<Item>> itemList = showItemService.showList();
		model.addAttribute("itemList", itemList);
		if (loginUser != null) {
			model.addAttribute("userName", loginUser.getUser().getName() + "さんこんにちは");
		}

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

	/**
	 * 並び替えを行うメソッドを.
	 * 
	 * @param model       並び替えしたアイテムをリクエストスコープに入れる
	 * @param arrangeItem 降順(Mサイズ価格)または昇順(Mサイズ価格)を決める引数
	 * @return 並び替え後のアイテムリスト
	 */
	@RequestMapping("/sort-item")
	public String arrangeItem(Model model, String arrangeItem) {

		List<List<Item>> itemList;

		if ("1".equals(arrangeItem)) {
			itemList = showItemService.arrangeInDesc();
			model.addAttribute("itemList", itemList);
		} else if ("2".equals(arrangeItem)) {
			itemList = showItemService.arrangeInAsc();
			model.addAttribute("itemList", itemList);
		}

		return "item_list_coffee";
	}

}
