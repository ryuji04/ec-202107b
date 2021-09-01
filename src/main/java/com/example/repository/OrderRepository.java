package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.User;

/**
 * ordersテーブルを操作するリポジトリ.
 * 
 * @author okahikari
 * 
 */
@Repository
public class OrderRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final ResultSetExtractor<List<Order>> ORDER_ROW_MAPPER
	= (rs) -> {
		List<Order> orderList = new ArrayList<>();
		List<OrderItem> orderItemList = null;
		int idNumber = 0;
		while (rs.next()) {
			int nowIdNumber = rs.getInt("id");
			if (idNumber != nowIdNumber) {
				Order order = new Order();
				// OrderのuserId使ってOrderのドメインにUserの値詰めたい
				// User user = new User();
				// order.setUser();
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("userId"));
				order.setStatus(rs.getInt("status"));
				order.setTotalPrice(rs.getInt("totalPrice"));
				order.setOrderDate(rs.getDate("orderDate"));
				order.setDestinationName(rs.getString("destinationName"));
				order.setDestinationEmail(rs.getString("destinationEmail"));
				order.setDestinationZipcode(rs.getString("destinationZipcode"));
				order.setDestinationAddress(rs.getString("destinationAddress"));
				order.setDestinationTel(rs.getString("destinationTel"));
				order.setDeliveryTime(rs.getTimestamp("deliveryTime"));
				order.setPaymentMethod(rs.getInt("paymentMethod"));
				orderItemList = new ArrayList<>();
				order.setOrderItemList(orderItemList);
				orderList.add(order);
			}
			if(rs.getInt("order_item_id") != 0) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("oi_id"));
				orderItem.setItemId(rs.getInt("oi_item_id"));
				orderItem.setOrderId(rs.getInt("oi_order_id"));
				orderItem.setQuantity(rs.getInt("oi_quantity"));
				// 注文商品のサイズをCharacter型でsetしたいけどキャストできないしgetString()しかない
				// orderItem.setSize((Character)(rs.getString("oi_size")));
			}
		}
		return orderList;
	};
}
