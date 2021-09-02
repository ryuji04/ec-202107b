package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

@Service
@Transactional
public class ShowItemService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品一覧を表示する.
	 * 
	 * @return アイテム情報のリスト
	 */
	public List<List<Item>> showList() {
		List<List<Item>> totalItemList = new ArrayList<>();
		List<Item> divideItemList = new ArrayList<>();
		List<Item> itemList = itemRepository.findAll();
		
		listItem(totalItemList, divideItemList, itemList);
		return totalItemList;
	}

	/**
	 * 名前検索に当てはまる商品一覧を表示する.
	 * 
	 * @param name 商品名
	 * @return アイテム情報のリスト
	 */
	public List<List<Item>> searchByLikeName(String name) {
		List<List<Item>> totalItemList = new ArrayList<>();
		List<Item> divideItemList = new ArrayList<>();
		List<Item> itemList = itemRepository.findByLikeName(name);

		listItem(totalItemList, divideItemList, itemList);
		return totalItemList;
	}

	/**
	 * アイテム商品を降順に並び替える.
	 * 
	 * @return 並び替え後のアイテム商品リスト
	 */
	public List<List<Item>> arrangeInDesc() {
		List<List<Item>> totalItemList = new ArrayList<>();
		List<Item> divideItemList = new ArrayList<>();
		List<Item> itemList = itemRepository.arrangeInDesc();
		
		listItem(totalItemList, divideItemList, itemList);
		return totalItemList;
	}

	/**
	 * アイテム商品を昇順に並び替える.
	 * 
	 * @return 並び替え後のアイテム商品リスト
	 */
	public List<List<Item>> arrangeInAsc() {
		List<List<Item>> totalItemList = new ArrayList<>();
		List<Item> divideItemList = new ArrayList<>();
		List<Item> itemList = itemRepository.arrangeInAsc();
		
		listItem(totalItemList, divideItemList, itemList);
		return totalItemList;
	}
	
	
	//商品を3つずつリストに格納するメソッド
	public void listItem(List<List<Item>> totalItemList, List<Item> divideItemList, List<Item> itemList) {
		for (int i = 0; i < itemList.size(); i++) {
			divideItemList.add(itemList.get(i));
			
			if ((i + 1) % 3 == 0) {
				totalItemList.add(divideItemList);
				divideItemList = new ArrayList<>();
			}
			
			if (itemList.size() == i + 1) {
				totalItemList.add(divideItemList);
				break;
			}
		}
	}
	
}