package com.robo.store_shopkeeper.dao;

public class GetCashOrderListRequest extends CommonRequest {
	private String shopId;
	private int checkOutStatus; // 0：未结算 1：已结算 9：全部
	private int pageIndex;
	private int pageCount;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public int getCheckOutStatus() {
		return checkOutStatus;
	}

	public void setCheckOutStatus(int checkOutStatus) {
		this.checkOutStatus = checkOutStatus;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
