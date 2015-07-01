/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateCustomerAccountForm extends FormBean {
	private String username = "";
	private String firstname = "";
	private String lastname = "";
	private String confirmPassword = "";
	private String password     = "";
	private String addr_line1 = "";
	private String addr_line2 = "";
	private String city = "";
	private String state = "";
	private String zip = "";
	
	
	public String getConfirmPassword() { return confirmPassword; }
	public String getPassword()     { return password;     }
	public String getUsername() {return username;}
	public String getFirstname() {return firstname;}
	public String getLastname() {return lastname;}
	public String getAddr_line1() {return addr_line1;}
	public String getAddr_line2() {return addr_line2;}
	public String getCity() {return city;}
	public String getState() {return state;}
	public String getZip() {return zip;}
	
	public void setConfirmPassword(String s) { confirmPassword = s.trim(); }
	public void setPassword(String s)     { password     = s.trim(); }
	public void setUsername(String s) {username = s.trim();}
	public void setFirstname(String s) {firstname = s.trim();}
	public void setLastname(String s) {lastname = s.trim();}
	public void setAddr_line1(String s) {addr_line1 = s.trim();}
	public void setAddr_line2(String s) {addr_line2 = s.trim();}
	public void setCity(String s) {city = s.trim();}
	public void setState(String s) {state = s.trim();}
	public void setZip(String s) {zip = s.trim();}
	
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
		
		if (addr_line1.length() == 0) {
			errors.add("Address Line1 is required");
		}
		
		if (state.length() == 0) {
			errors.add("State is required");
		}
		
		if (city.length() == 0) {
			errors.add("City is required");
		}
		

		if (addr_line1.matches(".*[<>,/? `'~:;{}()\"].*"))
			errors.add("Address line 1 may not contain special characters, angle brackets or quotes");
		
		if (confirmPassword.matches(".*[<>,/?' `~:;{}()\"].*"))
			errors.add("Confirm Password may not contain special characters, angle brackets or quotes");

		if (username.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Username may not contain special characters, angle brackets or quotes");
		
		if (firstname.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("First name may not contain special characters,  angle brackets or quotes");

		if (lastname.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Last name may not contain angle brackets or quotes");
		
		if (password.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Password may not contain special characters, angle brackets or quotes");

		if (addr_line2.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Address line 2 may not contain special characters, angle brackets or quotes");
		
		if (city.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("City may not contain special characters, angle brackets or quotes");

		if ( state.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("State may not contain special characters, angle brackets or quotes");
		
		if (zip.matches(".*[<>,/? `~:;{}()'\"].*"))
			errors.add("Zip Code may not contain special characters, angle brackets or quotes");
		
		return errors;
	}
}
