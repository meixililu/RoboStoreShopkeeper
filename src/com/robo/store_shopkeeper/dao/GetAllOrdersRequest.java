package com.robo.store_shopkeeper.dao;

public class GetAllOrdersRequest extends CommonRequest {
	private String shopId;
	private int flag;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
