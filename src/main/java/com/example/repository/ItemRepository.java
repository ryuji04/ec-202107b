package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
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
	public List<Item> findAll() {
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
	public List<Item> findByLikeName(String name) {
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE name ILIKE :name ORDER BY id;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");

		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);

		return itemList;
	}

	/**
	 * 詳細商品情報を取得するメソッド.
	 * 
	 * @param id 商品ID
	 * @return １つの商品詳細情報
	 */
	public Item findById(Integer id) {
		Item item = new Item();

		// SQL文の作成
		String findByIdsql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE id = :id;";

		// プレースホルダに実数を格納
		SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

		// 実行
		item = template.queryForObject(findByIdsql, params, ITEM_ROW_MAPPER);

		return item;
	}

	/**
	 * アイテム情報を降順(価格)に並べ替える.
	 * 
	 * @return 降順(価格)に並んだアイテム情報リスト
	 */

	public List<Item> arrangeInDesc() {
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items ORDER BY price_m desc;";

		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);

		return itemList;
	}

	/**
	 * アイテム情報を昇順(価格)に並べ替える.
	 * 
	 * @return 昇順(価格)に並んだアイテム情報リスト
	 */
	public List<Item> arrangeInAsc() {
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items ORDER BY price_m;";

		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);

		return itemList;
	}

	/**
	 * アイテム情報を降順(価格)に並べ替える.
	 * 
	 * @return 降順(価格)に並んだアイテム情報リスト
	 */

	public List<Item> arrangeInDescByName(String name) {
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE name ILIKE :name  ORDER BY price_m desc;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");

		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);

		return itemList;
	}

	/**
	 * アイテム情報を昇順(価格)に並べ替える.
	 * 
	 * @return 昇順(価格)に並んだアイテム情報リスト
	 */
	public List<Item> arrangeInAscByName(String name) {
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE name ILIKE :name  ORDER BY price_m;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");

		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);

		return itemList;
	}
	
	//badgeテストの為のリポジトリ.
	public void csvInsert(Item item) {
		String sql
		="INSERT INTO items(id,name,description,price_m,price_l,image_path,deleted) VALUES( :id,:name ,:description,:priceM,:priceL,:imagePath,:deleted)";
	
		SqlParameterSource param
		=new BeanPropertySqlParameterSource(item);
		
		template.update(sql, param);
	}
	

}
