package com.model2.mvc.service.domain;

public class Cart {
	
	Product purchaseProd;
	User buyer;
	int amount;
	
	
	
	
	
	public Cart() {
		
	}
	
	
	@Override
	public String toString() {
		return "Cart [purchaseProd=" + purchaseProd + ", buyer=" + buyer + ", amount=" + amount + "]";
	}


	public Product getPurchaseProd() {
		return purchaseProd;
	}


	public void setPurchaseProd(Product purchaseProd) {
		this.purchaseProd = purchaseProd;
	}


	public User getBuyer() {
		return buyer;
	}


	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}





	
	
}
