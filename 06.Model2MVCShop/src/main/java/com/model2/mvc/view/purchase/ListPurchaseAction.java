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
		//세션 끊겼는지 체크
		if( (User)session.getAttribute("user")==null ) {
			System.out.println("세션이 끊겨서 로그인 페이지로 이동");
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
		searchVO.setSearchCondition(request.getParameter("searchCondition")); //서치할 데이터의 조건 //상품번호, 상품명, 상품가격 
		searchVO.setSearchKeyword(request.getParameter("searchKeyword")); //서치할 데이터 그 자체, 키워드
		
		// web.xml  meta-data 로 부터 상수 추출 
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		searchVO.setPageSize(pageSize);
		
		//0414 오름차순, 내림차순 정렬
		String sort = "asc";
		if(request.getParameter("sort")!=null) {
			sort = request.getParameter("sort");
		}
		
		//0418 어드민이 각 유저 주문정보 확인 할 수 있도록 처리
		String userId = ((User)session.getAttribute("user")).getUserId();
		if(request.getParameter("userId")!=null) {
			userId = request.getParameter("userId");
		}
		//System.out.println("???????????????????????????"+userId);
		
		PurchaseService service = new PurchaseServiceImpl();
		Map<String,Object> map=service.getPurchaseList(searchVO, userId, sort);
		
		Page resultPage	= new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("ListProductAction ::"+resultPage);
		
		request.setAttribute("map", map); //페이지 당 노출시킬 3개의 주문정보를 VO로 담아 저장
		request.setAttribute("searchVO", searchVO); //서치할 수 있는 조건들을 VO로 담아 저장
		
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", searchVO);
		request.setAttribute("sort",sort);
		
		return "forward:/purchase/listPurchase.jsp";
	}

}
