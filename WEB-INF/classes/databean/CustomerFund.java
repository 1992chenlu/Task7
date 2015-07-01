package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customer_id,fund_id")

public class CustomerFund {
	
	private int customer_id;
	private int fund_id;
	private long current_shares;
	private long available_shares;
		
	public long getAvailable_shares() {return available_shares;}
	public void setAvailable_shares(long available_shares) {this.available_shares = available_shares;}
	
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
		return current_shares;
	}
	public void setShares(long current_shares) {
		this.current_shares = current_shares;
	}
	
	
}
