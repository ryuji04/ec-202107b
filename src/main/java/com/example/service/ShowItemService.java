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
	
	public List<List<Item>> showList(){
		List<List<Item>> totalItemList = new ArrayList<>();
		List<Item> divideItemList = new ArrayList<>();
		List<Item> itemList = itemRepository.findAll();
		int roopCount = 0;
		
		for(Item item : itemList) {
			divideItemList.add(item);
			roopCount++;
			if(roopCount % 3 == 0) {
				totalItemList.add(divideItemList);
				divideItemList = new ArrayList<>();
			}
		}
		return totalItemList;
	}
	
	public List<List<Item>> searchByLikeName(String name){
		List<List<Item>> totalItemList = new ArrayList<>();
		List<Item> divideItemList = new ArrayList<>();
		List<Item> itemList = itemRepository.findByLikeName(name);
		int roopCount = 0;
		
		for(Item item : itemList) {
			divideItemList.add(item);
			roopCount++;
			if(roopCount % 3 == 0) {
				totalItemList.add(divideItemList);
				divideItemList = new ArrayList<>();
			}
		}
		return totalItemList;
	}
}
