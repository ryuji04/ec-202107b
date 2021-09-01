package com.example.repository;

import java.util.List;

import javax.swing.tree.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) ->{
		Item item = new Item();
		
		return item;
	};
	
	public List<Item> findAll(){
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items ORDER BY id;";
		
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		
		return itemList;
	}
	
	public List<Item> findByLikeName(String name){
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE name　LIKE :name ORDER BY id;"; 
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		
		return itemList;
	}
}
