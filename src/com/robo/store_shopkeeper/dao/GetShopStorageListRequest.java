package com.robo.store_shopkeeper.dao;

public class GetShopStorageListRequest extends CommonRequest {
	private String shopId;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
}
