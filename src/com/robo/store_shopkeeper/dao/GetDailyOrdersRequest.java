package com.robo.store_shopkeeper.dao;

public class GetDailyOrdersRequest extends CommonRequest {
	private String shopId;
	private String datetime; // yyyy-MM-dd
	private int flag; // 0线下/1线上
	private int status; // 0正常/1异常

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
