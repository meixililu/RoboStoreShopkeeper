package com.robo.store_shopkeeper.dao;

public class AllMallPickupGoodsVo {
	private String datetime; // yyy-MM-dd
	private double price;

	public AllMallPickupGoodsVo() {

	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
