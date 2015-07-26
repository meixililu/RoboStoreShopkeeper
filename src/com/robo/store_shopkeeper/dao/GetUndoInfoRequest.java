package com.robo.store_shopkeeper.dao;

public class GetUndoInfoRequest extends CommonRequest {
	private String shopId;
	private String undoId;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getUndoId() {
		return undoId;
	}

	public void setUndoId(String undoId) {
		this.undoId = undoId;
	}
}
