package com.robo.store_shopkeeper.dao;

public class ModifyPasswordRequest extends CommonRequest {
	private String currentPwd;
	private String newPwd;

	public String getCurrentPwd() {
		return currentPwd;
	}

	public void setCurrentPwd(String currentPwd) {
		this.currentPwd = currentPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
}
