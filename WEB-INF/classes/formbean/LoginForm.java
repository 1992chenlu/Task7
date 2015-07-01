/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean {
	private String username;
	private String password;
	private String action;
	
	public String getUsername() {return username;}
	public String getPassword() {return password;}
	public String getAction() {return action;}
	
	public void setUsername(String s) {username = s.trim();}
	public void setPassword(String s) {password = s.trim();}
	public void setAction(String s)  { action = s.trim(); }
	

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (username == null || username.length() == 0) {
			errors.add("Username is required");
		}

		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}

		if (username.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Username may not contain special characters, angle brackets or quotes");
		
		if (password.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Password may not contain special characters, angle brackets or quotes");
		return errors;
	}
}
