package com.example.repository;

import javax.swing.tree.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) ->{
		Item item = new Item();
		
	}
	
	public List<Item> findAll(){
		String sql = 
	}
}
