package com.robo.store_shopkeeper.dao;

import java.util.List;

public class GetDamageListResponse extends CommonResponse {
	private List<GetDamageListVo> list;

	public List<GetDamageListVo> getList() {
		return list;
	}

	public void setList(List<GetDamageListVo> list) {
		this.list = list;
	}
}
