package com.model2.mvc.service.domain;

public class Cart {
	
	Product purchaseProd;
	User buyer;
	
	
	
	
	public Cart() {
		
	}
	
	
	@Override
	public String toString() {
		return "Cart [purchaseProd=" + purchaseProd + ", buyer=" + buyer + "]";
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


	
	
}
