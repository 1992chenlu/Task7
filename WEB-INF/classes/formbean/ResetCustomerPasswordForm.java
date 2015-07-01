/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ResetCustomerPasswordForm extends FormBean {
	
	private String customer_id ="";
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id.trim();
	}

	private String confirmPassword = "";
	private String newPassword     = "";
	
	
	public String getConfirmPassword() { return confirmPassword; }
	public String getNewPassword()     { return newPassword;     }
	
	public void setConfirmPassword(String s) { confirmPassword = s.trim(); }
	public void setNewPassword(String s)     { newPassword     = s.trim(); }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (newPassword == null || newPassword.length() == 0) {
			errors.add("New Password is required");
		}
		
		if (customer_id == null || customer_id.length() == 0) {
			errors.add("customer id is required");
		}
		
		if (confirmPassword == null || confirmPassword.length() == 0) {
			errors.add("Confirm Pwd is required");
		}
		
		if (errors.size() > 0) return errors;
		
		if (!newPassword.equals(confirmPassword)) {
			errors.add("New Password and Confirm Pwd do not match");
		}

		if (newPassword.matches(".*[<> `~:;{}()'\"].*"))
			errors.add("New Password may not contain special characters, and angle brackets or quotes");
		
		if (confirmPassword.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Confirm Password may not contain special characters, angle brackets or quotes");
		
		if (customer_id.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Customer ID may not contain special characters, angle brackets or quotes");
		

		return errors;
	}
}
