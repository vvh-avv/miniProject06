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
		purchaseVO = service.getPurchase(Integer.parseInt(tranNo)); //tranNo �������� purchaseVO �����ͼ�
		purchaseVO.setTranCode(tranCode); //������ tranCode �� �����ä
		
		service.updateTranCode(purchaseVO); //update ���� ����
		
		return "redirect:listPurchase.do"; //forward�� �ص� �����غ���
	}

}
