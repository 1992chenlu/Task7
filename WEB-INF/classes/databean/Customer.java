/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customer_id")
public class Customer {
	
	private int customer_id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String addr_line1;
	private String addr_line2;
	private String city;
	private String state;
	private String zip;
	private long cash;
	private long available_cash;
			
	public long getAvailable_cash() {return available_cash;}
	public void setAvailable_cash(long available_cash) {this.available_cash = available_cash;}
	public void setCustomer_id(int customer_id) {this.customer_id = customer_id;}
	public void setUsername(String username) {this.username = username;}
	public void setPassword(String password) {this.password = password;}
	public void setFirstname(String firstname) {this.firstname = firstname;}
	public void setLastname(String lastname) {this.lastname = lastname;}
	public void setAddr_line1(String addr_line1) {this.addr_line1 = addr_line1;}
	public void setAddr_line2(String addr_line2) {this.addr_line2 = addr_line2;}
	public void setCity(String city) {this.city = city;}
	public void setState(String state) {this.state = state;}
	public void setZip(String zip) {this.zip = zip;}
	public void setCash(long cash) {this.cash = cash;}
	
	public int getCustomer_id() {return customer_id;}
	public String getUsername() {return username;}
	public String getPassword() {return password;}
	public String getFirstname() {return firstname;}
	public String getLastname() {return lastname;}
	public String getAddr_line1() {return addr_line1;}
	public String getAddr_line2() {return addr_line2;}
	public String getCity() {return city;}
	public String getState() {return state;}
	public String getZip() {return zip;}
	public long getCash() {return cash;}

}
