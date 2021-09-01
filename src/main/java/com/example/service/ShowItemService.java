package com.example.service;

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
	
	public List<Item> showList(){
		List<Item> itemList = itemRepository.findAll();
		return itemList;
	}
	
	public List<Item> searchByLikeName(String name){
		List<Item> itemList = itemRepository.findByLikeName(name);
		
		return itemList;
	}
}
