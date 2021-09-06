package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.repository.ItemRepository;
import com.example.repository.ToppingRepository;

/**
 * itemsテーブルとtoppingsテーブルを操作するService.
 * 
 * @author nayuta
 */
@Service
@Transactional
public class ShowItemDetailService {
	// ToppingRepositoryをインスタンス化
	@Autowired
	private ToppingRepository toppingRepository;
	// ItemRepositoryをインスタンス化
	@Autowired
	private ItemRepository itemRepository;

	/**
	 * idから商品の詳細情報を取得するメソッド.
	 * 
	 * @param id
	 * @return アイテム詳細情報
	 */
	public Item showItemDetailService(Integer id) {
		// アイテムを取得
		Item item = itemRepository.findById(id);

		// トッピングを全取得
		List<Topping> toppingList = new ArrayList<>();
		toppingList = toppingRepository.findAll();

		// itemの中のtoppingListに全取得したトッピングを格納
		item.setToppingList(toppingList);

		return item;
	}
}
