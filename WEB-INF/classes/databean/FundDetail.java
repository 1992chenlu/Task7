package databean;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("fund_id,price_date")
public class FundDetail implements Comparable<FundDetail> {
	private int fund_id;
	private Date price_date;
	private long price;
	

	/*public int getFph_id() {
		return fph_id;
	}

	public void setFph_id(int fph_id) {
		this.fph_id = fph_id;
	}*/

	public int getFund_id() {
		return fund_id;
	}

	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}

	public Date getPrice_date() {
		return price_date;
	}

	public void setPrice_date(Date price_date) {
		this.price_date = price_date;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public int compareTo(FundDetail fd) {
		// TODO Auto-generated method stub
		return this.price_date.compareTo(fd.price_date);
	}


}
