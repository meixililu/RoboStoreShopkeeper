package com.robo.store_shopkeeper.dao;

public class GetCashOrderInfoRequest extends CommonRequest {
	private String shopId;
	private String settleId;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getSettleId() {
		return settleId;
	}

	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}
}
