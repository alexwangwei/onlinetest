package com.alex.onlinetest.dao;

import java.util.List;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Item;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.Task;
import com.alex.onlinetest.hbm.User;

public interface ItemDao {

	/**
	 * 根据标识属性来加载Item实例
	 * @param id 需要加载的Item实例的标识属性值
	 * @return 指定标识属性对应的Item实例
	 */
	Item get(Integer id);
	
	/**
	 * 持久化指定的Item实例
	 * @param Item 需要被持久化的Item实例
	 * @return Item 实例被持久化后的标识属性值
	 */
	Integer save(Item item);
	
	/**
	 * 修改指定的Item实例
	 * @param Item 需要被修改的Item实例
	 */
	void update(Item item);
	
	/**
	 * 删除指定的Item实例
	 * @param item需要被删除的Item实例
	 */
	void delete(Item task);
	
	/**
	 * 根据标识属性删除Item实例
	 * @param id 需要被删除的Item实例的标识属性值
	 */
	void delete(Integer id);
	
	/**
	 * 查询全部的Item实例
	 * @return 数据库中全部的Item实例
	 */
	List<Item> findAllItem();
	
	/**
	 * 查询Task全部的Item实例
	 * @param taskid
	 * @return 数据库中全部Item的Task实例
	 */
	List<Item> findAllItemByTask(Integer taskid);
	
	/**
	 * 分页查询
	 * @param offset 开始记录
	 * @param pageSize 记录数限制
	 * @return PageInfo实例
	 */
	PageInfo getResultByPage(int offset, int pageSize);
	
}
