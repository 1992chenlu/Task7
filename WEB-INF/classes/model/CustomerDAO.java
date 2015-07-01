/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.Customer;

public class CustomerDAO extends GenericDAO<Customer> {
	public CustomerDAO(ConnectionPool cp, String tableName)
			throws DAOException, RollbackException {
		super(Customer.class, tableName, cp);
		
		// Write Initialize later!
		Customer[] list = match();
		if (list.length == 0) {
			init();
		}
	}

	public int getCustomerIdByUsername(String username)
			throws RollbackException {
		Customer[] customers = match();
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].getUsername().equals(username)) {
				return customers[i].getCustomer_id();
			}
		}
		return -1;
	}

	public boolean isExisted(String username) throws RollbackException {
		Customer[] customers = match(MatchArg.equals("username", username));
		if (customers.length > 0) {
			return true;
		}
		return false;
	}

	public void setPassword(String username, String password)
			throws RollbackException {
		try {
			Transaction.begin();
			Customer customer = read(getCustomerIdByUsername(username));

			if (customer == null) {
				throw new RollbackException("User registered with \" "
						+ username + " \" not exists");
			}

			customer.setPassword(password);

			update(customer);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	public void init() throws RollbackException {
		Customer customer1 = new Customer();
		customer1.setUsername("user1");
		customer1.setPassword("123");
		customer1.setFirstname("John");
		customer1.setLastname("Smith");
		customer1.setAddr_line1("417 S Craig St");
		customer1.setAddr_line2("floor 2");
		customer1.setCity("Pittsburgh");
		customer1.setState("PA");
		customer1.setZip("15213");
		customer1.setCash(0);
		customer1.setAvailable_cash(0);
		create(customer1);

		Customer customer2 = new Customer();
		customer2.setUsername("user2");
		customer2.setPassword("123");
		customer2.setFirstname("Mary");
		customer2.setLastname("Smith");
		customer2.setAddr_line1("418 S Craig St");
		customer2.setAddr_line2("floor 1");
		customer2.setAvailable_cash(0);
		customer2.setCity("Pittsburgh");	
		customer2.setState("PA");
		customer2.setZip("15213");
		customer2.setCash(0);
		create(customer2);

		Customer customer3 = new Customer();
		customer3.setUsername("user3");
		customer3.setPassword("123");
		customer3.setFirstname("Joe");
		customer3.setLastname("Smith");
		customer3.setAddr_line1("419 S Craig St");
		customer3.setAddr_line2("floor 0");
		customer3.setCity("Pittsburgh");
		customer3.setState("PA");
		customer3.setAvailable_cash(0);
		customer3.setZip("15213");
		customer3.setCash(0);
		create(customer3);
	}

	public void setAvailableCash(double d, int customer_id) throws RollbackException{
		// TODO Auto-generated method stub
		try {
			Transaction.begin();
			Customer customer = read(customer_id);
			long cash_available = customer.getAvailable_cash();
			System.out.println("amount to be added");
			cash_available = cash_available + (long) Math.round(d);
			customer.setAvailable_cash(cash_available);
			update(customer);
			Transaction.commit();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RollbackException(e);
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	
		
	}
}
