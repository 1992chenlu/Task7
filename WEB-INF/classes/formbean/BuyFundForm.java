package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyFundForm extends FormBean {
	private String fund_id;
	private String cash;
		
	
	public String getFund_id() {
		return fund_id;
	}
	public void setFund_id(String fund_id) {
		this.fund_id = fund_id.trim();
	}
	
	public String getCash() {
		return cash;
	}
	public void setCash(String cash) {
		this.cash = cash.trim();
	}
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fund_id == null || fund_id.length() == 0) {
			errors.add("Fund id is required");
		}
		
		if (cash == null || cash.length() == 0) {
			errors.add("Amount is required");
		}
		
		if (cash.matches(".*[<>,/? `~:;{}()''\"].*"))
			errors.add("Cash may not contain special characters, angle brackets or quotes");
		
		if (fund_id.matches(".*[<>,/? ~`;:''\"].*"))
			errors.add("Fund ID may not contain special characters, angle brackets or quotes");
		
		if (errors.size() > 0) return errors;
		
		return errors;
	}
}
