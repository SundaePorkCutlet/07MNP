package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
								HttpServletRequest request,
									Model model) throws Exception {

		System.out.println("/addPurchase : POST");

		Product product = productService.getProduct(prodNo);
		purchase.setPurchaseProd(product);
		purchase.setBuyer((User)(request.getSession(true).getAttribute("user")));
		purchase.setDivyAddr(Addr);
		purchase.setDivyRequest(Request);
		purchase.setDivyDate(Date);
		purchase.setTranCode("001");
		purchaseService.addPurchase(purchase);
		
//		model.addAttribute("PurchaseProd",product);
//		model.addAttribute("Buyer",request.getSession(true).getAttribute("user"));
		
		System.out.println(request.getSession(true).getAttribute("user"));
		
		
		
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
	public String updateTranCodeByProd( @RequestParam("prodNo") int prodNo,
						@RequestParam("ProTranCode") String TranCode,  
									Model model,
									HttpServletRequest request) throws Exception{

		System.out.println("/updateTranCodeByProd : GET");
		//Business Logic
		Purchase purchase = purchaseService.getPurchase2(prodNo);
		String tran=TranCode;
		if("001".equals(tran)) {
			tran = "002";
			purchase.setTranCode(tran);
			purchaseService.updateTranCode(purchase);
		}
		System.out.println(request.getParameter("menu"));
		model.addAttribute("menu",request.getParameter("menu"));
		
		
		return "forward:/product/listProduct";
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
	
	
}