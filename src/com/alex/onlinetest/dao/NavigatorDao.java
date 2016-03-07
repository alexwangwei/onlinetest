package com.alex.onlinetest.dao;

import java.util.List;

import com.alex.onlinetest.hbm.Navigator;


public interface NavigatorDao {
	
	/**
	 * 根据标识属性来加载Navigator实例
	 * @param id 需要加载的Navigator实例的标识属性值
	 * @return 指定标识属性对应的Navigator实例
	 */
	Navigator get(Integer id);
	
	/**
	 * 根据标识属性来加载Navigator实例
	 * @param nodeName 需要加载的Navigator实例的标识属性值
	 * @return 指定标识属性对应的Navigator实例
	 */
	Navigator get(String nodeName);
	
	/**
	 * 持久化指定的Navigator实例
	 * @param nav 需要被持久化的Navigator实例
	 * @return Navigator 实例被持久化后的标识属性值
	 */
	Integer save(Navigator nav);
	
	/**
	 * 修改指定的Navigator实例
	 * @param nav 需要被修改的Navigator实例
	 */
	void update(Navigator nav);
	
	/**
	 * 删除指定的Navigator实例
	 * @param nav需要被删除的Navigator实例
	 */
	void delete(Navigator nav);
	
	/**
	 * 根据标识属性删除Navigator实例
	 * @param id 需要被删除的Navigator实例的标识属性值
	 */
	void delete(Integer id);
	
	/**
	 * 查询全部的Navigator实例
	 * @return 数据库中全部的Navigator实例
	 */
	List<Navigator> findAllNavigator();
	
	/**
	 * 查询全部的Navigator实例
	 * @return 数据库中全部的Navigator实例，并且所有实例按顺序排列
	 */
	List<Navigator> findAllNavigatorBySequence();
	
	/**
	 * 根据ParentID查询全部节点实例
	 * @param parentId 父节点
	 * @return 数据库中全部符合条件的子节点，并且所有子节点按顺序排列
	 */
	List<Navigator> findAllNodesBySequence(int parentId);
	
	/**
	 * 获取所有的一级根节点
	 * @return 数据库中全部的Navigator实例，并且所有实例按顺序排列
	 */
	List<Navigator> findAllRootBySequence();
}
