package com.model2.mvc.service.cart;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Cart;

public interface CartDao {

	public void addCart(Cart cart) throws Exception;
	
	public List<Cart> getCartList(Search search, String buyerId) throws Exception;
	
	public int getTotalCount(String buyerId) throws Exception ;
	
	public void deleteCart(Cart cart) throws Exception;
	
}