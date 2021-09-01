package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

@Repository
public class ItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) ->{
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDeleted(rs.getBoolean("deleted"));
		
		return item;
	};
	
	/**
	 * アイテム情報を全件表示します.
	 * 
	 * @return アイテム情報のリスト
	 */
	public List<Item> findAll(){
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items ORDER BY id;";
		
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		
		return itemList;
	}
	
	/**
	 * 名前検索でアイテム情報を表示する.
	 * 
	 * @param name 名前
	 * @return アイテム情報のリスト
	 */
	public List<Item> findByLikeName(String name){
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE name LIKE :name ORDER BY id;"; 
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		
		return itemList;
	}
}
