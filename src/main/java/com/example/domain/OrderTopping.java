package com.example.domain;

/**
 * オーダートッピング情報を表すドメイン.
 * 
 * @author okahikari
 * 
 */
public class OrderTopping {

	/** ID */
	private Integer id;
	/** トッピングのID */
	private Integer toppingId;
	/** 注文商品のID */
	private Integer OrderItemId;
	/** トッピング */
	private Topping topping;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getToppingId() {
		return toppingId;
	}

	public void setToppingId(Integer toppingId) {
		this.toppingId = toppingId;
	}

	public Integer getOrderItemId() {
		return OrderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		OrderItemId = orderItemId;
	}

	public Topping getTopping() {
		return topping;
	}

	public void setTopping(Topping topping) {
		this.topping = topping;
	}

	@Override
	public String toString() {
		return "OrderTopping [id=" + id + ", toppingId=" + toppingId + ", OrderItemId=" + OrderItemId + ", topping="
				+ topping + "]";
	}

}
