package com.example.domain;

import java.util.List;

/**
 * 注文商品情報を表すドメイン.
 * 
 * @author okahikari
 * 
 */
public class OrderItem {

	/** ID */
	private Integer id;
	/** 商品ID */
	private Integer itemId;
	/** 注文ID */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/** サイズ */
	private Character size;
	/** 商品 */
	private Item item;
	/** 注文トッピングリスト */
	private List<OrderTopping> orderToppingList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}
	
	public int getSubTotal() {
		int itemPrice = 0;
		int totalToppingPrice = 0;
		if( size == 'M' ) {
			itemPrice = item.getPriceM();
		}
		if( size == 'L' ) {
			itemPrice = item.getPriceL();
		}
		for( OrderTopping orderTopping : orderToppingList ) {
			Topping topping = orderTopping.getTopping();
			if( size == 'M') {
				totalToppingPrice += topping.getPriceM();
			}
			if( size == 'L') {
				totalToppingPrice += topping.getPriceL();
			}
		}
		return (itemPrice + totalToppingPrice) * quantity;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}
}