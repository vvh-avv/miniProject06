package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tranNo = request.getParameter("tranNo");
		String tranCode = request.getParameter("tranCode");
		
		Purchase purchaseVO = new Purchase();

		PurchaseService service = new PurchaseServiceImpl();
		purchaseVO = service.getPurchase(Integer.parseInt(tranNo)); //tranNo 기준으로 purchaseVO 가져와서
		purchaseVO.setTranCode(tranCode); //변경할 tranCode 로 덮어씌운채
		
		service.updateTranCode(purchaseVO); //update 쿼리 날림
		
		return "redirect:listPurchase.do"; //forward로 해도 무방해보임
	}

}
