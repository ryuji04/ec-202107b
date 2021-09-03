package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
				order.setUserId(rs.getInt("user_id"));
				order.setStatus(rs.getInt("status"));
				order.setTotalPrice(rs.getInt("total_price"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setDestinationName(rs.getString("destination_name"));
				order.setDestinationEmail(rs.getString("destination_email"));
				order.setDestinationZipcode(rs.getString("destination_zipcode"));
				order.setDestinationAddress(rs.getString("destination_address"));
				order.setDestinationTel(rs.getString("destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("delivery_time"));
				order.setPaymentMethod(rs.getInt("payment_method"));
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
				item.setDescription(rs.getString("i_desctiption"));
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
		String sql = "INSERT INTO orders(user_id, status, total_price) VALUES (:userId, :status, :totalPrice);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnNames = {"id"};
		template.update(sql, param, keyHolder, keyColumnNames);
		order.setId(keyHolder.getKey().intValue());
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
		String sql = "SELECT o.id, o.user_id, o.status, o.total_price, o.order_date, o.destination_name, o.destination_email, o.destination_zipcode, "
				+ "o.destination_address, o.destination_tel, o.delivery_time, o.payment_method, oi.id oi_id, oi.item_id oi_item_id, oi.order_id oi_order_id,"
				+ " oi.quantity oi_quantity, oi.size oi_size, i.id i_id, i.name i_name, i.description i_desctiption, i.price_m i_price_m, i.price_l i_price_l,"
				+ " i.image_path i_image_path, i.deleted i_deleted,  ot.id ot_id, ot.topping_id ot_topping_id, ot.order_item_id ot_order_item_id, t.id t_id,"
				+ " t.name t_name, t.price_m t_price_m, t.price_l t_price_l FROM orders AS o LEFT OUTER JOIN order_items AS oi ON o.id = oi.order_id"
				+ " JOIN items AS i ON oi.item_id = i.id JOIN order_toppings AS ot ON oi.id = ot.order_item_id JOIN toppings AS t ON t.id = ot.topping_id"
				+ " WHERE user_id = :userId AND status = :status ORDER BY o.id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		List<Order> orderList = template.query(sql, param, ORDER_ROW_MAPPER);
		if (orderList.size() == 0) {
			return null;
		}
		return orderList.get(0);
	}

	/**
	 * 注文前のOrderを取得するメソッド.
	 * 
	 * @param id ID
	 * @return 注文前のOrder
	 */
	public Order findById(Integer id) {
		// SQL文作成
		String findByIdSql = "SELECT id, user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, destination_tel, delivery_time, payment_method FROM orders WHERE id=:id ORDER BY id;";

		// プレースホルダー埋め込み
		SqlParameterSource params = new MapSqlParameterSource().addValue("id", 1);

		// 実行
		List<Order> orderList = template.query(findByIdSql, params, ORDER_ROW_MAPPER);

		return orderList.get(0);
	}

	/**
	 * 注文画面の入力情報を格納.
	 * 
	 * @param order 注文画面の入力情報
	 */
	public void upDate(Order order) {
		// SQL文作成
		String upDateSql = "UPDATE orders SET "
				+ "destination_name=:destination_name, destination_email=:destination_email, destination_zipcode=:destination_zipcode, destination_address=:destination_address, "
				+ "destination_tel=:destination_tel, delivery_time=:delivery_time, payment_method=:payment_method;";

		// プレースホルダー埋め込み
		SqlParameterSource params = new BeanPropertySqlParameterSource(order);

		// 実行
		template.update(upDateSql, params);
	}

	/** 以下、削除の可能性あり */
	/**
	 * Order_statusを1(未入金)にする.
	 */
	public void upDateStatus1() {
		// SQL文作成
		String upDateStatus1Sql = "UPDATE orders SET status = 1;";

		// 実行
		template.query(upDateStatus1Sql, ORDER_ROW_MAPPER);
	}

	/**
	 * Order_statusを2(入金済)にする.
	 */
	public void upDateStatus2() {
		// SQL文作成
		String upDateStatus1Sql = "UPDATE orders SET status = 2;";

		// 実行
		template.query(upDateStatus1Sql, ORDER_ROW_MAPPER);
	}
}
