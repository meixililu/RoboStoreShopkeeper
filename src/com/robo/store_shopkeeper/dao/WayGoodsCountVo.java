package com.robo.store_shopkeeper.dao;

public class WayGoodsCountVo {
	private String wayId;
	private int wayGoodsCount;

	public WayGoodsCountVo() {

	}

	public WayGoodsCountVo(String wayId, int wayGoodsCount) {
		this.wayId = wayId;
		this.wayGoodsCount = wayGoodsCount;
	}

	public String getWayId() {
		return wayId;
	}

	public void setWayId(String wayId) {
		this.wayId = wayId;
	}

	public int getWayGoodsCount() {
		return wayGoodsCount;
	}

	public void setWayGoodsCount(int wayGoodsCount) {
		this.wayGoodsCount = wayGoodsCount;
	}
}
