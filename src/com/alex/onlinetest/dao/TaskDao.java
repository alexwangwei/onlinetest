package com.alex.onlinetest.dao;

import java.util.List;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Item;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.Task;
import com.alex.onlinetest.hbm.User;

public interface TaskDao {

	/**
	 * 根据标识属性来加载Task实例
	 * @param id 需要加载的Task实例的标识属性值
	 * @return 指定标识属性对应的Task实例
	 */
	Task get(Integer id);
	
	/**
	 * 持久化指定的Task实例
	 * @param Task 需要被持久化的Task实例
	 * @return Task 实例被持久化后的标识属性值
	 */
	Integer save(Task task);
	
	/**
	 * 修改指定的Task实例
	 * @param Task 需要被修改的Task实例
	 */
	void update(Task task);
	
	/**
	 * 删除指定的Task实例
	 * @param task需要被删除的Task实例
	 */
	void delete(Task task);
	
	/**
	 * 根据标识属性删除Task实例
	 * @param id 需要被删除的Task实例的标识属性值
	 */
	void delete(Integer id);
	
	/**
	 * 查询全部的Task实例
	 * @return 数据库中全部的Task实例
	 */
	List<Task> findAllTask();
	
	/**
	 * 查询User全部的Task实例
	 * @param user
	 * @return 数据库中全部User的Task实例
	 */
	List<Task> findAllTaskByUser(User user);
	
	/**
	 * 分页查询
	 * @param offset 开始记录
	 * @param pageSize 记录数限制
	 * @return PageInfo实例
	 */
	PageInfo getResultByPage(int offset, int pageSize);
	
}
