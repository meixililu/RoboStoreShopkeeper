package com.robo.store_shopkeeper.dao;

public class GetInStrogeListVo {
	private String instorageId;
	private String datetime; // yyyy-MM-dd HH:mm:ss
	private String goodsName;
	private int count;
	private String deliveryId;

	public String getInstorageId() {
		return instorageId;
	}

	public void setInstorageId(String instorageId) {
		this.instorageId = instorageId;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
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
}
