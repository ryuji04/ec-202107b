package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.service.ShowOrderHistoryService;

/**
 * 注文履歴の情報を操作するコントローラ.
 * 
 * @author hayato.saishu
 *
 */
@Controller
@RequestMapping("/order-history")
public class ShowOrderHistoryController {

	@Autowired
	private ShowOrderHistoryService showOrderHistoryService;

	/**
	 * 注文履歴の一覧を表示する.
	 * 
	 * @param model     モデル
	 * @param loginUser ログインユーザー
	 * @return 注文履歴ページ
	 */
	@RequestMapping("/showList")
	public String showOrderHistory(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		int userId = loginUser.getUser().getId();

		List<Order> orderList = showOrderHistoryService.searchOrderHistory(userId);
		if (orderList.size() == 0) {
			model.addAttribute("blankMessage", "注文履歴が1件もありません");
		} else {
			model.addAttribute("orderList", orderList);
		}

		return "order_history";
	}

	/**
	 * 注文履歴の詳細を表示する.
	 * 
	 * @param model モデル
	 * @param id    ID
	 * @return 注文履歴の詳細ページ
	 */
	@RequestMapping("/showDetail")
	public String showOrderDetail(Model model, Integer id) {
		Order order = showOrderHistoryService.searchOrderHistoryDetail(id);
		model.addAttribute("order", order);

		return "order_history_detail";
	}
}