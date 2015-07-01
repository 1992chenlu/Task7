/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package model;

import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

public class Model {
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private CustFundDAO custFundDAO;
	private FundDAO fundDAO;
	private FundPriceDAO fundPriceDAO;
	private TransactionDAO transactionDAO;
	private TemporaryTransDAO tempTransDAO;

	private boolean requireSSL;

	public Model(ServletConfig config) throws ServletException, SQLException {
		requireSSL = new Boolean(config.getInitParameter("requireSSL"));

		String jdbcDriver = config.getInitParameter("jdbcDriverName");
		String jdbcURL = config.getInitParameter("jdbcURL");

		try {
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);

			customerDAO = new CustomerDAO(pool, "Customer");
			employeeDAO = new EmployeeDAO(pool, "Employee");
			fundDAO = new FundDAO(pool, "Fund");
			fundPriceDAO = new FundPriceDAO(pool, "fund_price_history");
			custFundDAO = new CustFundDAO(pool, "CustomerFund");
			transactionDAO = new TransactionDAO(pool, "Transactions");
			tempTransDAO = new TemporaryTransDAO(pool, "temporary_transactions");

		} catch (DAOException e) {
			throw new ServletException(e);
		} catch (RollbackException e) {
			throw new ServletException(e);
		}
	}

	public boolean getRequireSSL() {
		return requireSSL;
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public CustFundDAO getCustFundDAO() {
		return custFundDAO;
	}

	public FundDAO getFundDAO() {
		return fundDAO;
	}

	public FundPriceDAO getFundPriceDAO() {
		return fundPriceDAO;
	}

	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}

	public TemporaryTransDAO getTempTransDAO() {
		return tempTransDAO;
	}

}
