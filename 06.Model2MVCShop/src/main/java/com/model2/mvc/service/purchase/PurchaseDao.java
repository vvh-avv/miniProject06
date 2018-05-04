package com.model2.mvc.service.purchase;

import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {
	
	public void addPurchase(Purchase purchase) throws Exception ;
	
	public Purchase getPurchase(int no) throws Exception ;	
	
	public List<Purchase> getPurchaseList(Search search, String buyerId, String sort) throws Exception ;
	
	public HashMap<String,Object> getSaleList(Search search) ;
		//return null;
	//}//end of getSaleList()
	
	
	public void updatePurchase(Purchase purchase) throws Exception ;
	
	/*
	public void deletePurchase(Purchase purchaseVO) throws Exception {
		System.out.println("PurchaseDAO :: deleteTrancode() Ω√¿€");
		
		Connection con = DBUtil.getConnection();
		
		String sql = "DELETE FROM transaction WHERE tran_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, purchaseVO.getTranNo());
		stmt.executeQuery();

		System.out.println("PurchaseDAO :: Original SQL :: " + sql);
		
		con.close();

		System.out.println("PurchaseDAO :: deleteTrancode() ≥°");
	}//end of deleteTrancode()
	*/
	
	public void updateTrancode(Purchase purchase) throws Exception ;	
	
	public int getTotalCount(Search search) throws Exception ;
}