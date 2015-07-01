package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import model.CustFundDAO;
import model.FundDAO;
import model.Model;
import model.TemporaryTransDAO;
import databean.Customer;
import databean.CustomerFund;
import databean.CustomerFundShow;
import databean.Fund;
import formbean.ChangePasswordCustomerForm;
import formbean.LoginForm;

/**
 * Servlet implementation class FundsList
 */
public class SellFundsListAction extends Action {
	
	private TemporaryTransDAO tempDAO;
	private CustFundDAO custFundDAO;
	private FundDAO fundDAO;
	
	public SellFundsListAction(Model model) {

		tempDAO = model.getTempTransDAO();
		custFundDAO = model.getCustFundDAO();
		fundDAO = model.getFundDAO();
	}

	public String getName() { return "sellFundsList.do"; }

	@Override
	public String perform(HttpServletRequest request) {
		
		try {
			Customer customer = (Customer) request.getSession().getAttribute("customer");
			
			if (customer == null) {
				request.setAttribute("form", new LoginForm());
				return "login.jsp";
			}
			
			CustomerFund[] customerFundList = custFundDAO.getCustomerFund(customer.getCustomer_id());
			
			CustomerFundShow[] customerFundShowList = new CustomerFundShow[customerFundList.length];	
			int i = 0;
			for (CustomerFund customerFund : customerFundList) {
				customerFundShowList[i] = new CustomerFundShow();
				customerFundShowList[i].setAvailable_shares(customerFund.getAvailable_shares());
				customerFundShowList[i].setCustomer_id(customerFund.getCustomer_id());
				customerFundShowList[i].setFund_id(customerFund.getFund_id());
				customerFundShowList[i].setFund_name(fundDAO.getFundNameById(customerFund.getFund_id()));
				customerFundShowList[i].setShares(customerFund.getShares());
				customerFundShowList[i].setPrice(fundDAO.getFundPriceById(customerFund.getFund_id()));
				i++;
			}
			
			request.setAttribute("customerFundShowList", customerFundShowList);
			return "fundsListSell.jsp";
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			request.setAttribute("errors", e.getMessage());
			return "fundsListSell.jsp";
		}
		
	}

}
