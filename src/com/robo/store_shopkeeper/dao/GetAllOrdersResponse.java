package com.robo.store_shopkeeper.dao;

import java.util.List;

public class GetAllOrdersResponse extends CommonResponse {
	private List<GetAllOrdersVo> list;

	public List<GetAllOrdersVo> getList() {
		return list;
	}

	public void setList(List<GetAllOrdersVo> list) {
		this.list = list;
	}
}
