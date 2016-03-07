package com.alex.onlinetest.dao;

import com.alex.onlinetest.dao.impl.PageInfo;

public interface PageResult {
	
	/**
	 * 分页查询
	 * @param start 开始记录
	 * @param limit 记录数限制
	 * @return PageInfo实例
	 */
	public PageInfo getResultByPage(int start, int limit);
}
