/**
 * 金額を自動計算するJavaScript.
 */

'use strict';

$(function() {
	// 初期値
	calc_price();

	// サイズを変更した時
	$('.size').on('change', function() {
		calc_price();
	});

	// トッピングを追加・削除した時
	$('.topping_choice').on('change', function() {
		calc_price();
	});

	// 注文個数を変更した時
	$('#order_count').on('change', function() {
		calc_price();
	});

	// 価格計算メソッド.
	function calc_price() {
		/** size(M or L) */
		let size = $('.size:checked').val();
		/** トッピングの数 */
		let topping_count = 3//$('#topping input:checkbox:checked').length;
		/** 注文個数 */
		let order_count = $('#order_count option:selected').val();

		/** sizeの値段 */
		let size_price = 0;
		/** トッピングの値段 */
		let topping_price = 0;

		// 確認用
		console.log(size);
		// 確認用
		// 確認用
		console.log(topping_count);
		// 確認用
		// 確認用
		console.log(order_count);
		// 確認用

		if (size == 'M') {
			size_price = 480;
			topping_price = topping_count * 200;
		} else {
			size_price = 700;
			topping_price = topping_count * 300;
		}

		// 合計金額
		let total_price = (size_price + topping_price) * order_count;

		// 合計金額を表示
		$('#total-price').text(total_price.toLocaleString());
	}
});
