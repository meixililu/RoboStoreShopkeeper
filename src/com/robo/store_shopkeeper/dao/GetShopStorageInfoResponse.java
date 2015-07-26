package com.robo.store_shopkeeper.dao;

import java.util.List;

public class GetShopStorageInfoResponse extends CommonResponse {
	private String goodsBarcode;
	private String goodsName;
	private String coId;
	private String coName;
	private int storageCount;
	private String machineId;
	private List<WayGoodsCountVo> list;

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

	public List<WayGoodsCountVo> getList() {
		return list;
	}

	public void setList(List<WayGoodsCountVo> list) {
		this.list = list;
	}
}
