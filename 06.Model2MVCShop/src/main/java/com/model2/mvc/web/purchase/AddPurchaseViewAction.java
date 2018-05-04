package com.model2.mvc.web.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class AddPurchaseViewAction extends Action{
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ProductService service=new ProductServiceImpl();
		Product vo=service.getProduct(Integer.parseInt(request.getParameter("prod_no")));
		
		request.setAttribute("product", vo);

		return "forward:/purchase/addPurchaseView.jsp";
	}

}
