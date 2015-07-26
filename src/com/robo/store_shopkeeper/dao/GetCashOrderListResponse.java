package com.robo.store_shopkeeper.dao;

import java.util.List;

public class GetCashOrderListResponse extends CommonResponse {
	private List<GetCashOrderListVo> list;

	public List<GetCashOrderListVo> getList() {
		return list;
	}

	public void setList(List<GetCashOrderListVo> list) {
		this.list = list;
	}
}
