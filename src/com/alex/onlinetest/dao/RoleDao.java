package com.alex.onlinetest.dao;

import java.util.List;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Role;
import com.alex.onlinetest.hbm.User;

public interface RoleDao {

	/**
	 * 根据标识属性来加载Role实例
	 * @param id 需要加载的Role实例的标识属性值
	 * @return 指定标识属性对应的Role实例
	 */
	Role get(Integer id);
	
	/**
	 * 根据标识属性来加载Role实例
	 * @param roleName 需要加载的Role实例的标识属性值
	 * @return 指定标识属性对应的Role实例
	 */
	Role get(String roleName);
	
	/**
	 * 持久化指定的Role实例
	 * @param role 需要被持久化的Role实例
	 * @return Role 实例被持久化后的标识属性值
	 */
	Integer save(Role role);
	
	/**
	 * 修改指定的Role实例
	 * @param role 需要被修改的Role实例
	 */
	void update(Role role);
	
	/**
	 * 删除指定的Role实例
	 * @param role需要被删除的Role实例
	 */
	void delete(Role role);
	
	/**
	 * 根据标识属性删除Role实例
	 * @param id 需要被删除的Role实例的标识属性值
	 */
	void delete(Integer id);
	
	/**
	 * 查询全部的Role实例
	 * @return 数据库中全部的Role实例
	 */
	List<Role> findAllRole();
	
	/**
	 * 分页查询
	 * @param offset 开始记录
	 * @param pageSize 记录数限制
	 * @return PageInfo实例
	 */
	public PageInfo getResultByPage(int offset, int pageSize);
}
