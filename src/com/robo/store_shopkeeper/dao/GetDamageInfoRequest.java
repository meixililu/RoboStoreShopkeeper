package com.robo.store_shopkeeper.dao;

public class GetDamageInfoRequest extends CommonRequest {
	private String shopId;
	private String damageId;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getDamageId() {
		return damageId;
	}

	public void setDamageId(String damageId) {
		this.damageId = damageId;
	}
}
