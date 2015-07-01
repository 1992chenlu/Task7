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
import formbean.ChangePasswordEmployeeForm;
import formbean.LoginForm;
import model.EmployeeDAO;
import model.Model;

public class ChangePasswordEmployeeAction extends Action {
	private static FormBeanFactory<ChangePasswordEmployeeForm> formBeanFactory = FormBeanFactory
			.getInstance(ChangePasswordEmployeeForm.class);

	private EmployeeDAO employeeDAO;
	
	public ChangePasswordEmployeeAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() { return "changePasswordEmployee.do"; }
    
    public String perform(HttpServletRequest request) {
		try {
			
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				request.setAttribute("form", new LoginForm());
				return "login.jsp";
			}
			
			ChangePasswordEmployeeForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
	        // presented (we assume for the first time).
			
	        if (!form.isPresent()) {
	            return "changePasswordEmployee.jsp";
	        }
	
	        List<String> errors = form.getValidationErrors();
	        request.setAttribute("errors",errors);
	    	if (errors.size() > 0) return "changePasswordEmployee.jsp";
	    	
	    	

	    	employeeDAO.setPassword(employee.getUsername(), form.getNewPassword());
			
			request.setAttribute("errors","Password changed for employee: "+employee.getUsername());
	    	return "manage.jsp";
		} catch (RollbackException e) {
        	e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	return "changePasswordEmployee.jsp";
		} catch (FormBeanException e) {
        	e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	return "changePasswordEmployee.jsp";
		}
    }
}
