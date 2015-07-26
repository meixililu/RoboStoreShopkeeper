package com.robo.store_shopkeeper.dao;

import java.util.List;

public class GetCashOrderInfoResponse extends CommonResponse {
	private String settleId;
	private int checkOutStatus;
	private String date; // yyyy-MM-dd
	private double amount;
	private List<GetCashOrderInfoVo> list;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public List<GetCashOrderInfoVo> getList() {
		return list;
	}

	public void setList(List<GetCashOrderInfoVo> list) {
		this.list = list;
	}
}
