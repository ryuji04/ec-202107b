package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

/**
 * ordersテーブルを操作するservice.
 * 
 * @author nayuta
 */
/**
 * @author nayuta
 *
 */
@Service
@Transactional
public class ShowitemCartService {
	@Autowired
	private OrderRepository repository;

	/**
	 * statusが0のデータを取得するメソッド.
	 * 
	 * @param userId ユーザーID
	 * @return statusが0のorder
	 */
	public Order showItemCart(Integer userId, Integer status) {
		/**
		 * 1.userIdでorderを全取得. 2.statusが0のものだけをif文で取得.
		 * 
		 * ※SQL文が2回必要…？
		 */

		//
		if (status == 0) {
			Order order = new Order();

			order = repository.findByUserIdAndStatus(userId, status);

			return order;
		}

		return null;
	}

	/** 合計金額を計算するメソッドも必要…？ */

}
