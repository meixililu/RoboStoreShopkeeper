package com.robo.store_shopkeeper.dao;

public class GetRadioRequest extends CommonRequest {
	private String shopId;
	private String month;	// yyyy-MM

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}
