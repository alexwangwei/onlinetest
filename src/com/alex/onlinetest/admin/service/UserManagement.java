package com.alex.onlinetest.admin.service;

import java.util.List;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Role;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;

public interface UserManagement {
	
	/**
	 * 分页查询
	 * @param start 开始记录
	 * @param limit 记录数限制
	 * @return PageInfo实例
	 */
	public PageInfo getResultByPage(int offset, int pageSize);	
	
	/**
	 * 用户身份验证
	 * @param user 需要验证的User实例
	 * @return 若用户未合法用户返回True，反之返回False
	 */
	public boolean validUser(User user);
	
	/**
	 * 根据用户loginID获取用户实体
	 * @param loginId 登录用户ID
	 * @return 用户实体
	 */
	public User getUserByLoginId(String loginId);
	
	/**
	 * 更改用户密码
	 * @param user 需要更改密码的User实例
	 * @param newpwd 新密码
	 * @return 若更改成功返回True, 反之返回False
	 */
	public boolean changePassword(User user, String newpwd);
	
	/**
	 * 更改显示名
	 * @param user 需要更改显示名的User实例
	 * @param newdisplayname 显示名
	 * @return 若更改成功返回True, 反之返回False
	 */
	public boolean changeDisplayName(User user, String newdisplayname);
	
	/**
	 * 添加Role
	 * @param user 需要更改显示名的User实例
	 * @param role 需要添加的Role实例
	 * @return 若更改成功返回True, 反之返回False
	 */
	public boolean addRole(User user, Role role);
	
	/**
	 * 删除Role
	 * @param user 需要更改显示名的User实例
	 * @param role 需要移除的Role实例
	 * @return 若更改成功返回True, 反之返回False
	 */
	public boolean removeRole(User user, Role role);
	
	/**
	 * 添加Group
	 * @param user 需要更改显示名的User实例
	 * @param group 需要添加的UserGroup实例
	 * @return 若更改成功返回True, 反之返回False
	 */
	public boolean addGroup(User user, UserGroup group);
	
	/**
	 * 删除Group
	 * @param user 需要更改显示名的User实例
	 * @param group 需要移除的UserGroup实例
	 * @return 若更改成功返回True, 反之返回False
	 */
	public boolean removeGroup(User user, UserGroup group);
	
	/**
	 * 添加新用户
	 * @param user 用户实例
	 * @return 若更改成功返回True, 反之返回False
	 */
	public boolean addUser(User user);
	
	/**
	 * 删除用户
	 * @param user
	 * @return 若更改成功返回True, 反之返回False
	 */
	public boolean delUser(User user);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean delUserById(Integer id);
	
	/**
	 * 返回系统中所有用户的列表
	 * @return 返回系统中所有用户的User实例
	 */
	public List<User> getAll();
	
	/**
	 * 判断一个用户是否在指定的UserGroup中
	 * @param user 需要判断的User实例
	 * @param ug 需要判断的UserGroup实例
	 * @return 如果用户在指定的UserGroup中返回True，相反返回False
	 */
	public boolean inUserGroup(User user, UserGroup ug);
	
	/**
	 * 更新用户与用户组的对应关系
	 * @param ug 用户组实例
	 * @param users 希望对应到用户组的用户列表
	 */
	public void updateUserAndGroupMapping(UserGroup ug, List<User> users);
}
