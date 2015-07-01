package model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.FundDetail;

public class FundPriceDAO extends GenericDAO<FundDetail> {

	public FundPriceDAO(ConnectionPool cp, String tableName) throws DAOException, RollbackException, SQLException{
		// TODO Auto-generated constructor stub
		super(FundDetail.class, tableName, cp);
//		Connection con = cp.getConnection();
		
//		PreparedStatement pst = con.prepareStatement("ALTER TABLE "+ tableName +" ADD FOREIGN KEY (fund_id) REFERENCES Fund(fund_id)");
//		pst.executeUpdate();
		FundDetail[] list = match();
		if (list.length == 0) {
			init();
		}
		
	}

	public void init() throws RollbackException {
		// TODO Auto-generated method stub
		FundDetail fundDetail = new FundDetail();
		fundDetail.setFund_id(1);
		fundDetail.setPrice(1);
		fundDetail.setPrice_date( new java.util.Date());
		create(fundDetail);
	}
	
//	public void create(FundDetail fundDetail){
//		try {
//			Transaction.begin();
//			create(fundDetail);
//			Transaction.commit();
//		} catch (RollbackException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			if (Transaction.isActive()) {
//				Transaction.rollback();
//			}
//		}
//	}
	
	public Date getMaximumDate() throws RollbackException {
		Date dt= null;
		FundDetail[] list = match();
		Arrays.sort(list);
		dt = list[list.length-1].getPrice_date();
		return dt;	
	}

	public void createPrice(int fund_id, long price, Date newDate) throws RollbackException {
		// TODO Auto-generated method stub
		
		try {
			FundDetail fund_history = new FundDetail();
			fund_history.setFund_id(fund_id);
			fund_history.setPrice(price);
			fund_history.setPrice_date(newDate);
			create(fund_history);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new RollbackException(e);
		}
		
		
	}
}
