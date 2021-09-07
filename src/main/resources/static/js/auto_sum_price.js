/**
 * 金額を自動計算するJavaScript.
 */

'use strict';

$(function() {
	// 初期値
	calc_price();

	// サイズ変更
	$('.item_size').on('change', function() {
		calc_price();
	});

	// トッピング追加・削除
	$('.topping_choice').on('change', function() {
		calc_price();
	});

	// 注文数変更
	$('#order_count').on('change', function() {
		calc_price();
	});

	function calc_price() {
		/** 商品のサイズ */
		let size = $('.item_size:checked').val();
		/** トッピングの数 */
		let topping_count = $('#topping input:checkbox:checked').length;
		/** 商品の注文数 */
		let item_count = $('#order_count option:selected').val();

		/** 商品の合計金額 */
		let item_price = 0;
		/** トッピングの値段 */
		let topping_price = 0;

		// sizeによって金額を変更
		if (size == 'M') {
			// 表示されている値段を取得
			let price_m_String = $("#price_m").text();
			// 取得した数字をString -> int型へ
			let price_m = Number(price_m_String);

			item_price = price_m;
			topping_price = 200 * topping_count;
		} else {
			// 表示されている値段を取得
			let price_l_String = $("#price_l").text();
			// 取得した数字をString -> int型へ
			let price_l = Number(price_l_String);

			item_price = price_l;
			topping_price = 300 * topping_count;
		}

		/** 合計金額 */
		let total_price = (item_price + topping_price) * item_count;

		// 合計金額をセット
		$("#total-price").text(total_price);
	}
});