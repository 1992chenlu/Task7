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
import model.CustFundDAO;
import model.FundDAO;
import model.Model;
import model.TemporaryTransDAO;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
import databean.Customer;
import databean.CustomerFund;
import databean.CustomerFundShow;
import formbean.LoginForm;
import formbean.SellFundForm;

public class SellFundCustomerAction extends Action {
	private static FormBeanFactory<SellFundForm> formBeanFactory = FormBeanFactory
			.getInstance(SellFundForm.class);

	private TemporaryTransDAO tempDAO;
	private FundDAO fundDAO;
	private CustFundDAO custFundDAO;
	private int fund_id = 0;
	CustomerFundShow customerFundShow = new CustomerFundShow();
	
	// private double price;
	private long shares;

	public SellFundCustomerAction(Model model) {
		tempDAO = model.getTempTransDAO();
		custFundDAO = model.getCustFundDAO();
		fundDAO = model.getFundDAO();
	}

	public String getName() {
		return "sell.do";
	}

	public String perform(HttpServletRequest request) {
		
		try {
			
			Customer customer = (Customer) request.getSession().getAttribute("customer");
			if (customer == null) {
				request.setAttribute("form", new LoginForm());
				return "login.jsp";
			}
			
			List<String> errors = new ArrayList<String>();
			//parameter validation
			
			
			
			//form and form content validation
			SellFundForm form;
			form = formBeanFactory.create(request);
			
			request.setAttribute("form",form);
			if (!form.isPresent()) {
				String fund_id1 = request.getParameter("id");
				if (fund_id1 == null || fund_id1.trim().length() == 0){
					errors.add("Fund ID is missing. Please Select the fund you wish to buy again.");
					request.setAttribute("errors",errors);
					return "sellFundsList.do";
				}
				try {
					fund_id = Integer.parseInt(fund_id1);
				} catch (NumberFormatException e) {
					errors.add("Fund ID is missing. Please Select the fund you wish to buy again.");
					request.setAttribute("errors",errors);
					return "sellFundsList.do";
				}
				//Set Customer Fund show session
				CustomerFund customerFund = custFundDAO.getCustomerFund(fund_id, customer.getCustomer_id());
				customerFundShow.setAvailable_shares(customerFund.getAvailable_shares());
				customerFundShow.setCustomer_id(customerFund.getCustomer_id());
				customerFundShow.setFund_id(customerFund.getFund_id());
				customerFundShow.setFund_name(fundDAO.getFundNameById(fund_id));
				customerFundShow.setShares(customerFund.getShares());
				customerFundShow.setPrice(fundDAO.getFundPriceById(fund_id));
				request.setAttribute("customerFundShow", customerFundShow);
			    return "sell.jsp";
			}
			errors = form.getValidationErrors();
	        request.setAttribute("errors",errors);
	        
	    	if (errors.size() > 0) {
	    		request.setAttribute("customerFundShow", customerFundShow);
	    		return "sell.jsp";
	    	}
				
			//Share amount validation
		    try{
		    	double haha = Double.parseDouble(form.getShares()) * 1000;
		    	System.out.println("this is form's shares * 1000"+haha);
		    	shares = (long)Math.round(haha);
		    	//shares = ;//01/27/2015 changed
		    }catch(NumberFormatException e){
		    	errors.add("Value of shares is invalid. It should be numerical value.");
		    	request.setAttribute("customerFundShow", customerFundShow);
		        return "sell.jsp";
		    }
		    
		    if (shares < 1){
		    	errors.add("Shares entered should be greater than 0.001");
		    	request.setAttribute("customerFundShow", customerFundShow);
		       	return "sell.jsp";
		    }
		    
		    //Calculation and change temp table
		    NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(3);
			nf.setMinimumFractionDigits(3);
			int customer_id = customer.getCustomer_id();
			long availableShares;
	
			availableShares = custFundDAO.getAvailableShares(fund_id, customer_id);
			
			if (availableShares < shares) {
				errors.add("Amount deficit with Current Share Available:  "
							+ nf.format((double)availableShares / 1000));
				request.setAttribute("errors", errors);
				request.setAttribute("customerFundShow", customerFundShow);
		        return "sell.jsp";
			}
			tempDAO.sellFund(fund_id, shares, customer_id);
			custFundDAO.setCustomerFund(fund_id, customer_id, availableShares - shares);
			
					
			request.setAttribute("errors","Your transaction has been queued for processing.");
//			customer = customerDAO.read(customer.getCustomer_id());
//			HttpSession session = request.getSession();
//			session.setAttribute("customer", customer);
		    return "manage.jsp";
		} catch (RollbackException e) {
        	e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	request.setAttribute("customerFundShow", customerFundShow);
        	return "sell.jsp";
		} catch (FormBeanException e) {
			e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	request.setAttribute("customerFundShow", customerFundShow);
        	return "sell.jsp";
		} catch (NumberFormatException e){
			e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	request.setAttribute("customerFundShow", customerFundShow);
        	return "sell.jsp";
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	request.setAttribute("customerFundShow", customerFundShow);
        	return "sell.jsp";
		}
		
    }
}
