package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class ListPurchaseAction extends Action{
	@Override
	public String execute(	HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		//���� ������� üũ
		if( (User)session.getAttribute("user")==null ) {
			System.out.println("������ ���ܼ� �α��� �������� �̵�");
			return "redirect:/user/loginView.jsp";
		}

		Search searchVO = new Search();
		int currentPage=1;
		if( request.getParameter("currentPage") != null ) {
			if( !request.getParameter("currentPage").equals("") ) {
				currentPage=Integer.parseInt(request.getParameter("currentPage"));
			}
		}
		
		searchVO.setCurrentPage(currentPage);
		searchVO.setSearchCondition(request.getParameter("searchCondition")); //��ġ�� �������� ���� //��ǰ��ȣ, ��ǰ��, ��ǰ���� 
		searchVO.setSearchKeyword(request.getParameter("searchKeyword")); //��ġ�� ������ �� ��ü, Ű����
		
		// web.xml  meta-data �� ���� ��� ���� 
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		searchVO.setPageSize(pageSize);
		
		//0414 ��������, �������� ����
		String sort = "asc";
		if(request.getParameter("sort")!=null) {
			sort = request.getParameter("sort");
		}
		
		//0418 ������ �� ���� �ֹ����� Ȯ�� �� �� �ֵ��� ó��
		String userId = ((User)session.getAttribute("user")).getUserId();
		if(request.getParameter("userId")!=null) {
			userId = request.getParameter("userId");
		}
		//System.out.println("???????????????????????????"+userId);
		
		PurchaseService service = new PurchaseServiceImpl();
		Map<String,Object> map=service.getPurchaseList(searchVO, userId, sort);
		
		Page resultPage	= new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("ListProductAction ::"+resultPage);
		
		request.setAttribute("map", map); //������ �� �����ų 3���� �ֹ������� VO�� ��� ����
		request.setAttribute("searchVO", searchVO); //��ġ�� �� �ִ� ���ǵ��� VO�� ��� ����
		
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", searchVO);
		request.setAttribute("sort",sort);
		
		return "forward:/purchase/listPurchase.jsp";
	}

}
