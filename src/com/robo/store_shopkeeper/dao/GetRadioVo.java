package com.robo.store_shopkeeper.dao;

public class GetRadioVo {
	private String time;
	private String price; // 合计金额
	private String shopinRentRadio; // 店铺内场地租金分成
	private String adRatio; // 广告分成
	private String goodsWayRadio; // 货道租金分成
	private String selfProfitRadio; // 自营分成

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getShopinRentRadio() {
		return shopinRentRadio;
	}

	public void setShopinRentRadio(String shopinRentRadio) {
		this.shopinRentRadio = shopinRentRadio;
	}

	public String getAdRatio() {
		return adRatio;
	}

	public void setAdRatio(String adRatio) {
		this.adRatio = adRatio;
	}

	public String getGoodsWayRadio() {
		return goodsWayRadio;
	}

	public void setGoodsWayRadio(String goodsWayRadio) {
		this.goodsWayRadio = goodsWayRadio;
	}

	public String getSelfProfitRadio() {
		return selfProfitRadio;
	}

	public void setSelfProfitRadio(String selfProfitRadio) {
		this.selfProfitRadio = selfProfitRadio;
	}
}
