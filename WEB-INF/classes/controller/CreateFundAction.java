package controller;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.FundDAO;
import model.FundPriceDAO;
import model.Model;

import org.genericdao.DAOException;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.Employee;
import databean.Fund;
import databean.FundDetail;
import formbean.CreateFundForm;
import formbean.LoginForm;

/**
 * Servlet implementation class CreateFundAction
 */
public class CreateFundAction extends Action {

	private static FormBeanFactory<CreateFundForm> formBeanFactory = FormBeanFactory
			.getInstance(CreateFundForm.class);

	private FundDAO fundDAO;
	private FundPriceDAO fundPriceDAO;

	public CreateFundAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceDAO = model.getFundPriceDAO();
	}

	public String getName() {
		return "createFund.do";
	}

	public String perform(HttpServletRequest request) {
		try {

			Employee employee = (Employee) request.getSession().getAttribute(
					"employee");

			if (employee == null) {
				request.setAttribute("form", new LoginForm());
				return "login.jsp";
			}

			CreateFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			// presented (we assume for the first time).

			if (!form.isPresent()) {
				return "createFund.jsp";
			}

			List<String> errors = form.getValidationErrors();
			request.setAttribute("errors", errors);
			if (errors.size() > 0)
				return "createFund.jsp";

			Fund fund = new Fund();
			if (fundDAO.isExisted(form.getFundName())) {
				errors.add("Fund with the same name exists, pick a different name");
				request.setAttribute("errors", errors);
				return "createFund.jsp";
			}

			if (fundDAO.isSymbol(form.getFundSym())) {
				errors.add("fund with the same symbol exists, pick a different symbol");
				request.setAttribute("errors", errors);
				return "createFund.jsp";
			}

			// Fund[] fund2 = fundDAO.match(MatchArg.equals("name",
			// form.getFundName()));
			// if (fund2.length>0){
			// errors.add("Fund with the same name exists, pick a different name");
			// request.setAttribute("errors", errors);
			// return "createFund.jsp";
			// }
			//
			// fund2 = fundDAO.match(MatchArg.equals("symbol",
			// form.getFundSym()));
			// if (fund2.length>0){
			// errors.add("fund with the same symbol exists, pick a different symbol");
			// request.setAttribute("errors", errors);
			// return "createFund.jsp";
			// }

			long fund_price = 100 * Long.parseLong(form.getFundPrice());
			if (fund_price <= 0) {
				request.setAttribute("errors",
						"The price entered should be a positive numerical value");
				return "createFund.jsp";
			}

			fund.setName(form.getFundName());
			fund.setSymbol(form.getFundSym());
			fund.setFund_price(fund_price);
			fundDAO.create(fund);

			/*get id of the newly created fund, and update the fund_price_history table*/
			fund = fundDAO.getFund(fundDAO.toDisplayCase(fund.getName()));

			FundDetail fundDetail = new FundDetail();
			fundDetail.setFund_id(fund.getFund_id());
			fundDetail.setPrice(fund_price);
			fundDetail.setPrice_date(new Date());

			fundPriceDAO.create(fundDetail);
			
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);

			request.setAttribute("errors",
					"Fund created: <br> Name: " + fund.getName()
							+ "<br> Fund ID: " + fund.getFund_id()
							+ "<br> Fund Price: $" + nf.format(fund.getFund_price()/100)
							+ "<br> on Date:" + (new Date()));
			request.setAttribute("fund", fund);
			return "createFund.jsp";
		} catch (RollbackException e) {
			request.setAttribute("errors", e.getMessage());
			return "createFund.jsp";
		} catch (FormBeanException e) {
			request.setAttribute("errors", e.getMessage());
			return "createFund.jsp";
		} catch (NumberFormatException e) {
			request.setAttribute("errors",
					"The price entered should be a positive numerical value");
			return "createFund.jsp";
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			request.setAttribute("errors", e.getMessage());
			return "createFund.jsp";
		}
	}

}
