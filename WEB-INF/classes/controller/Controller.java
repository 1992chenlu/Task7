/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;
import databean.Customer;
import databean.Employee;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		Model model = null;
		try {
			model = new Model(getServletConfig());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (model != null) {
			Action.add(new LoginAction(model));
			Action.add(new ChangePasswordEmployeeAction(model));
			Action.add(new CreateEmployeeAccountAction(model));
			Action.add(new CreateCustomerAccountAction(model));
			Action.add(new ResetCustomerPasswordAction(model));
			Action.add(new ViewCustomerAccountAction(model));
			Action.add(new LogoutEmployeeAction());
			Action.add(new UnauthorizedAction());
			Action.add(new ChangePasswordCustomerAction(model));
			Action.add(new LogoutCustomerAction());
			Action.add(new ViewAccountAction(model));
			Action.add(new CreateFundAction(model));
			Action.add(new EmployeeCheckAction(model));
			Action.add(new RequestCheckAction(model));
			Action.add(new FundsList(model));
			Action.add(new BuyFundCustomerAction(model));
			Action.add(new TransitionDayAction(model));
			Action.add(new SellFundCustomerAction(model));
			Action.add(new SellFundsListAction(model));
			Action.add(new ViewTransactionHistoryAction(model));
			Action.add(new ResearchFundAction(model));
			Action.add(new ShowTemporaryTransactions(model));
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = performTheAction(request);
		sendToNextPage(nextPage, request, response);
	}

	/*
	 * Extracts the requested action and (depending on whether the user is
	 * logged in) perform it (or make the user login).
	 * 
	 * @param request
	 * 
	 * @return the next page (the view)
	 */
	private String performTheAction(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		// User user = (User) session.getAttribute("user");
		Employee employee = (Employee) session.getAttribute("employee");
		Customer customer = (Customer) session.getAttribute("customer");
		String action = getActionName(servletPath);

		// System.out.println("servletPath="+servletPath+" requestURI="+request.getRequestURI()+"  user="+user);

		if (action.equals("login.do") || (employee == null && customer == null)) {
			// Allow these actions without logging in
			return Action.perform(action, request);
		}

		if (customer != null
				&& (action.equals("changePasswordCustomer.do")
						|| action.equals("viewAccount.do")
						|| action.equals("buy.do")
						|| action.equals("fundsList.do")
						|| action.equals("sell.do")
						|| action.equals("manage.do")
						|| action.equals("requestCheck.do")
						|| action.equals("sell.do")
						|| action.equals("sellFundsList.do")
						|| action.equals("transactionHistory.do")
						|| action.equals("researchFundCust.do") || action
							.equals("logoutCustomer.do"))) {
			return Action.perform(action, request);
		}

		if (employee != null
				&& (action.equals("changePasswordEmployee.do")
						|| action.equals("createEmployeeAccount.do")
						|| action.equals("createFund.do")
						|| action.equals("transition.do")
						|| action.equals("viewAccount.do")
						|| action.equals("transactionHistory.do")
						|| action.equals("showTemporary.do")
						|| action.equals("employeeCheck.do")
						|| action.equals("createCustomerAccount.do")
						|| action.equals("resetCustomerPassword.do")
						|| action.equals("viewCustomerAccount.do") || action
							.equals("logoutEmployee.do"))) {
			return Action.perform(action, request);
		}

		// Ignore unauthorized action
		List<String> errors = new ArrayList<String>();
		errors.add("You tried to access an unauthorized page");
		request.setAttribute("errors", errors);
		return Action.perform("unauthorized.do", request);
	}

	/*
	 * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
	 * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
	 * page (the view) This is the common case
	 */
	private void sendToNextPage(String nextPage, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					request.getServletPath());
			return;
		}

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher("WEB-INF/"
					+ nextPage);
			d.forward(request, response);
			return;
		}

		// response.sendRedirect(response.encodeRedirectURL(nextPage));

		throw new ServletException(Controller.class.getName()
				+ ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
	}

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
	private String getActionName(String path) {
		// We're guaranteed that the path will start with a slash
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
}
