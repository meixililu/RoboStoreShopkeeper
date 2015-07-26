package com.robo.store_shopkeeper.dao;

import java.util.List;

public class GetInStrogeListResponse extends CommonResponse {
	private List<GetInStrogeListVo> list;

	public List<GetInStrogeListVo> getList() {
		return list;
	}

	public void setList(List<GetInStrogeListVo> list) {
		this.list = list;
	}
}
