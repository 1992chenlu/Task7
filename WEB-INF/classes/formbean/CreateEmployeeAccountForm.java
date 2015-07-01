/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateEmployeeAccountForm extends FormBean {
	private String username = "";
	private String firstname = "";
	private String lastname = "";
	private String confirmPassword = "";
	private String password     = "";
	
	public String getConfirmPassword() { return confirmPassword; }
	public String getPassword()     { return password;     }
	public String getUsername() {return username;}
	public String getFirstname() {return firstname;}
	public String getLastname() {return lastname;}
	
	public void setConfirmPassword(String s) { confirmPassword = s.trim(); }
	public void setPassword(String s)     { password     = s.trim(); }
	public void setUsername(String s) {username = s.trim();}
	public void setFirstname(String s) {firstname = s.trim();}
	public void setLastname(String s) {lastname = s.trim();}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (username.length() == 0) {
			errors.add("Username is required");
		}
		
		if (firstname.length() == 0) {
			errors.add("First name is required");
		}
		
		if (lastname.length() == 0) {
			errors.add("Last name is required");
		}

		if (password.length() == 0) {
			errors.add("New Password is required");
		}
		
		if (confirmPassword.length() == 0) {
			errors.add("Confirm Pwd is required");
		}
		
		if (errors.size() > 0) return errors;
		
		if (!password.equals(confirmPassword)) {
			errors.add("Password and Confirm Pwd do not match");
		}

		if (password.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Password may not contain special characters, angle brackets or quotes");
		
		if (confirmPassword.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Confirm Password may not contain special characters, angle brackets or quotes");
		
		if ( firstname.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("First Name may not contain special characters, angle brackets or quotes");
		
		if (lastname.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Last name may not contain special characters, angle brackets or quotes");
		
		if (username.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Username may not contain special characters, angle brackets or quotes");
		
		
		return errors;
	}
}
