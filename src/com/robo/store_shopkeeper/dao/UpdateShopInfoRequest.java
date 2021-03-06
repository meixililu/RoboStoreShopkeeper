package com.robo.store_shopkeeper.dao;


public class UpdateShopInfoRequest extends CommonRequest {
	private String shopId; // 店铺ID
	// private String shopName; // 店铺名称
	private String memo; // 店铺简介
	private String longitude; // 经度
	private String latitude; // 纬度
	private String linkMan; // 联系人
	private String linkMobile; // 联系电话
	private String sosMan; // 紧急联系人
	private String sosTel; // 紧急联系电话
	// private String businessNO; // 营业执照
	// private String opendate; // 开店时间 yyyy-MM-dd
	// private String provinceId; // 省份Id
	// private String provinceName; // 省份名称
	// private String cityId; // 城市Id
	// private String cityName; // 城市名称
	private String address; // 详细地址

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkMobile() {
		return linkMobile;
	}

	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}

	public String getSosMan() {
		return sosMan;
	}

	public void setSosMan(String sosMan) {
		this.sosMan = sosMan;
	}

	public String getSosTel() {
		return sosTel;
	}

	public void setSosTel(String sosTel) {
		this.sosTel = sosTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
