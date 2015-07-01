/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import databean.Customer;
import databean.Fund;
import databean.FundDetail;
import databean.FundDetailString;
import formbean.LoginForm;
import model.FundDAO;
import model.FundPriceDAO;
import model.Model;

public class ResearchFundAction extends Action {
	// private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private FundPriceDAO fundPriceDAO;
	private Fund[] fund;
	private FundDetail[] fundDetail;
	private List<String> errors = null;

	public ResearchFundAction(Model model) {
		// customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		fundPriceDAO = model.getFundPriceDAO();
	}

	public String getName() {
		return "researchFundCust.do";
	}

	public String perform(HttpServletRequest request) {
		try {
			errors = new ArrayList<String>();

			Customer customer = (Customer) request.getSession().getAttribute(
					"customer");

			if (customer == null) {
				request.setAttribute("form", new LoginForm());
				return "login.jsp";
			}
			String fundString = request.getParameter("fund_id");
			System.out.println("fundString" + fundString);

			// presented (we assume for the first time).
			fund = fundDAO.match();
			fundDetail = fundPriceDAO.match();

			Date oldDate = fundPriceDAO.getMaximumDate();

			List<String> errors = new ArrayList<String>();
			request.setAttribute("errors", errors);
			request.setAttribute("fundsList", fund);
			request.setAttribute("fundsDetails", fundDetail);

			NumberFormat formatter = new DecimalFormat("#0.00");
			HashMap<Integer, String> price_map = new HashMap<Integer, String>();

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

			if (fundString == null) {
				System.out.println("form is not present");
				return "researchFundCustomer.jsp";
			}

			int fundInt = Integer.parseInt(fundString);

			Fund individualFundBean = fundDAO.read(fundInt);

			if (individualFundBean == null) {
				errors.add("Fund is not available.");
				request.setAttribute("errors", errors);
				return "researchFundCustomer.jsp";
			}
			FundDetail[] individualFundDetailBean = fundPriceDAO.match(MatchArg
					.equals("fund_id", individualFundBean.getFund_id()));

			ArrayList<FundDetailString> individualFundDetailStringBean = new ArrayList<FundDetailString>();

			for (int i = 0; i < individualFundDetailBean.length; i++) {
				FundDetailString fds = new FundDetailString();
				fds.setFund_id(String.valueOf(individualFundDetailBean[i]
						.getFund_id()));
				fds.setPrice(String.valueOf(individualFundDetailBean[i]
						.getPrice()));

				Date tempDate = individualFundDetailBean[i].getPrice_date();
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				String dateString = format.format(tempDate);
				fds.setPrice_date(dateString);
				individualFundDetailStringBean.add(fds);
			}

			request.setAttribute("funds", individualFundBean);
			request.setAttribute("fundsDetails", individualFundDetailStringBean);
			return "researchIndividualFundCustomer.jsp";

		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			errors.add("Fund is not available.");
			request.setAttribute("errors", errors);
			return "researchFundCustomer.jsp";
		} 
		return null;
	}
}
