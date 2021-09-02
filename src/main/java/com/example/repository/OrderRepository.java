package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
	@Autowired
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
		String findByIdSql = "SELECT id, user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, destination_tel, delivery_time, payment_method FROM orders WHERE id=:id ORDER BY id;";

		// プレースホルダー埋め込み
		SqlParameterSource params = new MapSqlParameterSource().addValue("id", 1);

		// 実行
		Order order = template.queryForObject(findByIdSql, params, ORDER_ROW_MAPPER);

		return order;
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
		template.queryForObject(upDateSql, params, ORDER_ROW_MAPPER);
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
	/** 以上、削除の可能性あり */
}
