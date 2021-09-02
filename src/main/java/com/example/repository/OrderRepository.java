package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;

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
		List<OrderTopping> orderToppingList = null;
		List<Topping> toppingList = null;
		int idNumber = 0;
		while (rs.next()) {
			int nowIdNumber = rs.getInt("id");
			if (idNumber != nowIdNumber) {
				Order order = new Order();
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
			if(rs.getInt("oi_id") != 0) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("oi_id"));
				orderItem.setItemId(rs.getInt("oi_item_id"));
				orderItem.setOrderId(rs.getInt("oi_order_id"));
				orderItem.setQuantity(rs.getInt("oi_quantity"));
				String size = rs.getString("oi_size");
				char[] c = size.toCharArray();
				orderItem.setSize(c[0]);
				orderToppingList = new ArrayList<>();
				orderItem.setOrderToppingList(orderToppingList);
				orderItemList.add(orderItem);
			}
			
			if(rs.getInt("ot_id") != 0 ) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("ot_id"));
				orderTopping.setToppingId(rs.getInt("ot_topping_id"));
				orderTopping.setOrderItemId(rs.getInt("ot_order_item_id"));
				orderToppingList.add(orderTopping);
			}
			
			if(rs.getInt("i_id") != 0) {
				Item item = new Item();
				item.setId(rs.getInt("i_id"));
				item.setName(rs.getString("i_name"));
				item.setDescription(rs.getString("i_description"));
				item.setPriceM(rs.getInt("i_price_m"));
				item.setPriceL(rs.getInt("i_price_l"));
				item.setImagePath(rs.getString("i_image_path"));
				item.setDeleted(rs.getBoolean("i_deleted"));
			}
			
			if(rs.getInt("t_id") != 0) {
				Topping topping = new Topping();
				topping.setId(rs.getInt("t_id"));
				topping.setName(rs.getString("t_name"));
				topping.setPriceM(rs.getInt("t_price_m"));
				topping.setPriceL(rs.getInt("t_price_l"));
				toppingList = new ArrayList<>();
				toppingList.add(topping);
			}
			idNumber = nowIdNumber;
		}
		return orderList;
	};
	
	/**
	 * 渡した注文情報を保存する.
	 * 
	 * @param order 注文情報
	 * @return 保存した注文情報
	 */
	public Order insert(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String sql = "INSERT INTO orders(user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, "
				+ "destination_address, destination_tel, delivery_time, payment_method) VALUES "
				+ "(:userId, :status, :totalPrice, :orderDate, :destinationName, :destinationEmail, :destinationZipcode, :destinationAddress, :destinationTel, "
				+ ":deliveryTime, :paymentMethod);";
		template.update(sql, param);
		return order;
	}
	
	/**
	 * ユーザーIDとステータスから注文情報を取得する.
	 * 
	 * @param userId ユーザーID
	 * @param status 注文ステータス
	 * @return 注文情報
	 */
	public Order findByUserIdAndStatus(Integer userId, Integer status) {
		//formかなんかで送信された情報に入っているuserIdとstatusを元にOrderがあるかどうか探し出す
		String sql = "SELECT id, user_id, status, total_price, order_date, destination_name, destination_email,"
				+ " destination_zipcode, destination_address, destination_tel, payment_method, user, order_item_list FROM "
				+ "orders WHERE user_id = :userId AND status = :status ORDER BY id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		List<Order> orderList = template.query(sql, param, ORDER_ROW_MAPPER);
		if (orderList.size() == 0) {
			return null;
		}
		return orderList.get(0);
	}
}
