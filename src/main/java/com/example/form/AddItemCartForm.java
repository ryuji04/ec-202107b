package com.example.form;

import java.util.List;

/**
 * カートに商品を入れる時に使用するクラス.
 * 
 * @author okahikari
 * 
 */
public class AddItemCartForm {

	/** 商品ID */
	private Integer itemId;
	/** 数量 */
	private Integer quantity;
	/** サイズ */
	private Character size;
	/** トッピングリスト */
	private List<Integer> toppingList;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public List<Integer> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Integer> toppingList) {
		this.toppingList = toppingList;
	}

	@Override
	public String toString() {
		return "AddItemCartForm [itemId=" + itemId + ", quantity=" + quantity + ", size=" + size + ", toppingList="
				+ toppingList + "]";
	}

}
