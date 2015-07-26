package com.robo.store_shopkeeper.dao;

import java.util.List;

public class GetDailyOrdersResponse extends CommonResponse {
	private List<GetDailyOrdersVo> list;

	public List<GetDailyOrdersVo> getList() {
		return list;
	}

	public void setList(List<GetDailyOrdersVo> list) {
		this.list = list;
	}
}
