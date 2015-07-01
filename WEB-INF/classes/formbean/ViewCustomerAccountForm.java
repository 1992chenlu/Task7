/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ViewCustomerAccountForm extends FormBean {
	private String customerUsername = "";
	
	public String getCustomerUsername() { return customerUsername; }

	public void setCustomerUsername(String s) {customerUsername = s.trim(); }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (customerUsername == null || customerUsername.length() == 0) {
			errors.add("Customer's username is required");
		}

		if (customerUsername.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Username may not contain special characters, angle brackets or quotes");
		
		
		return errors;
	}
}
