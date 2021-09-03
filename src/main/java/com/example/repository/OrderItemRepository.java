package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;

/**
 * order_itemsテーブルを操作するリポジトリ.
 * 
 * @author okahikari
 * 
 */
@Repository
public class OrderItemRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<OrderItem> ORDER_ITEM_ROW_MAPPER = (rs, i) -> {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(rs.getInt("id"));
		orderItem.setItemId(rs.getInt("item_id"));
		orderItem.setOrderId(rs.getInt("order_id"));
		orderItem.setQuantity(rs.getInt("quantity"));
		String size = rs.getString("size");
		char[] c = size.toCharArray();
		orderItem.setSize(c[0]);
		return orderItem;
	};

	/**
	 * 注文商品情報を挿入する.
	 * 
	 * @param orderItem 注文商品情報
	 * @return 挿入した注文商品
	 */
	public OrderItem insert(OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		String sql = "INSERT INTO order_items(item_id, order_id, quantity, size) VALUES (:itemId, :orderId, :quantity, :size);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnNames = { "id" };
		template.update(sql, param, keyHolder, keyColumnNames);
		orderItem.setId(keyHolder.getKey().intValue());
		return orderItem;
	}

	/**
	 * 注文商品情報を削除する.
	 * 
	 * @param id 削除する注文商品のID
	 */
	public void deleteById(Integer id) {
		String sql = "DELETE FROM order_items WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

	/**
	 * IDから注文商品情報の親の注文IDを取得する.
	 * 
	 * @param id 注文商品ID
	 * @return 注文ID情報
	 */
	public OrderItem findById(Integer id) {
		String sql = "SELECT id, item_id, order_id, quantity, size FROM order_items WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, param, ORDER_ITEM_ROW_MAPPER);
	}

	/**
	 * orderIdから注文商品情報の親の注文IDを取得する.
	 * 
	 * @param id 注文商品ID
	 * @return 注文ID情報
	 */
	public List<OrderItem> findByOrderId(Integer orderId) {
		String sql = "SELECT id, item_id, order_id, quantity, size FROM order_items WHERE order_id = :order_id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("order_id", orderId);
		List<OrderItem> orderItemList = template.query(sql, param, ORDER_ITEM_ROW_MAPPER);

		return orderItemList;
	}
}
