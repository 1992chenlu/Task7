package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.TemporaryTransDAO;
import model.TransactionDAO;

import org.genericdao.RollbackException;

import databean.Customer;
import databean.Employee;
import databean.TemporaryTransaction;
import databean.TemporaryTransactionShow;
import databean.Transactions;
import databean.TransactionShow;
import formbean.LoginForm;

public class ViewTransactionHistoryAction extends Action {
	
	CustomerDAO customerDAO;
	TransactionDAO transactionDAO;
	TemporaryTransDAO tempTransDAO;
	FundDAO fundDAO;
	Transactions[] transList;
	TemporaryTransaction[] tempTransList;
	TemporaryTransactionShow[] tempTransShowList;
	TransactionShow[] transShowList;
	int customer_id = 0;
	
	public ViewTransactionHistoryAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
		tempTransDAO = model.getTempTransDAO();
		fundDAO = model.getFundDAO();
	}

	@Override
	public String getName() {
		return "transactionHistory.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);
			
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		Employee employee = (Employee) request.getSession().getAttribute("employee");
			
		if (customer == null && employee == null) {
			request.setAttribute("form", new LoginForm());
			return "login.jsp";
		}
		
		if (customer == null){
			String id = (String) request.getParameter("id");
			if (id == null) {
				errors.clear();
				errors.add("No customer valid id provided, please select a customer.");
				System.out.println("No customer valid id provided, please select a customer.");
				request.setAttribute("errors", errors);
	    		try {
					request.setAttribute("customerList", customerDAO.match());
				} catch (RollbackException e) {
					// TODO Auto-generated catch block
					errors.clear();
					errors.add("No customer valid id provided, please select a customer.");
					
					request.setAttribute("errors", errors);
		    		
					return "manage.jsp";//Redirect to customer list
				}
				return "customerList.jsp";//Redirect to customer list
			}
			try {
				customer_id = Integer.parseInt(id);
				
				customer = customerDAO.read(customer_id);
				
				if (customer == null){
		    		errors.add("Not a Valid ID. Such Customer does not exist. Please select again.");
		    		request.setAttribute("errors", errors);
		    		request.setAttribute("customerList", customerDAO.match());
		    		return "customerList.jsp";
		    	}
			} catch (NumberFormatException | RollbackException ex) {
				errors.clear();
				errors.add("No customer valid id provided, please select a customer.");
				System.out.println("No customer valid id provided, please select a customer.");
				request.setAttribute("errors", errors);
				try {
					request.setAttribute("customerList", customerDAO.match());
				} catch (RollbackException e) {
					// TODO Auto-generated catch block
					errors.clear();
					errors.add("No customer valid id provided, please select a customer.");
					
					request.setAttribute("errors", errors);
		    		
					return "manage.jsp";//Redirect to customer list
				}
				return "customerList.jsp";//Redirect to customer list
			}
		} else {
			customer_id = customer.getCustomer_id();
		}
		

		
		
		try {
			transList = transactionDAO.getTransactionByCusId(customer_id);
		} catch (RollbackException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
        	request.setAttribute("errors",e1.getMessage());
        	return "viewTransactionHistory.jsp";
		}
		try {
			tempTransList = tempTransDAO.getTempTransactionByCusID(customer_id);
		} catch (RollbackException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
        	request.setAttribute("errors",e1.getMessage());
        	return "viewTransactionHistory.jsp";
		}
		
		int j = 0;
		if (transList != null) {
			transShowList = new TransactionShow[transList.length];
			for (Transactions transaction : transList) {
				transShowList[j] = new TransactionShow();
				transShowList[j].setAmount(transaction.getAmount());
				transShowList[j].setCustomer_id(transaction.getCustomer_id());
				transShowList[j].setExecute_date(transaction.getExecute_date());
				transShowList[j].setFund_id(transaction.getFund_id());
				try {
					transShowList[j].setFund_name(fundDAO.getFundNameById(transaction.getFund_id()));
				} catch (RollbackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					tempTransShowList[j].setFund_name("not available");
				}
				transShowList[j].setShares(transaction.getShares());
				transShowList[j].setTransaction_id(transaction.getTransaction_id());
				transShowList[j].setTransaction_type(transaction.getTransaction_type());
				j++;
			}
		}
		
		
		int i = 0;
		if (tempTransList != null) {
			tempTransShowList = new TemporaryTransactionShow[tempTransList.length];
			for (TemporaryTransaction tempTrans : tempTransList) {
				tempTransShowList[i] = new TemporaryTransactionShow();
				tempTransShowList[i].setAmount(tempTrans.getAmount());
				tempTransShowList[i].setCustomer_id(tempTrans.getCustomer_id());
				tempTransShowList[i].setFund_id(tempTrans.getFund_id());
				String haha = "not available";
				try {
					haha = fundDAO.getFundNameById(tempTrans.getFund_id());
				} catch (RollbackException e) {
					// TODO Auto-generated catch block
					haha = "not available";
					//tempTransShowList[i].setFund_name("not available");
				}
				tempTransShowList[i].setFund_name(haha);
				tempTransShowList[i].setShares(tempTrans.getShares());
				tempTransShowList[i].setTransaction_id(tempTrans.getTransaction_id());
				tempTransShowList[i].setTransaction_type(tempTrans.getTransaction_type());
				i++;
			}
		}
			
		if (transShowList == null ) {
			errors.add("There is No Completed Transaction");
		}
		
		if (tempTransShowList == null ) {
			errors.add("There is No Pending Transaction");
		}
		
		request.setAttribute("transShowList", transShowList);
		request.setAttribute("tempTransShowList", tempTransShowList);

		return "viewTransactionHistory.jsp";
	}

}


