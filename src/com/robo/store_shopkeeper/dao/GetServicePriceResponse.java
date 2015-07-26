package com.robo.store_shopkeeper.dao;

import java.util.List;

public class GetServicePriceResponse extends CommonResponse {
	private List<GetServicePriceVo> list;

	public List<GetServicePriceVo> getList() {
		return list;
	}

	public void setList(List<GetServicePriceVo> list) {
		this.list = list;
	}
}
