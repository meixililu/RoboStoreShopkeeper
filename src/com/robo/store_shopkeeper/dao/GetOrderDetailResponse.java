package com.robo.store_shopkeeper.dao;

import java.util.List;

public class GetOrderDetailResponse extends CommonResponse {
	private String datetime; // yyyy-MM-dd HH:mm:ss
	private double price; // 金额
	private String machineId;
	private String orderId;
	private List<GetOrderDetailGoodVo> list;

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

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<GetOrderDetailGoodVo> getList() {
		return list;
	}

	public void setList(List<GetOrderDetailGoodVo> list) {
		this.list = list;
	}
}
