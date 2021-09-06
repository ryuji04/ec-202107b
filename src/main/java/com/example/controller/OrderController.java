package com.example.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.User;
import com.example.form.OrderForm;
import com.example.service.OrderService;
import com.example.service.ShowitemCartService;

/**
 * ordersテーブルを操作するcontroller.
 * 
 * @author nayuta, okahikari
 */
@Controller
@RequestMapping("/order-item")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ShowitemCartService showItemCartService;

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public OrderForm setUpOrderForm() {
		return new OrderForm();
	}

	@RequestMapping("/index")
	public String index(Model model) {
		return "order_confirm.html";
	}

	/**
	 * 注文をする.
	 * 
	 * @param form
	 * @return 注文完了画面
	 */
	@RequestMapping("/order")
	public String order(@Validated OrderForm form, BindingResult result, RedirectAttributes redirectAttributes, Model model,
			@AuthenticationPrincipal LoginUser loginUser) {
		Order order = orderService.findById(form.getId());

		// 注文日時
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		order.setOrderDate(date);

		// 注文日時用のtimestampオブジェクトを用意
		Timestamp orderTime = new Timestamp(date.getTime());

		// ユーザー配達希望日時
		String deliveryStrDate = form.getDeliveryDate();
		Date deliveryDate = null;
		try {
			deliveryDate = dateFormat.parse(deliveryStrDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int hour = Integer.parseInt(form.getDeliveryTime());

		// Orderの配達時間用のtimestampオブジェクトを用意
		Timestamp timestamp = new Timestamp(deliveryDate.getTime());
		timestamp.setHours(hour);
		order.setDeliveryTime(timestamp);

		// 日時のエラー追加
		if ((timestamp.getTime() - orderTime.getTime()) < 10800000) {
			result.rejectValue("deliveryDate", "", "今から3時間後の日時をご入力ください");
		}

		if (result.hasErrors()) {
			User user = loginUser.getUser();

			// status=0の商品を取得
			Order returnOrder = showItemCartService.showItemCart(user.getId(), 0);

			// orderのtotalPriceを取得
			int totalPrice = 0;
			for( OrderItem orderItem : returnOrder.getOrderItemList()) {
				totalPrice += orderItem.getSubTotal();
			}
			returnOrder.setTotalPrice(totalPrice);

			model.addAttribute("order", returnOrder);

			return "order_confirm";
		}

		// orderに情報変更後のorderを格納
		order = orderService.upDateOrder(form, model);
		model.addAttribute(order);
		return "redirect:/order-item/finished-order";
	}

	/**
	 * 注文完了画面に遷移するメソッド.
	 * 
	 * @return 注文完了画面
	 */
	@RequestMapping("/finished-order")
	public String finishedOrder() {
		return "order_finished";
	}

}