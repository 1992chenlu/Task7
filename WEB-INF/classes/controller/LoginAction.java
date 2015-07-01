/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.Customer;
import databean.Employee;
import formbean.LoginForm;
import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;


public class LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
//	private UserDAO userDAO;
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;

	public LoginAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "login.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
            	
    	// If user is already logged in, redirect to todolist.do
        if (session.getAttribute("employee") != null || session.getAttribute("customer") != null) {
        	return "manage.jsp";//change
        }
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
//        	request.setAttribute("userList",userDAO.getUsers());
        	
	    	LoginForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "login.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "login.jsp";
	        }

	        // Look up the employee
	        Employee employee = employeeDAO.read(form.getUsername());

	        // Check the employee password
	        if (employee != null && !employee.getPassword().equals(form.getPassword())) {
	        	errors.add("Incorrect password");
	        	return "login.jsp";
	        }
	        
	        //Lookup the customer
	        Customer customer = customerDAO.read(customerDAO.getCustomerIdByUsername(form.getUsername()));
	        
	        if (customer != null && !customer.getPassword().equals(form.getPassword())) {
	        	errors.add("Incorrect password");
	        	return "login.jsp";
	        }
	        
	        //No such username in both customer and employee table
	        if (employee == null && customer == null) {
	        	errors.add("username not exist");
	        	return "login.jsp";
	        }
	
	        // Attach (this copy of) the user bean to the session
	        session.setAttribute("employee", employee);
	        session.setAttribute("customer", customer);
	        
	        // If redirectTo is null, redirect to the "todolist" action
			return "manage.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "login.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "login.jsp";
        }
    }
}
