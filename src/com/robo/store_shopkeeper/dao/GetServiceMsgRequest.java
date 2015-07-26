package com.robo.store_shopkeeper.dao;

public class GetServiceMsgRequest extends CommonRequest {
	private String shopId;
	private String msgId;	// 
	private int pageIndex;
	private int pageCount;	// 默认10条

	public String getShopId() {
		return shopId;
	}

	public void Id(String shopId) {
		this.shopId = shopId;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
}
