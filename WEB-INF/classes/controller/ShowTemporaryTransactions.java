package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustFundDAO;
import model.FundDAO;
import model.Model;
import model.TemporaryTransDAO;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.Fund;
import databean.TemporaryTransaction;
import databean.TemporaryTransactionShow;

/**
 * Servlet implementation class FundsList
 */
public class ShowTemporaryTransactions extends Action {
	
	private FundDAO fundDAO;
	private TemporaryTransDAO tempDAO;
	private CustFundDAO custFundDAO;
	
	public ShowTemporaryTransactions(Model model) {
		fundDAO = model.getFundDAO();
		tempDAO = model.getTempTransDAO();
	}

	public String getName() { return "showTemporary.do"; }

	@Override
	public String perform(HttpServletRequest request) {
		int id;
		try {
		
			TemporaryTransaction tempTrans[] = tempDAO.match();
			List<TemporaryTransactionShow> showTemp = new ArrayList<TemporaryTransactionShow>();
			showTemp.clear();
			TemporaryTransactionShow tempShow;
			
			for (TemporaryTransaction temp : tempTrans){
				tempShow = new TemporaryTransactionShow();
				tempShow.setAmount(temp.getAmount());
				tempShow.setCustomer_id(temp.getCustomer_id());
				tempShow.setExecute_date(temp.getExecute_date());
				tempShow.setFund_id(temp.getFund_id());
				id = temp.getFund_id();
				if (id == 0){
					tempShow.setFund_name("----");
				} else{
					tempShow.setFund_name(fundDAO.read(temp.getFund_id()).getName());
				}
				tempShow.setShares(temp.getShares());
				System.out.println(temp.getShares());
				tempShow.setTransaction_id(temp.getTransaction_id());
				tempShow.setTransaction_type(temp.getTransaction_type());
				
				
				showTemp.add(tempShow);
			}
			
			for(TemporaryTransactionShow show: showTemp){
				System.out.println(show.getAmount());
				System.out.println(show.getCustomer_id());
				System.out.println(show.getFund_name());
				System.out.println(show.getTransaction_type());
				System.out.println("This is transactions id: "+show.getTransaction_id());
			}
			
			request.setAttribute("showTemp", showTemp);
			return "temporaryTransactions.jsp";
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			request.setAttribute("errors", e.getMessage());
			return "manage.jsp";
		}
		
		
		
	}

}
