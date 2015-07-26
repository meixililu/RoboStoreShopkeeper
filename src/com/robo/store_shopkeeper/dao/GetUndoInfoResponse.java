package com.robo.store_shopkeeper.dao;

import java.util.List;

public class GetUndoInfoResponse extends CommonResponse {
	private String undoId;
	private int undoStatus;
	private String undotime; // yyyy-MM-dd HH:mm:ss
	private String deliveryId;
	private String coId;
	private String coName;
	private List<GetUndoInfoVo> list;

	public String getUndoId() {
		return undoId;
	}

	public void setUndoId(String undoId) {
		this.undoId = undoId;
	}

	public int getUndoStatus() {
		return undoStatus;
	}

	public void setUndoStatus(int undoStatus) {
		this.undoStatus = undoStatus;
	}

	public String getUndotime() {
		return undotime;
	}

	public void setUndotime(String undotime) {
		this.undotime = undotime;
	}

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
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

	public List<GetUndoInfoVo> getList() {
		return list;
	}

	public void setList(List<GetUndoInfoVo> list) {
		this.list = list;
	}
}
