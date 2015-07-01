package controller;

import java.text.NumberFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.Model;
import model.TemporaryTransDAO;

import org.genericdao.DAOException;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.Customer;
import formbean.LoginForm;
import formbean.RequestCheckForm;

/**
 * Servlet implementation class DepositCheckAction
 */
public class RequestCheckAction extends Action {
	private static FormBeanFactory<RequestCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(RequestCheckForm.class);

	RequestCheckForm form;
	TemporaryTransDAO temp;
	CustomerDAO customerDAO;

	public RequestCheckAction(Model model) {

		temp = model.getTempTransDAO();
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "requestCheck.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		Customer customer = (Customer) request.getSession().getAttribute(
				"customer");

		if (customer == null) {
			request.setAttribute("form", new LoginForm());
			return "login.jsp";
		}

		try {
			form = formBeanFactory.create(request);
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		request.setAttribute("form", form);
		// presented (we assume for the first time).

		if (!form.isPresent()) {
			System.out.println("form not present");
			return "requestCheck.jsp";
		}

		List<String> errors = form.getValidationErrors();
		request.setAttribute("errors", errors);
		if (errors.size() > 0)
			return "requestCheck.jsp";
		double cash = 0;
		int id = 0;
		try {
			cash = Double.parseDouble(form.getCash());
			if (cash <= 0.01) {
				errors.add("Amount should be a numerical value without any comma greater than $0.01");
				request.setAttribute("errors", errors);
				return "requestCheck.jsp";
			}
			
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);

			id = customer.getCustomer_id();
			double balance = customer.getCash();
			double due = customer.getAvailable_cash();
			if (due < ((cash * 100))) {
				errors.add("Amount deficit with Current Cash Balance:  $"
						+ nf.format(balance / 100) + " and Available Balance:  $"
						+ nf.format(due / 100));
				request.setAttribute("errors", errors);
				return "requestCheck.jsp";
			}
			
			if (Long.valueOf("1000000000000")< ((cash * 100))) {
				errors.add("Amount should be less than:  $"
						+ nf.format(Long.valueOf("1000000000000")));
				request.setAttribute("errors", errors);
				return "requestCheck.jsp";
			}

		} catch (NumberFormatException e) {
			errors.add("Amount should be a numerical value.");
			request.setAttribute("errors", errors);
			return "requestCheck.jsp";
		}

		try {
			temp.requestCheck(id, cash);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
			request.setAttribute("errors", errors);
			return "requestCheck.jsp";
		}
		
		try {
			customerDAO.setAvailableCash((0-cash*100), id);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
			request.setAttribute("errors", errors);
			return "requestCheck.jsp";
		}
		request.setAttribute("errors",
				"The withdrawal has been queued for transaction.");
		try {
			customer = customerDAO.read(customer.getCustomer_id());
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("customer", customer);
		return "manage.jsp";

	}

}
