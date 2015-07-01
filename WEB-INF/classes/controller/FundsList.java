package controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import model.FundDAO;
import model.Model;
import model.TemporaryTransDAO;
import databean.Fund;

/**
 * Servlet implementation class FundsList
 */
public class FundsList extends Action {
	
	private TemporaryTransDAO tempDAO;
	private FundDAO fundDAO;
	
	public FundsList(Model model) {

		tempDAO = model.getTempTransDAO();
		fundDAO = model.getFundDAO();
		
	}

	public String getName() { return "fundsList.do"; }

	@Override
	public String perform(HttpServletRequest request) {
		
		try {
			Fund[] fundsList = fundDAO.match();
			request.setAttribute("fundsList", fundsList);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			request.setAttribute("errors", e.getMessage());
			return "fundsList.jsp";
		}
		
		return "fundsList.jsp";
		
	}

}
