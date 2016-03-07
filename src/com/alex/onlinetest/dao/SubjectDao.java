package com.alex.onlinetest.dao;

import java.util.List;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Subject;

public interface SubjectDao {
	/**
	 * 根据标识属性来加载Subject实例
	 * @param id 需要加载的Subject实例的标识属性值
	 * @return 指定标识属性对应的Subject实例
	 */
	Subject get(Integer id);
	
	/**
	 * 根据标识属性来加载Subject实例
	 * @param subjectName 需要加载的Subject实例的标识属性值
	 * @return 指定标识属性对应的Subject实例
	 */
	Subject get(String subjectName);
	
	/**
	 * 持久化指定的Subject实例
	 * @param sub 需要被持久化的Subject实例
	 * @return Subject 实例被持久化后的标识属性值
	 */
	Integer save(Subject sub);
	
	/**
	 * 修改指定的Subject实例
	 * @param sub 需要被修改的Subject实例
	 */
	void update(Subject sub);
	
	/**
	 * 删除指定的Subject实例
	 * @param sub需要被删除的Subject实例
	 */
	void delete(Subject sub);
	
	/**
	 * 根据标识属性删除Subject实例
	 * @param id 需要被删除的Subject实例的标识属性值
	 */
	void delete(Integer id);
	
	/**
	 * 查询全部的Subject实例
	 * @return 数据库中全部的Subject实例
	 */
	List<Subject> findAllSubject();
	
}
