package com.robo.store_shopkeeper.dao;

public class MachineGoodsCountVo {
	private String machineId;
	private int goodsCount;

	public MachineGoodsCountVo() {

	}

	public MachineGoodsCountVo(String machineId, int goodsCount) {
		this.machineId = machineId;
		this.goodsCount = goodsCount;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
}
