/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.TemporaryTransDAO;

import org.genericdao.DAOException;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.Customer;
import databean.Fund;
import formbean.BuyFundForm;
import formbean.LoginForm;

public class BuyFundCustomerAction extends Action {
	private static FormBeanFactory<BuyFundForm> formBeanFactory = FormBeanFactory
			.getInstance(BuyFundForm.class);

	private TemporaryTransDAO tempDAO;
	private FundDAO fundDAO;
	private CustomerDAO customerDAO;
	
	private int fund_id;
	// private double price;
	private double amount;
	private Fund fund;

	public BuyFundCustomerAction(Model model) {

		tempDAO = model.getTempTransDAO();
		fundDAO = model.getFundDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "buy.do";
	}

	public String perform(HttpServletRequest request) {
		try {
			
			Customer customer = (Customer) request.getSession().getAttribute("customer");
		
			if (customer == null) {
				request.setAttribute("form", new LoginForm());
				return "login.jsp";
			}
			
			 List<String> errors = new ArrayList<String>();
			
			BuyFundForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
			String fund_id1 = "";
	        // presented (we assume for the first time).
			 if (!form.isPresent()) {
		        	System.out.println("Form is not present");
		        	fund_id1 = request.getParameter("id");
					if (fund_id1 == null||fund_id1.trim().length()==0){
						 errors.add("Fund ID is missing. Please Select the fund you wish to buy again.");
						 request.setAttribute("errors",errors);
					     return "fundsList.do";
					}
					fund_id = Integer.parseInt(fund_id1);
					fund = fundDAO.read(fund_id);
		        	request.setAttribute("fund", fund);
		            return "buy.jsp";
		        }	       
	
	        errors = form.getValidationErrors();
	        request.setAttribute("errors",errors);
	        request.setAttribute("fund", fund);
	       
	    	if (errors.size() > 0) return "buy.jsp";
	    	
	    	fund_id = Integer.parseInt(form.getFund_id());// add check conditions here
	    	
	    	try{
	    	amount = Double.parseDouble(form.getCash());
	    	}catch(NumberFormatException e){
	    		errors.add("Amount entered should be a numerical value greater than $0.01");
	    		request.setAttribute("fund", fund);
	        	return "buy.jsp";
	    	}
	    	if (amount<=0.01){
	    		errors.add("Amount entered should be greater than $0.01");
	    		request.setAttribute("fund", fund);
	        	return "buy.jsp";
	    	}
	    	
	    	int customer_id = customer.getCustomer_id();			
			double cash = customer.getCash()/100;
			double cash_available = customer.getAvailable_cash();
			
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			if (cash_available < amount*100) {
				errors.add("Amount deficit with Current Cash Balance:  $"
						+ nf.format(cash/100) + "  and Available Cash Balance:  $"
						+ nf.format(cash_available/100));
				request.setAttribute("errors", errors);
				request.setAttribute("fund", fund);
	        	return "buy.jsp";
			}
			
			if (amount*100 >= Long.valueOf("1000000000000")*100) {
				errors.add("Amount cannot be greater than"+ nf.format(Long.valueOf("1000000000000"))+ "<br> Your Current Cash Balance:  $"
						+ nf.format(cash/100) + "  and Available Cash Balance:  $"
						+ nf.format(cash_available/100));
				request.setAttribute("errors", errors);
				request.setAttribute("fund", fund);
	        	return "buy.jsp";
			}
			
			
			
			tempDAO.buyFund(fund_id, amount, customer_id);
			
			customerDAO.setAvailableCash((0-amount*100), customer_id);					
			request.setAttribute("errors","Your transaction has been queued for processing.");
			try {
				customer = customerDAO.read(customer.getCustomer_id());
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
	    	return "manage.jsp";
		} catch (RollbackException e) {
        	e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	request.setAttribute("fund", fund);
        	return "buy.jsp";
		} catch (FormBeanException e) {
			e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	request.setAttribute("fund", fund);
        	return "buy.jsp";
		} catch (NumberFormatException e){
			e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	request.setAttribute("fund", fund);
        	return "buy.jsp";
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	request.setAttribute("fund", fund);
        	return "buy.jsp";
		}
    }
}
