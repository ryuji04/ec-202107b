package com.example.form;

import java.util.Date;

public class UserAndOrderForm {
	private Date orderDate;
	private int totalPrice;
	private String email;
	private int telephone;
	private String address;
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "UserAndOrder [orderDate=" + orderDate + ", totalPrice=" + totalPrice + ", email=" + email
				+ ", telephone=" + telephone + ", address=" + address + "]";
	}
	
}