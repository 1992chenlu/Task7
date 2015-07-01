package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellFundForm extends FormBean{
	private String fund_id;
	private String shares;
	
	
	public String getFund_id() {
		return fund_id;
	}
	public void setFund_id(String fund_id) {
		this.fund_id = fund_id.trim();
	}
	public String getShares() {
		return shares;
	}
	public void setShares(String shares) {
		this.shares = shares.trim();
	}
	
	public List<String> getValidationErrors(){
		List<String> errors = new ArrayList<String>();
		
		if(fund_id == null || fund_id.length() == 0){
			errors.add("Fund id is required");
		}
		
		if (shares == null || shares.length() == 0){
			errors.add("shares are required");
		}

		if (fund_id.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Fund ID may not contain special characters, angle brackets or quotes");
		
		if (shares.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Shares may not contain special characters, angle brackets or quotes");
		
		if(errors.size()>0) return errors;
		
		return errors;
	}
	
}
