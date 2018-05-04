package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class AddPurchaseAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		
		Purchase purchaseVO=new Purchase();
		purchaseVO.setBuyer((User)session.getAttribute("user")); //구매자 정보
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr")); //배송지 주소
		purchaseVO.setDivyDate(request.getParameter("receiverDate")); //배송 희망 날짜
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest")); //배송시 요구사항
		purchaseVO.setPaymentOption(request.getParameter("paymentOption")); //지불방식 //1:현금 2:신용
		purchaseVO.setPurchaseProd(new ProductServiceImpl().getProduct(Integer.parseInt(request.getParameter("prodNo")))); //물품 정보
		purchaseVO.setReceiverName(request.getParameter("receiverName")); //받는사람 이름
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone")); //받는사람 전화번호
		
		System.out.println(purchaseVO);

		PurchaseService service=new PurchaseServiceImpl();
		service.addPurchase(purchaseVO);
		request.setAttribute("purchase", purchaseVO);
		
		
		return "forward:/purchase/addPurchase.jsp";
	}
}
