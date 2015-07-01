/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("fund_id")
public class Fund {

	private int fund_id;
	private String name;
	private String symbol;
	private long fund_price;
	
	public long getFund_price() {
		return fund_price;
	}

	public void setFund_price(long fund_price) {
		this.fund_price = fund_price;
	}

	public int getFund_id() {
		return fund_id;
	}

	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
