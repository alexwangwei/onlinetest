package com.alex.onlinetest.dao;

import java.util.List;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Item;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.ResultReport;
import com.alex.onlinetest.hbm.Task;
import com.alex.onlinetest.hbm.User;

public interface ResultReportDao {

	/**
	 * 根据标识属性来加载ResultReport实例
	 * @param id 需要加载的ResultReport实例的标识属性值
	 * @return 指定标识属性对应的ResultReport实例
	 */
	ResultReport get(Integer id);
	
	/**
	 * 持久化指定的ResultReport实例
	 * @param ResultReport 需要被持久化的ResultReport实例
	 * @return ResultReport 实例被持久化后的标识属性值
	 */
	Integer save(ResultReport rr);
	
	/**
	 * 修改指定的ResultReport实例
	 * @param ResultReport 需要被修改的ResultReport实例
	 */
	void update(ResultReport rr);
	
	/**
	 * 删除指定的ResultReport实例
	 * @param ResultReport需要被删除的ResultReport实例
	 */
	void delete(ResultReport rr);
	
	/**
	 * 根据标识属性删除ResultReport实例
	 * @param id 需要被删除的ResultReport实例的标识属性值
	 */
	void delete(Integer id);
	
	/**
	 * 查询全部的ResultReport实例
	 * @return 数据库中全部的ResultReport实例
	 */
	List<ResultReport> findAllResultReport();
	
	
	/**
	 * 分页查询
	 * @param offset 开始记录
	 * @param pageSize 记录数限制
	 * @return PageInfo实例
	 */
	PageInfo getResultByPage(int offset, int pageSize);
	
}
