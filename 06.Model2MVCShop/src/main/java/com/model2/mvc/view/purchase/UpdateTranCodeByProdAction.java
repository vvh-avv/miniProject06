package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeByProdAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String prodNo = request.getParameter("prodNo");
		String tranCode = request.getParameter("tranCode");
		System.out.println("*********"+prodNo+"*********"+tranCode);
		
		//0423 �ϰ�ó��
		if(prodNo.contains(",")) {
			String[] prodList = prodNo.split(",");
			for(int i=0; i<prodList.length; i++) {
				Purchase purchaseVO = new Purchase();
				PurchaseService service = new PurchaseServiceImpl();
				purchaseVO = service.getPurchase2(Integer.parseInt(prodList[i]));
				purchaseVO.setTranCode(tranCode);
				service.updateTranCode(purchaseVO);			
			}
		}else {
			Purchase purchaseVO = new Purchase();
			PurchaseService service = new PurchaseServiceImpl();
			purchaseVO = service.getPurchase2(Integer.parseInt(prodNo)); //prodNo �������� purchaseVO �����ͼ�
			purchaseVO.setTranCode(tranCode); //������ tranCode �� �����ä
			service.updateTranCode(purchaseVO); //update ���� ����			
		}
		
		return "redirect:listProduct.do?menu=manage"; //forward�� �ص� �����غ���
	}

}
