/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestCheckForm extends FormBean {
	private String cash = "";
	
	public String getCash() { return cash; }
	
	public void setCash(String s) { cash = s.trim(); }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		
		if (cash == null || cash.length() == 0) {
			errors.add("Amount is required");
		}
		

		if (cash.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Cash may not contain special characters, angle brackets or quotes");
		
		
		
		if (errors.size() > 0) return errors;

		return errors;
	}
}
