package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateFundForm extends FormBean {
	private String fundName = "";
	private String fundSym = "";
	private String fundPrice = "";
	
	public String getFundPrice() {
		return fundPrice;
	}

	public void setFundPrice(String fundPrice) {
		this.fundPrice = fundPrice.trim();
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName.trim();
	}

	public String getFundSym() {
		return fundSym;
	}

	public void setFundSym(String fundSym) {
		this.fundSym = fundSym.trim();
	}

	public List<String> getValidationErrors() {
			List<String> errors = new ArrayList<String>();
	
			if (fundName == null || fundName.length() == 0) {
				errors.add("Fund name is required");
			}
			
			if (fundSym == null || fundSym.length() == 0) {
				errors.add("Fund symbol is required");
			}
			
			if (fundPrice == null || fundPrice.length()==0){
				errors.add("Fund price is required");
			}
			

			if (fundName.matches(".*[<>,/? `~:;{}()'\"].*"))
				errors.add("Fund name may not contain special characters, angle brackets or quotes");
			
			if (fundSym.matches(".*[<>,/? `~:;{}()'\"].*"))
				errors.add("Fund Symbol may not contain special characters, angle brackets or quotes");

			if (fundPrice.matches(".*[<>,/? `~:;{}()'\"].*"))
				errors.add("Fund price may not contain special characters, angle brackets or quotes");
			
			
			if (errors.size() > 0) return errors;
	
			return errors;
		}
}
