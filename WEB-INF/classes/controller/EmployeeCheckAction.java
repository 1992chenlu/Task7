package controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.Model;
import model.TemporaryTransDAO;

import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import databean.Customer;
import databean.Employee;
import formbean.LoginForm;

/**
 * Servlet implementation class EmployeeCheckAction
 */
public class EmployeeCheckAction extends Action {
	CustomerDAO customerDAO;
	TemporaryTransDAO tempDAO;
	Customer customer;

	List<String> errors = new ArrayList<String>();

	public EmployeeCheckAction(Model model) {
		
		customerDAO = model.getCustomerDAO();
		tempDAO = model.getTempTransDAO();
	}

	@Override
	public String getName() {
		return "employeeCheck.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		
		Employee employee = (Employee) request.getSession().getAttribute(
				"employee");

		if (employee == null) {
			request.setAttribute("form", new LoginForm());
			return "login.jsp";
		}
		String custID;
		String cash_string = request.getParameter("cash");
		String op = request.getParameter("button");
		if (op == null) {
			custID = request.getParameter("id");
			try {
				customer = customerDAO.read(Integer.parseInt(custID));
				if (customer == null){
					errors.clear();
		    		errors.add("Not a Valid ID. Such Customer does not exist. Please select again.");
		    		request.setAttribute("errors", errors);
		    		request.setAttribute("customerList", customerDAO.match());
		    		return "customerList.jsp";
		    	}
				request.setAttribute("customer", customer);
				
			} catch (NumberFormatException | RollbackException e1) {
				// TODO Auto-generated catch block
				
				errors.clear();
	    		errors.add("Not a Valid ID. Such Customer does not exist. Please select again.");
	    		request.setAttribute("errors", errors);
	    		try {
					request.setAttribute("customerList", customerDAO.match());
					return "customerList.jsp";
				} catch (RollbackException e) {
					// TODO Auto-generated catch block
					errors.clear();
					errors.add(e.getMessage());
					return "manage.jsp";
				}
	    		
			}
			return "deposit.jsp";
		} else {
			custID = request.getParameter("customer_id");
			

			try {
				customer = customerDAO.read(Integer.parseInt(custID));
				if (customer == null){
					errors.clear();
		    		errors.add("Not a Valid ID. Such Customer does not exist. Please select again.");
		    		request.setAttribute("errors", errors);
		    		request.setAttribute("customerList", customerDAO.match());
		    		return "customerList.jsp";
				}
				request.setAttribute("customer", customer);
			} catch (NumberFormatException | RollbackException e1) {
				// TODO Auto-generated catch block
				
				errors.clear();
	    		errors.add("Not a Valid ID. Such Customer does not exist. Please select again.");
	    		request.setAttribute("errors", errors);
	    		try {
					request.setAttribute("customerList", customerDAO.match());
					return "customerList.jsp";
				} catch (RollbackException e) {
					// TODO Auto-generated catch block
					errors.clear();
					errors.add(e.getMessage());
					return "manage.jsp";
				}
			}
		}


		double cash_double;
		int customer_id;
	
		if (cash_string == null) {
			errors.clear();
			errors.add("Missing parameters: amount");
			request.setAttribute("errors", errors);
			request.setAttribute("customer", customer);
			return "deposit.jsp";
		}

		if (custID == null) {
			errors.clear();
			errors.add("Missing parameters: customer_id");
			request.setAttribute("errors", errors);
			return "deposit.jsp";
		}

		if (cash_string.trim().length() == 0) {
			errors.clear();
			errors.add("Amuont field cannot be left blank");
			request.setAttribute("errors", errors);
			request.setAttribute("customer", customer);
			return "deposit.jsp";
		}
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		try {
			cash_double = Double.parseDouble(cash_string);
			if (cash_double <= 0.01) {
				errors.clear();
				errors.add("Amount should be greater than $0.01");
				request.setAttribute("errors", errors);
				request.setAttribute("customer_id", customer);
				return "deposit.jsp";
			}
			
			if (cash_double*100 >= Long.valueOf("100000000000000")) {
				errors.clear();
				errors.add("Amount should be less than: "+nf.format(Long.valueOf("1000000000000")));
				request.setAttribute("errors", errors);
				request.setAttribute("customer_id", customer);
				return "deposit.jsp";
			}
			
		} catch (NumberFormatException e) {
			errors.clear();
			errors.add("Amount has to be numerical value.");
			request.setAttribute("errors", errors);
			request.setAttribute("customer_id", customer);
			return "deposit.jsp";
		}

		request.setAttribute("cash", cash_double);

		if (custID.trim().length() == 0) {
			errors.clear();
			errors.add("Customer ID field cannot be left blank. Please choose again from the list of customers.");
			request.setAttribute("errors", errors);
			return "deposit.jsp";
		}

		try {
			customer_id = Integer.parseInt(custID);
		} catch (NumberFormatException e) {
			errors.clear();
			errors.add("Customer ID has to be integer.");
			request.setAttribute("errors", errors);
			return "deposit.jsp";
		}
		
		

		try {
			tempDAO.depositCheck(customer_id, cash_double);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			errors.clear();
			errors.add(e.getMessage());
			request.setAttribute("errors", errors);
			return "deposit.jsp";
		}
		
		try {
			customerDAO.setAvailableCash(cash_double*100, customer_id);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			errors.clear();
			errors.add(e.getMessage());
			request.setAttribute("errors", errors);
			return "deposit.jsp";
		}
		
		request.setAttribute("errors",
				"The check has been entered for processing.");
		try {
			request.setAttribute("customerList", customerDAO.match());
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errors.clear();
			errors.add(e.getMessage());
			request.setAttribute("errors", errors);
			return "deposit.jsp";
		}
		return "customerList.jsp";

	}

}
