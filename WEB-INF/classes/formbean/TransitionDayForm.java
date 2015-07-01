package formbean;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


public class TransitionDayForm /* extends FormBean*/ {
	private String date;
	private String button;

	public TransitionDayForm(HttpServletRequest request) {
		button = request.getParameter("button");
		date	= request.getParameter("date");
	}

	public String getDate ()					{	return date;		}
	public String getbutton()		 			{	return button;	}
	
	public boolean isPresent() {	return button != null; }
	
	// HashMap<String, Long> map: 
	// String:	    name in the jsp, related to fundid
	// String:		price
	public List<String> getValidationErrors (HashMap<String, String> map) {
		List<String> errors = new ArrayList<String>();

		if (date == null || date.length() == 0 )
			errors.add("Date cannot be empty.");
		if (button == null) errors.add("Button is required.");

		if (errors.size() > 0) 	return errors;
        if (!button.equals("setclosing")) errors.add("Invalid button.");
        
        try {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        	dateFormat.setLenient(false);
        	dateFormat.parse(date);
        } catch (Exception e) {
        	errors.add("Please enter a valid date with MM/DD/YYYY format.");
        }
        
        for (String price : map.values()) {
        		try {
					if (price == null || price.length() == 0) {
						errors.add("Price should be a positive number");
					break; }
					double d = Double.parseDouble(price.replace(",", ""));
					if (d <= 0.01) {
						errors.add("Price cannot be less than $0.01. ");
					 	break;
					}
					if (d > Long.MAX_VALUE) {
						errors.add("Price cannot be greater than $" + Long.MAX_VALUE/100 + ".00");
					 	break;
					}
				} catch (NumberFormatException e) {
					errors.add("Invalid closing price.");
				}
        }
//        if (button.matches("*[<>,/? `~:;{}()'\"]*"))
//			errors.add("Button may not contain special characters, angle brackets or quotes");
        if (date.matches(".*[<>,? `~:;{}()'\"].*"))
			errors.add("Date may not contain special characters, angle brackets or quotes");
		return errors;
	}
}