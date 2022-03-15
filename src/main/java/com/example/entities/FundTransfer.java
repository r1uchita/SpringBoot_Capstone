package com.example.entities;

public class FundTransfer {
    private int fromaccountid;
    private int toaccountid;
    private int amount;
    
    public FundTransfer()
    {
    	
    }
    
	public FundTransfer(int fromaccountid, int toaccountid, int amount) {
		this.fromaccountid = fromaccountid;
		this.toaccountid = toaccountid;
		this.amount = amount;
	}

	public int getFromaccountid() {
		return fromaccountid;
	}
	public void setFromaccountid(int fromaccountid) {
		this.fromaccountid = fromaccountid;
	}
	public int getToaccountid() {
		return toaccountid;
	}
	public void setToaccountid(int toaccountid) {
		this.toaccountid = toaccountid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "FundTransfer [fromaccountid=" + fromaccountid + ", toaccountid=" + toaccountid + ", amount=" + amount
				+ "]";
	}
	
    
}
