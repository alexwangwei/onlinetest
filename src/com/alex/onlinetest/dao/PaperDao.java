package com.alex.onlinetest.dao;

import java.util.List;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Question;

public interface PaperDao {

	/**
	 * 根据标识属性来加载Question实例
	 * @param id 需要加载的Question实例的标识属性值
	 * @return 指定标识属性对应的Question实例
	 */
	Paper get(Integer id);
	
	/**
	 * 持久化指定的Paper实例
	 * @param Paper 需要被持久化的Paper实例
	 * @return Paper 实例被持久化后的标识属性值
	 */
	Integer save(Paper paper);
	
	/**
	 * 修改指定的Paper实例
	 * @param Paper 需要被修改的Paper实例
	 */
	void update(Paper paper);
	
	/**
	 * 删除指定的Paper实例
	 * @param paper需要被删除的Paper实例
	 */
	void delete(Paper paper);
	
	/**
	 * 根据标识属性删除Paper实例
	 * @param id 需要被删除的Paper实例的标识属性值
	 */
	void delete(Integer id);
	
	/**
	 * 查询全部的Paper实例
	 * @return 数据库中全部的Paper实例
	 */
	List<Paper> findAllPaper();
	
	/**
	 * 分页查询
	 * @param offset 开始记录
	 * @param pageSize 记录数限制
	 * @return PageInfo实例
	 */
	public PageInfo getResultByPage(int offset, int pageSize);
}
