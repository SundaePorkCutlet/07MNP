package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {
	
	///Field
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("cartServiceImpl")
	private CartService cartService;
	//setter Method 구현 않음
		
	public PurchaseController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	//@RequestMapping("/addPurchaseView.do")
	@RequestMapping(value="addPurchase",method=RequestMethod.GET)
	public String addPurchaseView(@RequestParam("prod_no") int prodNo, Model model) throws Exception {

		System.out.println("/addPurchase : GET");
		Product product = productService.getProduct(prodNo);
		
		model.addAttribute("product",product);
		
		
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
	//@RequestMapping("/addPurchase.do")
	@RequestMapping(value="addPurchase",method=RequestMethod.POST)
	public String addPurchase( @ModelAttribute("purchase") Purchase purchase ,
								@RequestParam("prodNo") int prodNo,
								@RequestParam("receiverAddr") String Addr,
								@RequestParam("receiverRequest") String Request,
								@RequestParam("receiverDate") String Date,
								@RequestParam("amount") int amount,
								
								HttpServletRequest request,
									Model model) throws Exception {

		System.out.println("/addPurchase : POST");
		System.out.println();

		Product product = productService.getProduct(prodNo);
		
		int totalamount = product.getAmount() - amount;
		System.out.println("aaaaa"+totalamount);
		System.out.println("bbbbb"+amount);
		
		product.setAmount(totalamount);
		productService.updateProduct(product);
		
		
		User user = (User)request.getSession(true).getAttribute("user");
		int remainPoint = user.getPoint()-product.getPrice();
		
		System.out.println(remainPoint);
		System.out.println("구매방법"+purchase.getPaymentOption());
		if(purchase.getPaymentOption().equals("3")) {
		if(remainPoint>0) {
			System.out.println("포인트구매 진입");
			
			user.setPoint(remainPoint);
			purchase.setPurchaseProd(product);
			purchase.setBuyer((User)(request.getSession(true).getAttribute("user")));
			purchase.setDivyAddr(Addr);
			purchase.setDivyRequest(Request);
			purchase.setDivyDate(Date);
			purchase.setTranCode("001");
			purchase.setPurchaseAmount(amount);
			purchaseService.addPurchase(purchase);
			userService.updatePoint(user);
			
		}
		}else {
			purchase.setPurchaseProd(product);
			purchase.setBuyer((User)(request.getSession(true).getAttribute("user")));
			purchase.setDivyAddr(Addr);
			purchase.setDivyRequest(Request);
			purchase.setDivyDate(Date);
			purchase.setTranCode("001");
			purchase.setPurchaseAmount(amount);
			purchaseService.addPurchase(purchase);
		}
	
	
		model.addAttribute("amount",amount);
//		model.addAttribute("Buyer",request.getSession(true).getAttribute("user"));
		model.addAttribute("remainPoint",remainPoint);
		
		System.out.println(user);
		
		
		
		return "forward:/purchase/addPurchase.jsp";
	}
	
	//@RequestMapping("/getPurchase.do")
	@RequestMapping(value="getPurchase",method=RequestMethod.GET)
	public String getPurchase(@RequestParam("tranNo") int tranNo, Model model) throws Exception {

		System.out.println("/getPurchase : GET");
		Purchase purchase = purchaseService.getPurchase(tranNo);
		
		model.addAttribute("purchase",purchase);
		
		
		
		return "forward:/purchase/getPurchase.jsp";
	}
	
	//@RequestMapping("/listPurchase.do")
	@RequestMapping(value="listPurchase" )
	public String listPurchase( @ModelAttribute("search") Search search , Model model , HttpServletRequest request
								) throws Exception{
		
		System.out.println("/listPurchase ");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String,Object> map=purchaseService.getPurchaseList(search,((User)request.getSession(true).getAttribute("user")).getUserId());

		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/purchase/listPurchase.jsp";
	}
	
	//@RequestMapping("/updatePurchaseView.do")
	@RequestMapping(value="updatePurchase",method=RequestMethod.GET)
	public String updatePurchaseView( @RequestParam("tranNo") int tranNo , Model model) throws Exception{

		System.out.println("/updatePurchase : GET");
		//Business Logic
		Purchase purchase = purchaseService.getPurchase(tranNo);
	
		// Model 과 View 연결
		model.addAttribute("purchase", purchase);
		
		return "forward:/purchase/updatePurchaseView.jsp";
	}
	
	//@RequestMapping("/updatePurchase.do")
	@RequestMapping(value="updatePurchase",method=RequestMethod.POST)
	public String updatePurchase(  HttpServletRequest request ,
									@RequestParam("tranNo") int tranNo,  
									Model model ) throws Exception{

		System.out.println("/updatePurchase : POST");
		//Business Logic
		Purchase purchase = purchaseService.getPurchase(tranNo);
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("divyDate"));

	
		purchaseService.updatePurchase(purchase);
		model.addAttribute("purchase",purchase);
		
		
		
		return "forward:/purchase/updatePurchase.jsp";
	
	}

	
	//@RequestMapping("/updateTranCodeByProd.do")
	@RequestMapping(value="updateTranCodeByProd",method=RequestMethod.GET)
	public String updateTranCodeByProd( @RequestParam("tranNo") int tranNo,
						@RequestParam("ProTranCode") String TranCode,  
									Model model,
									HttpServletRequest request) throws Exception{

		System.out.println("/updateTranCodeByProd : GET");
		//Business Logic
		Purchase purchase = purchaseService.getPurchase(tranNo);
		String tran=TranCode;
		if("001".equals(tran)) {
			tran = "002";
			purchase.setTranCode(tran);
			purchaseService.updateTranCode(purchase);
		}

	
		
		
		return "forward:/product/listProductManage";
	}
	
	//@RequestMapping("/updateTranCode.do")
	@RequestMapping(value="updateTranCode",method=RequestMethod.GET)
	public String updateTranCode( @RequestParam("tranNo") int tranNo,
						@RequestParam("TranCode") String TranCode,  
									Model model ) throws Exception{

		System.out.println("/updateTranCode : GET");
		//Business Logic
		Purchase purchase = purchaseService.getPurchase(tranNo);
		String tran=TranCode;
		if("002".equals(tran)) {
			tran = "003";
			purchase.setTranCode(tran);
			purchaseService.updateTranCode(purchase);
		}
		
		
		
		return "redirect:/purchase/listPurchase";
	}
	
	@RequestMapping(value="random" )
	public String random( Model model , 
						HttpServletRequest request,
						@RequestParam("gameNumber") int gameNumber,
						@RequestParam("gamePoint") int gamePoint
								) throws Exception{
		User user = (User)(request.getSession(true).getAttribute("user"));
		int result = (int)((Math.random()*5)+1);
		if(user.getPoint()-gamePoint>-1 && gamePoint >0) {
			if(result == gameNumber) {
				gamePoint = gamePoint*2;
				model.addAttribute("rs","정답");
			}else {
				gamePoint = gamePoint*(-1);
				model.addAttribute("rs","오답");
			}
		}else {
			model.addAttribute("rs","부족");
			return "forward:/miniGame/random.jsp";
		}
		
		int remainPoint = user.getPoint() + gamePoint;
		user.setPoint(remainPoint);
		userService.updatePoint(user);
		model.addAttribute("result",result);
		model.addAttribute("gamePoint",gamePoint);
		model.addAttribute("gameNumber",gameNumber);
		
		
		return "forward:/miniGame/random.jsp";
	}
	
	@RequestMapping(value="listCart")
	public String listCart(@ModelAttribute("search") Search search, 
			HttpServletRequest request,
			
									Model model ) throws Exception{
		
		
	
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		
		System.out.println("search"+search);
		
		// Business logic 수행
		Map<String,Object> map=cartService.getCartList(search,((User)request.getSession(true).getAttribute("user")).getUserId());

		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		System.out.println(map.get("list"));
		
		
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		
		
		
		return "forward:/purchase/listCart.jsp";
	}
	
	@RequestMapping(value="addCart")
	public String addCart( HttpServletRequest request,
						@RequestParam("prodNo") int prodNo,
						Model model
			                     ) throws Exception{
	
		Product product = productService.getProduct(prodNo);
		
		Cart cart = new Cart();
		cart.setBuyer(((User)request.getSession(true).getAttribute("user")));
		cart.setPurchaseProd(product);
		
		System.out.println(cart);
		cartService.addCart(cart);
	
		
		
		
		model.addAttribute("product",product);
		model.addAttribute("menu","search");
		model.addAttribute("cart","true");
	
		
	
		
		
		
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping(value="deleteCart")
	public String deleteCart( HttpServletRequest request,
						@RequestParam("prodNo") int prodNo,
						Model model
			                     ) throws Exception{
		Product product = productService.getProduct(prodNo);
		Cart cart = new Cart();
		cart.setPurchaseProd(product);
		cart.setBuyer(((User)request.getSession(true).getAttribute("user")));
		
		
		cartService.deleteCart(cart);
		model.addAttribute("cart",cart);
		
		return "forward:/purchase/listCart";
	}
	
	@RequestMapping(value="addPoint")
	public String addPoint( HttpServletRequest request,
			HttpSession session,
							Model model
			                     ) throws Exception{
	
		User user = new User();
		user = userService.getUser(((User)request.getSession(true).getAttribute("user")).getUserId());
		
		int remainpoint = user.getPoint() + 1000;
		user.setPoint(remainpoint);
		userService.updatePoint(user);
		model.addAttribute("user",user);
		session.setAttribute("user", user);
		
		
		return "forward:/miniGame/randomView.jsp";
	
		
	}
}