package com.robo.store_shopkeeper.dao;

public class OperatorLoginResponse extends CommonResponse {
	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
