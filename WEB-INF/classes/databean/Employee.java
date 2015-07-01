/*
J               * Team 1
 * Task 7
 * 01/15/2015
 */
package databean;

import org.genericdao.PrimaryKey;


@PrimaryKey("username")
public class Employee {
	
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	
	public String getUsername() {return username;}
	public String getPassword() {return password;}
	public String getFirstname() {return firstname;}
	public String getLastname() {return lastname;}
	
	public void setUsername(String username) {this.username = username;}
	public void setPassword(String password) {this.password = password;}
	public void setFirstname(String firstname) {this.firstname = firstname;}
	public void setLastname(String lastname) {this.lastname = lastname;}

}