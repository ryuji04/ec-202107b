package com.example.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Order;

/**
 * ordersテーブルを操作するRepository.
 * 
 * @author nayuta
 */
@Repository
public class OrderRepository {
	private NamedParameterJdbcTemplate template;

	// ラムダ式でRowMapperを定義
	public static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
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

		return order;
	};

	/**
	 * 注文前のOrderを取得するメソッド.
	 * 
	 * @param id ID
	 * @return 注文前のOrder
	 */
	public Order findById(Integer id) {
		// SQL文作成
		String findByIdSql = "SELECT id,user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method"
				+ " FROM orders WHERE id = :id; ORDER BY id DESC";

		// プレースホルダー埋め込み
		SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

		// 実行
		Order order = template.queryForObject(findByIdSql, params, ORDER_ROW_MAPPER);

		return order;
	}

}
