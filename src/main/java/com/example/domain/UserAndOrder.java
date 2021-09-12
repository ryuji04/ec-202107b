package com.example.domain;

import java.util.Date;

public class UserAndOrder {
	private Date orderDate;
	private int totalPrice;
	private String email;
	private String telephone;
	private String address;
	
	  
	public UserAndOrder() {
	}
	  
	public UserAndOrder(Date orderDate,int totalPrice,String email,String telephone,String address) {
		this.orderDate=orderDate;
		this.totalPrice=totalPrice;
		this.email=email;
		this.telephone=telephone;
		this.address=address;
	}

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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
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



