package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customer_id,fund_id")

public class CustomerFundShow {
	
//	create table CustFund(customer_id int not null,
//			fund_id int not null, 
//			shares int,  
//			foreign key(customer_id) references Customer(customer_id), 
//			foreign key(fund_id) references Fund(fund_id), 
//			primary key(customer_id, fund_id));
	
	private int customer_id;
	private int fund_id;
	private long shares;
	private long available_shares;
	private String fund_name;
	private long price;

	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	//	
//	public long getAvailable_shares() {return available_shares;}
//	public void setAvailable_shares(long available_shares) {this.available_shares = available_shares;}
//	
//	public int getCustomer_id() {
//		return customer_id;
//	}
//	public void setCustomer_id(int customer_id) {
//		this.customer_id = customer_id;
//	}
//	public int getFund_id() {
//		return fund_id;
//	}
//	public void setFund_id(int fund_id) {
//		this.fund_id = fund_id;
//	}
//	public long getShares() {
//		return shares;
//	}
//	public void setShares(long shares) {
//		this.shares = shares;
//	}
//	public String getFund_name() {
//		return fund_name;
//	}
//	public void setFundName(String s) {
//		this.fund_name = s;
//	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getFund_id() {
		return fund_id;
	}
	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}
	public long getShares() {
		return shares;
	}
	public void setShares(long shares) {
		this.shares = shares;
	}
	public long getAvailable_shares() {
		return available_shares;
	}
	public void setAvailable_shares(long available_shares) {
		this.available_shares = available_shares;
	}
	public String getFund_name() {
		return fund_name;
	}
	public void setFund_name(String fund_name) {
		this.fund_name = fund_name;
	}
	
	
	
}
