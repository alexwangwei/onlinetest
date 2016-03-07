package com.alex.onlinetest.dao;

import java.util.List;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.User;

public interface UserDao {

	/**
	 * 根据标识属性来加载User实例
	 * @param id 需要加载的User实例的标识属性值
	 * @return 指定标识属性对应的User实例
	 */
	User get(Integer id);
	
	/**
	 * 持久化指定的User实例
	 * @param user 需要被持久化的User实例
	 * @return User 实例被持久化后的标识属性值
	 */
	Integer save(User user);
	
	/**
	 * 修改指定的User实例
	 * @param user 需要被修改的User实例
	 */
	void update(User user);
	
	/**
	 * 删除指定的User实例
	 * @param user需要被删除的User实例
	 */
	void delete(User user);
	
	/**
	 * 根据标识属性删除User实例
	 * @param id 需要被删除的User实例的标识属性值
	 */
	void delete(Integer id);
	
	/**
	 * 查询全部的User实例
	 * @return 数据库中全部的User实例
	 */
	List<User> findAllUser();
	
	/**
	 * 根据用户的LoginId查询全部的User实例
	 * @param loginid 用户登录ID
	 * @return 数据库中loginid等于该参数的的User实例
	 */
	User findByLoginId(String loginId);
	
	/**
	 * 根据用户的LoginId和Password查询符合的User实例
	 * @param loginId 用户登录ID
	 * @param password 用户密码
	 * @return 符合条件的User实例
	 */
	User findByLoginIdAndPassword(String loginId, String password);
	
	/**
	 * 分页查询
	 * @param offset 开始记录
	 * @param pageSize 记录数限制
	 * @return PageInfo实例
	 */
	public PageInfo getResultByPage(int offset, int pageSize);
}
