package model;

import java.sql.SQLException;
import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import org.genericdao.Transaction;

import databean.Transactions;

public class TransactionDAO extends GenericDAO<Transactions> {

	public TransactionDAO(ConnectionPool cp, String tableName) throws DAOException, RollbackException, SQLException{
		// TODO Auto-generated constructor stub
		super(Transactions.class, tableName, cp);
//		Connection con = cp.getConnection();
//		
//		PreparedStatement pst = con.prepareStatement("ALTER TABLE "+ tableName +" ADD FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)");
//		pst.executeUpdate();
//		pst = con.prepareStatement("ALTER TABLE "+ tableName +" ADD FOREIGN KEY (fund_id) REFERENCES Fund(fund_id)");
//		pst.executeUpdate();
	}

	public Transactions[] getTransactionByCusId(int customer_id) throws RollbackException {
		Transactions[] transactions = match(MatchArg.equals("customer_id",customer_id));
		return transactions;
	}

	public void createRow(long amount, int customer_id, Date newDate,
			int fund_id, long shares, String transaction_type) throws RollbackException {
		// TODO Auto-generated method stub
		try {
			Transactions transBean = new Transactions();
			transBean.setAmount(amount);
			transBean.setCustomer_id(customer_id);
			transBean.setExecute_date(newDate);
			transBean.setFund_id(fund_id);
			transBean.setShares(shares);
			transBean.setTransaction_type(transaction_type);
			create(transBean);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new RollbackException(e);
		}
	}
}
