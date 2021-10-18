package com.model2.mvc.service.cart.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.cart.CartDao;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.Purchase;

@Service("cartServiceImpl")
public class CartServiceImpl implements CartService{

	@Autowired
	@Qualifier("cartDaoImpl")
	private CartDao cartDao;
	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}
	
	public CartServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addCart(Cart cart) throws Exception {
		cartDao.addCart(cart);
		
	}
	
	@Override
	public void deleteCart(Cart cart) throws Exception {
		cartDao.deleteCart(cart);
		
	}

	@Override
	public Map<String, Object> getCartList(Search search, String buyerId) throws Exception {
		List<Cart> list= cartDao.getCartList(search,buyerId);
		int totalCount = cartDao.getTotalCount(buyerId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
	
		
		return map;
	}


}
