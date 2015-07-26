package com.robo.store_shopkeeper.dao;


public class QueryGoodsInfoResponse extends CommonResponse {
	private String goodsBarcode;
	private String goodsName;
	private String goodsMemo;

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

	public String getGoodsMemo() {
		return goodsMemo;
	}

	public void setGoodsMemo(String goodsMemo) {
		this.goodsMemo = goodsMemo;
	}
}
