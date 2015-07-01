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

import databean.Employee;
import formbean.LoginForm;
import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;
import formbean.CreateEmployeeAccountForm;

public class CreateEmployeeAccountAction extends Action {
	private static FormBeanFactory<CreateEmployeeAccountForm> formBeanFactory = FormBeanFactory
			.getInstance(CreateEmployeeAccountForm.class);

	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;
	
	public CreateEmployeeAccountAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "createEmployeeAccount.do"; }
    
    public String perform(HttpServletRequest request) {
		try {
			
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				request.setAttribute("form", new LoginForm());
				return "login.jsp";
			}
			
			CreateEmployeeAccountForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
	        // presented (we assume for the first time).
			
	        if (!form.isPresent()) {
	            return "createEmployeeAccount.jsp";
	        }
	
	        List<String> errors = form.getValidationErrors();
	        request.setAttribute("errors",errors);
	    	if (errors.size() > 0) return "createEmployeeAccount.jsp";
	    	
	    	if (employeeDAO.isExisted(form.getUsername())) {
	    		errors.add("Username is already taken by others, please use another username");
	    		return "createEmployeeAccount.jsp";
	    	}
	    	
	    	if (customerDAO.isExisted(form.getUsername())) {
	    		errors.add("Username is already taken by others, please use another username");
	    		return "createEmployeeAccount.jsp";
	    	}
	    	
	    	Employee newEmployee = new Employee();
	    	newEmployee.setFirstname(form.getFirstname());
	    	newEmployee.setLastname(form.getLastname());
	    	newEmployee.setPassword(form.getPassword());
	    	newEmployee.setUsername(form.getUsername());
	    	employeeDAO.create(newEmployee);

			request.setAttribute("errors","New employee user: "+newEmployee.getUsername() + " created");
	    	return "manage.jsp";
		} catch (RollbackException e) {
        	e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	return "createEmployeeAccount.jsp";
		} catch (FormBeanException e) {
        	e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	return "createEmployeeAccount.jsp";
		}
    }
}
