package com.robo.store_shopkeeper.dao;

public class GetInStrogeInfoRequest extends CommonRequest {
	private String shopId;
	private String instorageId;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getInstorageId() {
		return instorageId;
	}

	public void setInstorageId(String instorageId) {
		this.instorageId = instorageId;
	}
}
