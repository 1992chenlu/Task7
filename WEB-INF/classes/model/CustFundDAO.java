package model;

import java.sql.SQLException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.CustomerFund;

public class CustFundDAO extends GenericDAO<CustomerFund> {

	public CustFundDAO(ConnectionPool cp, String tableName)
			throws DAOException, RollbackException, SQLException {
		// TODO Auto-generated constructor stub

		super(CustomerFund.class, tableName, cp);
		
		CustomerFund[] list = match();
		if (list.length == 0) {
			init();
		}
			
	}
	
	public CustomerFund[] getCustomerFund(int customerID) throws RollbackException {
		CustomerFund[] customerFundInfo = match(MatchArg.equals("customer_id", customerID));
		return customerFundInfo;
	}
	
	public long getAvailableShares(int fund_id, int customer_id) throws RollbackException {
		CustomerFund[] customerFundInfo = match(MatchArg.equals("fund_id", fund_id));
		for (CustomerFund customerFund : customerFundInfo) {
			if (customerFund != null && customerFund.getCustomer_id() == customer_id) {
				return customerFund.getAvailable_shares();
			}
		}
		return -1;
	}
	
	public CustomerFund getCustomerFund(int fund_id, int customer_id) throws RollbackException {
		CustomerFund[] customerFundInfo = match(MatchArg.equals("fund_id", fund_id));
		for (CustomerFund customerFund : customerFundInfo) {
			if (customerFund != null && customerFund.getCustomer_id() == customer_id) {
				return customerFund;
			}
		}
		return null;
	}
	
	public void setCustomerFund(int fund_id, int customer_id, long shares) throws RollbackException {
		CustomerFund[] customerFundInfo = match(MatchArg.equals("fund_id", fund_id));
		for (CustomerFund customerFund : customerFundInfo) {
			if (customerFund != null && customerFund.getCustomer_id() == customer_id) {
				customerFund.setAvailable_shares(shares);
				update(customerFund);
			}
		}
	}
	
	public void init() throws RollbackException {
		CustomerFund customer1 = new CustomerFund();
		customer1.setAvailable_shares(1);
		customer1.setCustomer_id(1);
		customer1.setFund_id(1);
		customer1.setShares(1);
		create(customer1);

		CustomerFund customer2 = new CustomerFund();
		customer2.setAvailable_shares(1);
		customer2.setCustomer_id(2);
		customer2.setFund_id(1);
		customer2.setShares(1);
		create(customer2);
		
		CustomerFund customer3 = new CustomerFund();
		customer3.setAvailable_shares(1);
		customer3.setCustomer_id(3);
		customer3.setFund_id(1);
		customer3.setShares(1);
		create(customer3);
	}
}
