package com.robo.store_shopkeeper.dao;

import java.util.List;

public class QueryCoInfoResponse extends CommonResponse {
	private List<QueryCoInfoVo> list;

	public List<QueryCoInfoVo> getList() {
		return list;
	}

	public void setList(List<QueryCoInfoVo> list) {
		this.list = list;
	}
}
