package model;

import java.sql.SQLException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.TemporaryTransaction;

public class TemporaryTransDAO extends GenericDAO<TemporaryTransaction> {

	public TemporaryTransDAO(ConnectionPool cp, String tableName)
			throws DAOException, RollbackException, SQLException {
		// TODO Auto-generated constructor stub
		super(TemporaryTransaction.class, tableName, cp);
		// Connection con = cp.getConnection();

		// PreparedStatement pst = con.prepareStatement("ALTER TABLE "+
		// tableName
		// +" ADD FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)");
		// pst.executeUpdate();
		// pst = con.prepareStatement("ALTER TABLE "+ tableName
		// +" ADD FOREIGN KEY (fund_id) REFERENCES Fund(fund_id)");
		// pst.executeUpdate();
	}

	public void depositCheck(int customer_id, double paraCash)
			throws DAOException {
		// TODO Auto-generated method stub
		long cash = (long) (paraCash * 100);
		System.out.println("entered deposit check in temptransdao");
		try {
			TemporaryTransaction tempTrans = new TemporaryTransaction();
			tempTrans.setAmount(cash);
			tempTrans.setCustomer_id(customer_id);
			tempTrans.setFund_id(0);
			tempTrans.setShares(0);
			tempTrans.setTransaction_type("DEPOSIT_CHECK");
			tempTrans.setExecute_date(null);

			createAutoIncrement(tempTrans);

		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}

	}

	public void requestCheck(int customer_id, double paraCash)
			throws DAOException {
		// TODO Auto-generated method stub
		TemporaryTransaction tempTrans = new TemporaryTransaction();
		long cash = (long) Math.round((0 - paraCash * 100));
		System.out.println("entered request check in temptransdao");
		try {
			tempTrans.setAmount(cash);
			tempTrans.setCustomer_id(customer_id);
			tempTrans.setFund_id(0);
			tempTrans.setShares(0);
			tempTrans.setTransaction_type("REQUEST_CHECK");
			tempTrans.setExecute_date(null);

			createAutoIncrement(tempTrans);

		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
	}

	public long getPending(int customer_id) throws DAOException {
		// TODO Auto-generated method stub
		try {

			TemporaryTransaction[] array = (match(MatchArg.equals(
					"customer_id", customer_id)));
			// TemporaryTransaction trans;
			long sum = 0;
			if (array.length > 0) {
				for (int i = 0; i < array.length; i++) {

					sum = sum + array[i].getAmount();
				}

				return sum;
			}
			return 0;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}

	}

	public void buyFund(int fund_id, double amount, int customer_id)
			throws DAOException {
		// TODO Auto-generated method stub

		TemporaryTransaction tempTrans = new TemporaryTransaction();
		long cash = (long) (0 - amount * 100);
		System.out.println("entered buy fund in temptransdao");
		try {
			tempTrans.setAmount(cash);
			tempTrans.setCustomer_id(customer_id);
			tempTrans.setFund_id(fund_id);
			tempTrans.setShares(0);
			tempTrans.setTransaction_type("BUY_FUND");
			tempTrans.setExecute_date(null);

			createAutoIncrement(tempTrans);

		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}

	}

	public void sellFund(int fund_id, long shares, int customer_id)
			throws DAOException {
		// TODO Auto-generated method stub
		TemporaryTransaction tempTrans = new TemporaryTransaction();
		// long cash = (long) (0 - amount * 100);
		System.out.println("entered sell fund in temptransdao");
		try {
			tempTrans.setAmount(0);
			tempTrans.setCustomer_id(customer_id);
			tempTrans.setFund_id(fund_id);
			tempTrans.setShares(0 - shares);
			tempTrans.setTransaction_type("SELL_FUND");
			tempTrans.setExecute_date(null);

			createAutoIncrement(tempTrans);

		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		} 
	}

	public double getPendingSell(int customer_id, int fund_id)
			throws RollbackException {
		// TODO Auto-generated method stub

		TemporaryTransaction[] array = (match(MatchArg.equals("customer_id",
				customer_id)));
		double sell = 0;
		for (TemporaryTransaction trans : array) {
			if (trans.getFund_id() == fund_id) {
				sell = sell + trans.getShares();
			}
		}
		System.out.println("sell" + sell);
		return sell;
	}
	
	public TemporaryTransaction[] getTempTransactionByCusID(int customer_id) throws RollbackException {
		// TODO Auto-generated method stub
		TemporaryTransaction[] transactions = match(MatchArg.equals("customer_id",customer_id));
		return transactions;
	}

}
