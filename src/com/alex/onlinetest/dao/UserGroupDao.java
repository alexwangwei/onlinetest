package com.alex.onlinetest.dao;

import java.util.List;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Role;
import com.alex.onlinetest.hbm.UserGroup;

public interface UserGroupDao {
	
	/**
	 * 根据标识属性来加载UserGroup实例
	 * @param id 需要加载的UserGroup实例的标识属性值
	 * @return 指定标识属性对应的UserGroup实例
	 */
	UserGroup get(Integer id);
	
	/**
	 * 根据标识属性来加载UserGroup实例
	 * @param groupName 需要加载的UserGroup实例的标识属性值
	 * @return 指定标识属性对应的UserGroup实例
	 */
	UserGroup get(String groupName);
	
	/**
	 * 持久化指定的UserGroup实例
	 * @param userGroup 需要被持久化的UserGroup实例
	 * @return UserGroup 实例被持久化后的标识属性值
	 */
	Integer save(UserGroup userGroup);
	
	/**
	 * 修改指定的UserGroup实例
	 * @param userGroup 需要被修改的UserGroup实例
	 */
	void update(UserGroup userGroup);
	
	/**
	 * 删除指定的UserGroup实例
	 * @param userGroup需要被删除的UserGroup实例
	 */
	void delete(UserGroup userGroup);
	
	/**
	 * 根据标识属性删除UserGroup实例
	 * @param id 需要被删除的UserGroup实例的标识属性值
	 */
	void delete(Integer id);
	
	/**
	 * 查询全部的UserGroup实例
	 * @return 数据库中全部的UserGroup实例
	 */
	List<UserGroup> findAll();
	
	/**
	 * 分页查询
	 * @param offset 开始记录
	 * @param pageSize 记录数限制
	 * @return PageInfo实例
	 */
	public PageInfo getResultByPage(int offset, int pageSize);

}
