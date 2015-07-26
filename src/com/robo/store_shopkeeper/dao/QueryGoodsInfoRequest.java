package com.robo.store_shopkeeper.dao;

public class QueryGoodsInfoRequest extends CommonRequest {
	private String goodsBarcode;

	public String getGoodsBarcode() {
		return goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}
}
