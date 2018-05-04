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
		purchaseVO.setBuyer((User)session.getAttribute("user")); //������ ����
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr")); //����� �ּ�
		purchaseVO.setDivyDate(request.getParameter("receiverDate")); //��� ��� ��¥
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest")); //��۽� �䱸����
		purchaseVO.setPaymentOption(request.getParameter("paymentOption")); //���ҹ�� //1:���� 2:�ſ�
		purchaseVO.setPurchaseProd(new ProductServiceImpl().getProduct(Integer.parseInt(request.getParameter("prodNo")))); //��ǰ ����
		purchaseVO.setReceiverName(request.getParameter("receiverName")); //�޴»�� �̸�
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone")); //�޴»�� ��ȭ��ȣ
		
		System.out.println(purchaseVO);

		PurchaseService service=new PurchaseServiceImpl();
		service.addPurchase(purchaseVO);
		request.setAttribute("purchase", purchaseVO);
		
		
		return "forward:/purchase/addPurchase.jsp";
	}
}
