/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.Customer;
import databean.Employee;
import formbean.LoginForm;
import formbean.ResetCustomerPasswordForm;

public class ResetCustomerPasswordAction extends Action {
	private static FormBeanFactory<ResetCustomerPasswordForm> formBeanFactory = FormBeanFactory
			.getInstance(ResetCustomerPasswordForm.class);

	private CustomerDAO customerDAO;
	int customer_id = 0;
	List<String> errors = new ArrayList<String>();
	
	public ResetCustomerPasswordAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "resetCustomerPassword.do"; }
    
    public String perform(HttpServletRequest request) {
		try {
			
			
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				request.setAttribute("form", new LoginForm());
				return "login.jsp";
			}
			
			
	    	Customer customer = null;
			
			ResetCustomerPasswordForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
	        // presented (we assume for the first time).
			
	        if (!form.isPresent()) {
	        	String id = (String) request.getParameter("id");
				if (id == null) {
					errors.clear();
					errors.add("No customer valid id is provided, please select a customer.");
					System.out.println("No customer valid id is provided, please select a customer.");
					request.setAttribute("errors", errors);
		    		request.setAttribute("customerList", customerDAO.match());
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
					errors.clear();
					errors.add("No customer valid id provided, please select a customer.");
					System.out.println("No customer valid id provided, please select a customer.");
					request.setAttribute("errors", errors);
		    		request.setAttribute("customerList", customerDAO.match());
					return "customerList.jsp";//Redirect to customer list
				}
				
	        	request.setAttribute("customer_id", customer_id);
	            return "resetCustomerPassword.jsp";
	        }
	        errors.clear();
	       errors  = form.getValidationErrors();
	        request.setAttribute("errors",errors);
	    	if (errors.size() > 0){ 
	    		
	    		request.setAttribute("customer_id", customer_id);
	    		return "resetCustomerPassword.jsp";
	    		
	    	}
	    	
	    	try {
				customer_id = Integer.parseInt(form.getCustomer_id());
			} catch (NumberFormatException ex) {
				errors.clear();
				errors.add("No valid customer id provided, please select a customer.");
				System.out.println("No valid customer id provided, please select a customer.");
				request.setAttribute("errors", errors);
	    		request.setAttribute("customerList", customerDAO.match());
				return "customerList.jsp";//Redirect to customer list
			}
	    	
	    	customer = customerDAO.read(customer_id);
	    	
	    	
	    	
	    	customerDAO.setPassword(customer.getUsername(), form.getNewPassword());
	    	
			request.setAttribute("errors","Password changed for customer: "+customer.getLastname()+" , "+customer.getFirstname());
			try {
				request.setAttribute("customerList", customerDAO.match());
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errors.add(e.getMessage());
				request.setAttribute("errors", errors);
				return "manage.jsp";
			}
	    	return "customerList.jsp";
		} catch (RollbackException e) {
        	e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	return "resetCustomerPassword.jsp";
		} catch (FormBeanException e) {
        	e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	return "resetCustomerPassword.jsp";
		}
    }
}
