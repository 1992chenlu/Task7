/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustFundDAO;
import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceDAO;
import model.Model;
import model.TemporaryTransDAO;
import model.TransactionDAO;

import org.genericdao.RollbackException;
//import org.genericdao.Transaction;

import org.genericdao.Transaction;

import databean.Customer;
import databean.CustomerFund;
import databean.Employee;
import databean.Fund;
import databean.FundDetail;
import databean.TemporaryTransaction;
import formbean.LoginForm; 
import formbean.TransitionDayForm;

public class TransitionDayAction extends Action {
	private FundDAO fundDAO;
	private FundPriceDAO fundPriceDAO;
	private TemporaryTransDAO tempTransDAO;
	private CustomerDAO custDAO;
	private CustFundDAO custFundDAO;
	private TransactionDAO transDAO;
	private Fund[] fund;
	private FundDetail[] fundDetail;
	private TemporaryTransaction[] tempTrans;

	private static DateFormat df;

	public TransitionDayAction(Model model) {
		custDAO = model.getCustomerDAO();
		custFundDAO = model.getCustFundDAO();
		tempTransDAO = model.getTempTransDAO();
		transDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		fundPriceDAO = model.getFundPriceDAO();
	}

	public String getName() {
		return "transition.do";
	}

	public String perform(HttpServletRequest request) {
		try {
			
			Employee employee = (Employee) request.getSession().getAttribute(
					"employee");

			if (employee == null) {
				request.setAttribute("form", new LoginForm());
				return "login.jsp";
			}

			TransitionDayForm form = new TransitionDayForm(request);
			request.setAttribute("form", form);
			// presented (we assume for the first time).
			fund = fundDAO.match();
			
			fundDetail = fundPriceDAO.match();
			

			Date oldDate = fundPriceDAO.getMaximumDate();

			List<String> errors = new ArrayList<String>();
			request.setAttribute("errors", errors);
			request.setAttribute("fundsList", fund);
			request.setAttribute("fundsDetails", fundDetail);
			if (errors.size() > 0)
				return "transitionDay.jsp";

			NumberFormat formatter = new DecimalFormat("#0.00");
			HashMap<Integer, String> price_map = new HashMap<Integer, String>();
			df = new SimpleDateFormat("MM/dd/yyyy");

			System.out.println("Old Date:" + oldDate);

			for (Fund fb : fund) {
				if (oldDate == null) {
					price_map.put(fb.getFund_id(), "N/A");
					continue;
				}
				FundDetail tmp = fundPriceDAO.read(fb.getFund_id(), oldDate);
				if (tmp == null) {
					price_map.put(fb.getFund_id(), "N/A");
				} else {
					price_map.put(fb.getFund_id(),
							formatter.format(tmp.getPrice() / 100.0));
				}
			}
			request.setAttribute("price_map", price_map);
			request.setAttribute("lastClosingDate", oldDate);

			if (!form.isPresent()) {
				System.out.println("form is not present");
				return "transitionDay.jsp";
			}

		
			Date newDate;
			try {
				newDate = df.parse(form.getDate());
			} catch (Exception e) {
				errors.add("Please enter a valid date with MM/DD/YYYY format.");
				return "transitionDay.jsp";
			}
			System.out.println("New Date:" + newDate);

			if (newDate.compareTo(oldDate) < 0
					|| newDate.compareTo(oldDate) == 0) {
				errors.add("Date should be greater than the previous trading day.");
				return "transitionDay.jsp";
			}

			// Any validation errors?
			HashMap<String, String> map = new HashMap<String, String>();
			for (Fund fb : fund) {
				map.put("fund_" + fb.getFund_id(),
						request.getParameter("fund_" + fb.getFund_id()));
			}
			errors.addAll(form.getValidationErrors(map));

			if (errors.size() != 0) {
				return "transitionDay.jsp";
			}

			// Update closing price in fund_price_hsitory and fund;
			
			for (Fund fb : fund) {
				
				//create rows in fund price dao
				
				long price = (long) Math.round(Double.parseDouble(request
						.getParameter("fund_" + fb.getFund_id())) * 100);
				fundPriceDAO.createPrice(fb.getFund_id(), price, newDate);
				
				//update fund price in fund table.
				fundDAO.updatePrice(fb.getFund_id(),price,fb.getName(),fb.getSymbol());

			}
			tempTrans = tempTransDAO.match();
			if (tempTrans.length == 0) { 
				 errors.add("No pending transactions to process.");
				 request.setAttribute("price_map", price_map);
					request.setAttribute("lastClosingDate", oldDate);
				 return "transitionDay.jsp";
			}
			
	//		Transaction.begin();
			
			
			// Process pending transactions from temporary_transaction;
			
			for (TemporaryTransaction tempTransBean : tempTrans) {
				if (Transaction.isActive()) {
						Transaction.rollback();
				}
				Customer cb = custDAO.read(tempTransBean.getCustomer_id());
				CustomerFund cfb = custFundDAO.read(
						tempTransBean.getCustomer_id(),
						tempTransBean.getFund_id());
				switch (tempTransBean.getTransaction_type()) {
				case "SELL_FUND":
					// long shares;
					System.out.println("Inside case sell_fund:");
					Transaction.begin();
					double tempTransShares = Math
							.abs(tempTransBean.getShares());

					if (cfb != null) {

						if (cfb.getShares() - tempTransShares == 0) {
							custFundDAO.delete(cfb);
						} else {
							cfb.setShares((long) (cfb.getShares() - tempTransShares));
							cfb.setAvailable_shares(cfb.getShares());
							custFundDAO.update(cfb);
						}
						double price = fundPriceDAO.read(
								tempTransBean.getFund_id(), newDate).getPrice();
						double amount_double = ((Math.round(price) * (tempTransShares / 1000)));
						// long amount = (long) (price *
						// tempTransBean.getShares());
						if (amount_double<1){
							cfb.setAvailable_shares((long) (cfb.getAvailable_shares() + tempTransShares));
							custFundDAO.update(cfb);
						}
						long amount = (long) amount_double;
						cb.setCash(cb.getCash() + amount);
						cb.setAvailable_cash(cb.getCash());

						custDAO.update(cb);
						tempTransBean.setAmount(amount);
					}
					break;

				case "BUY_FUND":
					System.out.println("Inside case buy_fund:");
					Transaction.begin();
					long shares = 0;
					double amount = Math.abs(tempTransBean.getAmount());
					double price = fundPriceDAO.read(
							tempTransBean.getFund_id(), newDate).getPrice();
					shares = (long) Math.round((amount / price) * 1000);
					if (shares < 1) {
						// errors.add("The number of shares purchased for this amount < 0.001. Amount:  "+
						// (amount / 100.00)
						// +"  refunded with 0 shares purchased.");
						//cb.setCash(cb.getCash() + (long) amount);
						cb.setAvailable_cash(cb.getAvailable_cash()+(long) amount);
						custDAO.update(cb);

						tempTransBean.setTransaction_type("REFUND_AMOUNT"); 
						
						break;
					}


					if (cfb == null) {

						cfb = new CustomerFund();
						cfb.setCustomer_id(tempTransBean.getCustomer_id());
						cfb.setFund_id(tempTransBean.getFund_id());
						cfb.setShares(shares);
						cfb.setAvailable_shares(shares);
						custFundDAO.create(cfb);

					} else {

						cfb.setShares((long) (shares + cfb.getShares()));
						cfb.setAvailable_shares((long) (cfb.getShares()));
						custFundDAO.update(cfb);

					}

					tempTransBean.setShares(shares); // as this transition bean
														// is copied to
														// transaction table

					// update the customer cash (reduce it)
					cb.setCash(cb.getCash() + tempTransBean.getAmount());
					cb.setAvailable_cash(cb.getCash());
					custDAO.update(cb);
					break;

				case "DEPOSIT_CHECK":
					System.out.println("Inside case deposit check:");
					Transaction.begin();
					cb.setCash(cb.getCash() + tempTransBean.getAmount());
					cb.setAvailable_cash(cb.getCash());
					custDAO.update(cb);
					break;

				case "REQUEST_CHECK":
					System.out.println("Inside case request check:");
					Transaction.begin();
					cb.setCash(cb.getCash() + tempTransBean.getAmount());
					cb.setAvailable_cash(cb.getCash());
					custDAO.update(cb);
					break;
				}
				
				transDAO.createRow(tempTransBean.getAmount(),tempTransBean.getCustomer_id(),newDate,tempTransBean.getFund_id(),tempTransBean.getShares(),tempTransBean
						.getTransaction_type());

				tempTransDAO.delete(tempTransBean.getTransaction_id());
				Transaction.commit();
			}
			request.setAttribute("errors", "Successfully updated records!");
			return "manage.jsp";
		} catch (RollbackException e) {
			e.printStackTrace();
			request.setAttribute("errors", e.getMessage());
			request.setAttribute("transitionDay", fund);
			request.setAttribute("fundsDetails", fundDetail);
			return "transitionDay.jsp";
		} catch (NumberFormatException e) {
			request.setAttribute("errors",
					"The price should be a numerical value.");
			request.setAttribute("transitionDay", fund);
			request.setAttribute("fundsDetails", fundDetail);
			return "transitionDay.jsp";
		} 
	}
 }