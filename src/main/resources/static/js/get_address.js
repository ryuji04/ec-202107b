/**
 * 住所を自動取得するJS.
 * 
 * @author nayuta
 */

'use strict';

$(function(){
	// 住所を自動取得するための非同期通信開始
	$('#get_address_btn').on('click', function(){
		$.ajax({
			url: 'http://zipcoda.net/api',
			dataType: 'jsonp',
			data: {
		        zipcode: $('#inputZipcode').val()
			},
			async: true		
		}).done(function(data){
		      // 検索成功時に実行
		      // コンソールに所得データ表示
		      console.dir(JSON.stringify(data));
		      $('#inputAddress').val(data.items[0].address);
		    }).fail(function (XMLHttpRequest, textStatus, errorThrow) {
		      // 失敗時にダイアログを表示
		      alert('正しい結果を得られませんでした。');
		      console.log('XMLHttpRequest : ' + XMLHttpRequest.status);
		      console.log('textStatus     : ' + textStatus);
		      console.log('errorThrown    : ' + errorThrown.message);
		});
	});
});
