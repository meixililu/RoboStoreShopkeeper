package com.robo.store_shopkeeper.dao;

public class GoodsUndoRequest extends CommonRequest {
	private String shopId;
	private String undoId;
	private String deliveryId;

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

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
}
