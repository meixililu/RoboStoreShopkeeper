package com.robo.store_shopkeeper.dao;

import java.util.List;

public class GetUndoListResponse extends CommonResponse {
	private List<GetUndoListVo> list;

	public List<GetUndoListVo> getList() {
		return list;
	}

	public void setList(List<GetUndoListVo> list) {
		this.list = list;
	}
}
