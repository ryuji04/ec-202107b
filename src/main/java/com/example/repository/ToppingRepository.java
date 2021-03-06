package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Topping;

/**
 * toppingsテーブルを操作するRepository.
 * 
 * @author nayuta
 */
@Repository
public class ToppingRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	// ラムダ式でROWMAPPERを定義
	private static final RowMapper<Topping> TOPPING_ROW_MAPPER = (rs, i) -> {
		Topping topping = new Topping();

		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceM(rs.getInt("price_m"));
		topping.setPriceL(rs.getInt("price_l"));

		return topping;
	};

	/**
	 * ToppingItemを全取得.
	 * 
	 * @return
	 */
	public List<Topping> findAll() {
		List<Topping> toppingList = new ArrayList<>();

		// SQL文作成
		String sql = "SELECT id, name, price_m, price_l FROM toppings ORDER BY id;";

		// 実行
		toppingList = template.query(sql, TOPPING_ROW_MAPPER);

		return toppingList;
	}

	/**
	 * idからトッピングを取得.
	 * 
	 * @return
	 */
	public Topping findById(Integer id) {
		Topping topping = new Topping();

		// SQL文作成
		String sql = "SELECT id, name, price_m, price_l FROM toppings WHERE id = :id;";

		// プレースホルダー埋め込み
		SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

		// 実行
		topping = template.queryForObject(sql, params, TOPPING_ROW_MAPPER);

		return topping;
	}
}
