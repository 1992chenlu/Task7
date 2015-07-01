/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.Customer;
import formbean.ChangePasswordCustomerForm;
import formbean.LoginForm;
import model.CustomerDAO;
import model.Model;

public class ChangePasswordCustomerAction extends Action {
	private static FormBeanFactory<ChangePasswordCustomerForm> formBeanFactory = FormBeanFactory
			.getInstance(ChangePasswordCustomerForm.class);

	private CustomerDAO customerDAO;
	
	public ChangePasswordCustomerAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "changePasswordCustomer.do"; }
    
    public String perform(HttpServletRequest request) {
		try {
			
			Customer customer = (Customer) request.getSession().getAttribute("customer");
		
			if (customer == null) {
				request.setAttribute("form", new LoginForm());
				return "login.jsp";
			}
			
			ChangePasswordCustomerForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
	        // presented (we assume for the first time).
			
	        if (!form.isPresent()) {
	            return "changePasswordCustomer.jsp";
	        }
	
	        List<String> errors = form.getValidationErrors();
	        request.setAttribute("errors",errors);
	    	if (errors.size() > 0) return "changePasswordCustomer.jsp";
	    	
	    	customerDAO.setPassword(customer.getUsername(), form.getNewPassword());
			
			request.setAttribute("errors","Password changed for customer: "+customer.getUsername());
	    	return "manage.jsp";
		} catch (RollbackException e) {
        	e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	return "changePasswordCustomer.jsp";
		} catch (FormBeanException e) {
        	e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	return "changePasswordCustomer.jsp";
		}
    }
}
