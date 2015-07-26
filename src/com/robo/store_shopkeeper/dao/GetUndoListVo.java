package com.robo.store_shopkeeper.dao;

public class GetUndoListVo {
	private String datetime; // yyyy-MM-dd HH:mm:ss
	private String undoId;
	private int undoStatus;
	private String title;

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
