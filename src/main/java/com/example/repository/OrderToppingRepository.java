package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderTopping;

/**
 * order_toppingsテーブルを操作するリポジトリ.
 * 
 * @author okahikari
 * 
 */
@Repository
public class OrderToppingRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<OrderTopping> ORDER_TOPPING_ROW_MAPPER
	= new BeanPropertyRowMapper<>(OrderTopping.class);
	
	/**
	 * 注文トッピング情報を挿入する.
	 * 
	 * @param orderTopping 注文トッピング情報
	 */
	public void insert(OrderTopping orderTopping) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderTopping);
		String sql = "INSERT INTO order_toppings(topping_id, order_item_id) VALUES (:toppingId, :orderItemId);";
		template.update(sql, param);
	}
	
	/**
	 * 注文トッピング情報を削除する.
	 * 
	 * @param id 削除する注文トッピングのID
	 */
	public void deleteById(Integer id) {
		String sql = "DELETE FROM order_toppings WHERE order_item_id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
}
