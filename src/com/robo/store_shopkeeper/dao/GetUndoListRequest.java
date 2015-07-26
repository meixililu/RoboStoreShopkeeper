package com.robo.store_shopkeeper.dao;

public class GetUndoListRequest extends CommonRequest {
	private String shopId;
	private int undoStatus;	// 撤销状态0=未 1=已 9=全部
	private int pageIndex;
	private int pageCount;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public int getUndoStatus() {
		return undoStatus;
	}

	public void setUndoStatus(int undoStatus) {
		this.undoStatus = undoStatus;
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
}
