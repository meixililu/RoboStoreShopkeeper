package com.robo.store_shopkeeper.dao;

public class GoodsDamageRequest extends CommonRequest {
	private String goodsBarcode;
	private int count;
	private String deliveryId;
	private String coId;
	private String damageMemo;

	public String getGoodsBarcode() {
		return goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getCoId() {
		return coId;
	}

	public void setCoId(String coId) {
		this.coId = coId;
	}

	public String getDamageMemo() {
		return damageMemo;
	}

	public void setDamageMemo(String damageMemo) {
		this.damageMemo = damageMemo;
	}
}
