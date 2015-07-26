package com.robo.store_shopkeeper.dao;

public class GetDamageInfoResponse extends CommonResponse {
	private String damageId;
	private String datetime; // yyyy-MM-dd HH:mm:ss
	private String goodsBarcode;
	private String goodsName;
	private int count;
	private String deliveryId;
	private String coId;
	private String coName;
	private String goodsMemo;
	private String damageMemo;

	public String getDamageId() {
		return damageId;
	}

	public void setDamageId(String damageId) {
		this.damageId = damageId;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getGoodsBarcode() {
		return goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public String getCoName() {
		return coName;
	}

	public void setCoName(String coName) {
		this.coName = coName;
	}

	public String getGoodsMemo() {
		return goodsMemo;
	}

	public void setGoodsMemo(String goodsMemo) {
		this.goodsMemo = goodsMemo;
	}

	public String getDamageMemo() {
		return damageMemo;
	}

	public void setDamageMemo(String damageMemo) {
		this.damageMemo = damageMemo;
	}
}
