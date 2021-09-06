package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.OrderItemRepository;
import com.example.repository.OrderToppingRepository;

/**
 * 削除する注文商品情報を操作するサービス.
 * 
 * @author okahikari
 *
 */
@Service
@Transactional
public class DeleteItemCartService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	/**
	 * カートの中の商品を削除する.
	 * 
	 * @param orderItemId 削除する注文商品情報
	 */
	public void deleteById(Integer orderItemId) {
		// 依存関係があるためorderItemIdをorderItemIdカラムとしてもつOrderToppingから削除する
		orderToppingRepository.deleteById(orderItemId);

		orderItemRepository.deleteById(orderItemId);	}
}
