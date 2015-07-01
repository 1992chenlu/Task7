/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.Customer;
import databean.Employee;
import formbean.ViewCustomerAccountForm;
import formbean.LoginForm;
import model.CustomerDAO;
import model.Model;

public class ViewCustomerAccountAction extends Action {
	private static FormBeanFactory<ViewCustomerAccountForm> formBeanFactory = FormBeanFactory
			.getInstance(ViewCustomerAccountForm.class);

	private CustomerDAO customerDAO;

	public ViewCustomerAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "viewCustomerAccount.do"; }
    
    public String perform(HttpServletRequest request) {
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				request.setAttribute("form", new LoginForm());
				return "login.jsp";
			}
			
			List<String> errors = new ArrayList<String>();
			try {
				Customer[] customerList = customerDAO.match();
				request.setAttribute("customerList", customerList);
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				errors.add(e.getMessage());
				return "manage.jsp";
			}
			return "customerList.jsp";
    }
}
