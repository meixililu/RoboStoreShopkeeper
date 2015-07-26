package com.robo.store_shopkeeper.dao;

public class GetShopStorageListVo {
	private String goodsBarcode;
	private String goodsName;
	private int storageCount;
	private String machineId;
	private int machineCount;

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

	public int getStorageCount() {
		return storageCount;
	}

	public void setStorageCount(int storageCount) {
		this.storageCount = storageCount;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public int getMachineCount() {
		return machineCount;
	}

	public void setMachineCount(int machineCount) {
		this.machineCount = machineCount;
	}
}
