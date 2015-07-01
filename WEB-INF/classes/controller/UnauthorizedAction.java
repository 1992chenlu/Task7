/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package controller;

import javax.servlet.http.HttpServletRequest;



public class UnauthorizedAction extends Action {

	public String getName() { return "unauthorized.do"; }
    
    public String perform(HttpServletRequest request) {
			return "manage.jsp";
    }
}
