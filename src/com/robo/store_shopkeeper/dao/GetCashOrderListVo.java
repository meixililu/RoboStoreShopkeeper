package com.robo.store_shopkeeper.dao;

public class GetCashOrderListVo {
	private String date; // yyyy-MM-dd
	private String settleId;
	private int checkOutStatus; // 0：未结算1：已结算
	private double amount;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSettleId() {
		return settleId;
	}

	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}

	public int getCheckOutStatus() {
		return checkOutStatus;
	}

	public void setCheckOutStatus(int checkOutStatus) {
		this.checkOutStatus = checkOutStatus;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
