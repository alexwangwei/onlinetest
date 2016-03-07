package com.alex.onlinetest.admin.service;

import java.util.List;
import java.util.Set;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;


public interface GroupManagement {
	
	/**
	 * 分页查询
	 * @param start 开始记录
	 * @param limit 记录数限制
	 * @return PageInfo实例
	 */
	public PageInfo getResultByPage(int offset, int pageSize);	
	
	/**
	 * 获取所有UserGroup实例
	 * @return 返回List<UserGroup>
	 */
	public List<UserGroup> getAllUserGroup();
	
	/**
	 * 添加新的UserGroup
	 * @param usergroup 需要添加的UserGroup实例
	 * @return boolean 添加成功返回True，相反返回False
	 */
	public boolean addUserGroup(UserGroup usergroup);
	
	/**
	 * 修改用户组组名
	 * @param usergroup 需要修改的用户组实例
	 * @param groupname 需要修改的名字
	 * @return boolean 修改成功返回True，相反返回False
	 */
	public boolean updateUserGroupName(UserGroup usergroup, String newgroupname);
	
	/**
	 * 按识别ID获取UserGroup实例
	 * @param id
	 * @return UserGroup实例
	 */
	public UserGroup getById(int id);
	
	/**
	 * 按groupname获取UserGroup实例
	 * @param groupname
	 * @return UserGroup实例
	 */
	public UserGroup getByGroupName(String groupname);
	
	
	/**
	 * 按识别ID删除UserGroup实例
	 * @param id
	 * @return boolean 修改成功返回True，相反返回False
	 */
	public boolean deleteById(int id);
	
	/**
	 * 按groupname删除UserGroup实例
	 * @param groupname
	 * @return boolean 修改成功返回True，相反返回False
	 */
	public boolean deleteByGroupName(String groupname);
	
	/**
	 * 返回指定用户组的所有User实例
	 * @param usergroup 指定用户组
	 * @return 返回指定用户组的所有User实例
	 */
	public List<User> getAllUsersInGroup(UserGroup usergroup);
	
	/**
	 * 返回非重复的User实体列表
	 * @param grouplist
	 * @return 返回非重复的User实体列表
	 */
	public List<User> getAllDistinctUsersInGroup(Set<UserGroup> grouplist);
}
