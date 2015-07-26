package com.robo.store_shopkeeper.dao;

public class UploadFailureRequest extends CommonRequest {
	private String machineId;
	private String failureInfo;

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getFailureInfo() {
		return failureInfo;
	}

	public void setFailureInfo(String failureInfo) {
		this.failureInfo = failureInfo;
	}
}
