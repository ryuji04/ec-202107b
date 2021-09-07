'use strict'

$(function(){
	console.log($('.select').val());
	$('.select').on('change',function(){
		if($('.select').val()=="0"){
			console.log($('.select').val());
		}else if($('.select').val()=="1"){
			
			console.log($('.select').val());
		}else if($('.select').val()=="2"){
			console.log($('.select').val());
		}
	})
})

/** $(function(){
	let value=$('.select').val();
	console.log(value);
})*/

/**console.log($('.select').val()); */