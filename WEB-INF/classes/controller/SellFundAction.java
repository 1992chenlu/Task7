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
import model.FundPriceDAO;
import model.Model;
import model.TemporaryTransDAO;

import org.genericdao.DAOException;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.Customer;
import databean.CustomerFund;
import databean.Fund;
import databean.FundDetail;
import formbean.LoginForm;
import formbean.SellFundForm;

public class SellFundAction extends Action {
	private static FormBeanFactory<SellFundForm> formBeanFactory = FormBeanFactory
			.getInstance(SellFundForm.class);

	private TemporaryTransDAO tempDAO;
	private FundDAO fundDAO;
	private FundPriceDAO fundPriceDAO;
	private int fund_id;
	// private double price;
	private double shares;
	private List<FundDetail> fundDetail = new ArrayList<FundDetail>();
	private CustFundDAO custFundDAO;
	private List<Fund> custFund = new ArrayList<Fund>();

	public SellFundAction(Model model) {

		tempDAO = model.getTempTransDAO();
		fundDAO = model.getFundDAO();
		fundPriceDAO = model.getFundPriceDAO();
		custFundDAO = model.getCustFundDAO();
	}

	public String getName() {
		return "sell.do";
	}

	public String perform(HttpServletRequest request) {
		try {

			Customer customer = (Customer) request.getSession().getAttribute(
					"customer");

			if (customer == null) {
				request.setAttribute("form", new LoginForm());
				return "login.jsp";
			}

			// get the list of funds for the customer, and the prices for each
			// fund
			fundDetail.clear();
			custFund.clear();
			CustomerFund[] customerFund1 = custFundDAO.match(MatchArg.equals(
					"customer_id", customer.getCustomer_id()));
			int f_id = 0;
			for (CustomerFund fund1 : customerFund1) {
				f_id = fund1.getFund_id();
				custFund.add(fundDAO.read(f_id));
				fundDetail.add(fundPriceDAO.read(f_id));
			}

			SellFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			// presented (we assume for the first time).

			if (!form.isPresent()) {
				System.out.println("form is not present");
				request.setAttribute("fundsList", custFund);
				request.setAttribute("fundsDetails", fundDetail);
				return "customerFunds.jsp";
			}

			List<String> errors = form.getValidationErrors();
			request.setAttribute("errors", errors);
			request.setAttribute("fundsList", custFund);
			request.setAttribute("fundsDetails", fundDetail);
			if (errors.size() > 0)
				return "customerFunds.jsp";

			fund_id = Integer.parseInt(form.getFund_id());
			// price = Double.parseDouble(form.getPrice());
			shares = Double.parseDouble(form.getShares());

			if (shares <= 0) {
				errors.add("Shares entered should be greater than zero");
				request.setAttribute("fundsList", custFund);
				request.setAttribute("fundsDetails", fundDetail);
				return "customerFunds.jsp";
			}

			int customer_id = customer.getCustomer_id();
			double custShares = 0;
			for (int i = 0; i < customerFund1.length; i++) {
				if (fund_id == customerFund1[i].getFund_id()) {
					custShares = custShares + customerFund1[i].getShares();
				}
			}
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(3);
			
			nf.setMinimumFractionDigits(3);
			double due = tempDAO.getPendingSell(customer_id, fund_id)/1000;

			if ((custShares) + due < (shares*1000)) {
				errors.add("Shares requested:  " +shares+"  deficit for current number of shares:  "
						+ nf.format(custShares/1000)+" and the queued sell shares:  "+nf.format(due/1000));
				request.setAttribute("errors", errors);
				request.setAttribute("fundsList", custFund);
				request.setAttribute("fundsDetails", fundDetail);
				return "customerFunds.jsp";
			}
			long shares_long = (long) (shares*1000);
			tempDAO.sellFund(fund_id, shares_long, customer_id);

			request.setAttribute("errors",
					"your transaction has been queued for processing");
			return "manage.jsp";

		} catch (RollbackException e) {
			e.printStackTrace();
			request.setAttribute("errors", e.getMessage());
			request.setAttribute("fundsList", custFund);
			request.setAttribute("fundsDetails", fundDetail);
			return "customerFunds.jsp";
		} catch (FormBeanException e) {
			e.printStackTrace();
			request.setAttribute("fundsList", custFund);
			request.setAttribute("fundsDetails", fundDetail);
			request.setAttribute("errors", e.getMessage());
			return "customerFunds.jsp";
		} catch (NumberFormatException e) {
			request.setAttribute("errors",
					"the price and Shares should be double");
			request.setAttribute("fundsList", custFund);
			request.setAttribute("fundsDetails", fundDetail);
			return "customerFunds.jsp";
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errors",
					e.getMessage());
			request.setAttribute("fundsList", custFund);
			request.setAttribute("fundsDetails", fundDetail);
			return "customerFunds.jsp";
		}
	}
}
