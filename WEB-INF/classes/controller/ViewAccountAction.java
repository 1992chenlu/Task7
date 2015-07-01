/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustFundDAO;
import model.CustomerDAO;
import model.FundDAO;
import model.Model;

import org.genericdao.RollbackException;

import databean.Customer;
import databean.CustomerFund;
import databean.CustomerFundShow;
import databean.Employee;
import formbean.LoginForm;

public class ViewAccountAction extends Action {

	int customer_id = -1;
	CustomerDAO customerDAO;
	Customer customerInfo;
	CustFundDAO custFundDAO;
	FundDAO fundDAO;
	
	public ViewAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();
		custFundDAO = model.getCustFundDAO();
		fundDAO = model.getFundDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "viewAccount.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		
		if (employee == null && customer == null){
			request.setAttribute("form", new LoginForm());
			return "login.jsp";
		}
		
		List<String> errors = new ArrayList<String>();
		
		Customer[] customerList;
		
		try {
			//Get customer list
			customerList = customerDAO.match();
			request.setAttribute("customerList", customerList);
			
			//Initialize customer id
			if (customer == null) {
				String id = (String) request.getParameter("id");
				if (id == null) {
					errors.add("No customer valid id is provided, please select a customer.");
					request.setAttribute("errors", errors);
					System.out.println("No customer valid id is provided, please select a customer.");
					return "customerList.jsp";//Redirect to customer list
				}
				try {
					customer_id = Integer.parseInt(id);
					customer = customerDAO.read(customer_id);
					if (customer == null){
						errors.clear();
			    		errors.add("Not a Valid ID. Such Customer does not exist. Please select again.");
			    		request.setAttribute("errors", errors);
			    		request.setAttribute("customerList", customerDAO.match());
			    		return "customerList.jsp";
			    	}
				} catch (NumberFormatException ex) {
					errors.add("No customer valid id provided, please select a customer.");
					System.out.println("No customer valid id provided, please select a customer.");
					request.setAttribute("errors", errors);
					return "customerList.jsp";//Redirect to customer list
				}
			} else {
				customer_id = customer.getCustomer_id();
			}
			
		
			//get customer object and set as attribute
			customerInfo = customerDAO.read(customer_id);
			if (customerInfo == null) {
				errors.add("No such customer with user id:" + Integer.toString(customer_id));
				System.out.println("No such customer with user id:" + Integer.toString(customer_id));
				return "customerList.jsp";//Redirect to customer list
			}
			request.setAttribute("customerInfo", customerInfo);
		
		
			//Get customer fund list and set as attribute
			CustomerFund[] customerFundList;
		
			customerFundList = custFundDAO.getCustomerFund(customer_id);
			CustomerFundShow[] customerFundShowList = new CustomerFundShow[customerFundList.length];	
			int i = 0;
			for (CustomerFund customerFund : customerFundList) {
				customerFundShowList[i] = new CustomerFundShow();
				customerFundShowList[i].setAvailable_shares(customerFund.getAvailable_shares());
				customerFundShowList[i].setCustomer_id(customerFund.getCustomer_id());
				customerFundShowList[i].setFund_id(customerFund.getFund_id());
				try {
					customerFundShowList[i].setFund_name(fundDAO.getFundNameById(customerFund.getFund_id()));
				} catch (RollbackException e) {
					// TODO Auto-generated catch block
					customerFundShowList[i].setFund_name("Not Available");
					e.printStackTrace();
				}
				customerFundShowList[i].setShares(customerFund.getShares());
				i++;
			}
			request.setAttribute("customerFundShowList", customerFundShowList);
			
			return "viewAccount.jsp";
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errors.add(e.toString());
			return "viewAccount.jsp";
		} 
	}

}
