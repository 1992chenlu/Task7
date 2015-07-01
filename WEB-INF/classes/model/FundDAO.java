package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.DuplicateKeyException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.Fund;

public class FundDAO extends GenericDAO<Fund> {
	private Fund fund;

	public FundDAO(ConnectionPool cp, String tableName) throws DAOException,
			RollbackException {
		// TODO Auto-generated constructor stub

		super(Fund.class, tableName, cp);

		Fund[] list = match();
		if (list.length == 0) {
			init();
		}
	}

	public void create(Fund fund) throws DuplicateKeyException {
		try {
			this.fund = fund;
			if (getFund(fund.getName()) != null) {
				throw new DuplicateKeyException(fund.getName());
			}
			fund.setName(toDisplayCase(fund.getName()));
			fund.setSymbol(fund.getSymbol().toUpperCase());
			createAutoIncrement(fund);
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("This fund already exists: "
					+ fund.getName());
		} catch (RollbackException e) {
			// throw new BeanFactoryException(e);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isExisted(String username) throws RollbackException {
		Fund[] funds = match(MatchArg.equals("name", username));
		if (funds.length > 0) {
			return true;
		}
		return false;
	}

	public boolean isSymbol(String username) throws RollbackException {
		Fund[] funds = match(MatchArg.equals("symbol", username));
		if (funds.length > 0) {
			return true;
		}
		return false;
	}

	public Fund getFund(String name) throws DAOException {
		// TODO Auto-generated method stub

		try {
			System.out.println("this is display case: " + name);
			Fund[] array = (match(MatchArg.equals("name", name)));
			Fund fund;
			if (array.length > 0) {
				fund = array[0];
				return fund;
			}
			return null;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}

	}

	public void init() throws RollbackException {
		// TODO Auto-generated method stub
		Fund fund = new Fund();
		fund.setName("facebook");
		fund.setSymbol("FB");
		fund.setFund_price(100);
		create(fund);
	}

	public String toDisplayCase(String s) {

		final String ACTIONABLE_DELIMITERS = " '-/"; // these cause the
														// character following
														// to be capitalized

		StringBuilder sb = new StringBuilder();
		boolean capNext = true;

		for (char c : s.toCharArray()) {
			c = (capNext) ? Character.toUpperCase(c) : Character.toLowerCase(c);
			sb.append(c);
			capNext = (ACTIONABLE_DELIMITERS.indexOf((int) c) >= 0); // explicit
																		// cast
																		// not
																		// needed
		}
		return sb.toString();
	}
	
	public String getFundNameById(int fundId) throws RollbackException {
		Fund[] funds = match(MatchArg.equals("fund_id", fundId));
		for (int i = 0; i < funds.length; i++) {
			if (funds[i] != null) {
				return funds[i].getName();
			}
		}
		return "unknown";
	}
	
	public long getFundPriceById(int fundId) throws RollbackException {
		Fund[] funds = match(MatchArg.equals("fund_id", fundId));
		for (int i = 0; i < funds.length; i++) {
			if (funds[i] != null) {
				return funds[i].getFund_price();
			}
		}
		return 0;
	}
	
	public void updatePrice(int fund_id, long price, String name, String symbol)
			throws RollbackException {
		// TODO Auto-generated method stub
		try {
			Transaction.begin();
			Fund fdb = new Fund();
			fdb.setFund_id(fund_id);
			fdb.setFund_price(price);
			fdb.setName(name);
			fdb.setSymbol(symbol);
			update(fdb);
			Transaction.commit();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			throw new RollbackException(e);
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}

	}

}
