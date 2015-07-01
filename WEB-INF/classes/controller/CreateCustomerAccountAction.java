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
import databean.Employee;
import formbean.LoginForm;
import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;
import formbean.CreateCustomerAccountForm;

public class CreateCustomerAccountAction extends Action {
	private static FormBeanFactory<CreateCustomerAccountForm> formBeanFactory = FormBeanFactory
			.getInstance(CreateCustomerAccountForm.class);

	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;
	
	public CreateCustomerAccountAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "createCustomerAccount.do"; }
    
    public String perform(HttpServletRequest request) {
		try {
			
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				request.setAttribute("form", new LoginForm());
				return "login.jsp";
			}
			
			CreateCustomerAccountForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
	        // presented (we assume for the first time).
			
	        if (!form.isPresent()) {
	            return "createCustomerAccount.jsp";
	        }
	
	        List<String> errors = form.getValidationErrors();
	        request.setAttribute("errors",errors);
	    	if (errors.size() > 0) return "createCustomerAccount.jsp";
	    	
	    	if (customerDAO.isExisted(form.getUsername())) {
	    		errors.add("Username is already taken by others, please use another username");
	    		return "createCustomerAccount.jsp";
	    	}
	    	
	    	if (employeeDAO.isExisted(form.getUsername())) {
	    		errors.add("Username is already taken by others, please use another username");
	    		return "createCustomerAccount.jsp";
	    	}
	    	
	    	Customer newCustomer = new Customer();
	    	newCustomer.setAddr_line1(form.getAddr_line1());
	    	newCustomer.setAddr_line2(form.getAddr_line2());
	    	newCustomer.setCash(0);
	    	newCustomer.setCity(form.getCity());
	    	newCustomer.setFirstname(form.getFirstname());
	    	newCustomer.setLastname(form.getLastname());
	    	newCustomer.setPassword(form.getPassword());
	    	newCustomer.setState(form.getState());
	    	newCustomer.setUsername(form.getUsername());
	    	newCustomer.setZip(form.getZip());
	    	newCustomer.setAvailable_cash(0);

	    	customerDAO.create(newCustomer);

			request.setAttribute("errors","New customer user: "+newCustomer.getUsername() + " created");
	    	return "manage.jsp";
		} catch (RollbackException e) {
        	e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	return "createCustomerAccount.jsp";
		} catch (FormBeanException e) {
        	e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	return "createCustomerAccount.jsp";
		}
    }
}
