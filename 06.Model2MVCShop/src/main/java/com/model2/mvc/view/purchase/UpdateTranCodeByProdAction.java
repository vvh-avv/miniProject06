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
		
		//0423 일괄처리
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
			purchaseVO = service.getPurchase2(Integer.parseInt(prodNo)); //prodNo 기준으로 purchaseVO 가져와서
			purchaseVO.setTranCode(tranCode); //변경할 tranCode 로 덮어씌운채
			service.updateTranCode(purchaseVO); //update 쿼리 날림			
		}
		
		return "redirect:listProduct.do?menu=manage"; //forward로 해도 무방해보임
	}

}
