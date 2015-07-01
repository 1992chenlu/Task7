/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutCustomerAction extends Action {

	public String getName() { return "logoutCustomer.do"; }

	public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.removeAttribute("customer");
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        return "login.jsp";
    }
}
