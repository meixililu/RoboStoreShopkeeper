package com.robo.store_shopkeeper.dao;

public class GetAllOrdersVo {
	private String datetime; 	// yyyy-MM-dd
	private double price; 		// 金额

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
