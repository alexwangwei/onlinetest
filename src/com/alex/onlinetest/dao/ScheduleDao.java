package com.alex.onlinetest.dao;

import java.util.List;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.Schedule;

public interface ScheduleDao {

	/**
	 * 根据标识属性来加载Schedule实例
	 * @param id 需要加载的Schedule实例的标识属性值
	 * @return 指定标识属性对应的Schedule实例
	 */
	Schedule get(Integer id);
	
	/**
	 * 持久化指定的Schedule实例
	 * @param Schedule 需要被持久化的Schedule实例
	 * @return Schedule 实例被持久化后的标识属性值
	 */
	Integer save(Schedule schedule);
	
	/**
	 * 修改指定的Schedule实例
	 * @param Schedule 需要被修改的Schedule实例
	 */
	void update(Schedule schedule);
	
	/**
	 * 删除指定的Schedule实例
	 * @param Schedule需要被删除的Schedule实例
	 */
	void delete(Schedule schedule);
	
	/**
	 * 根据标识属性删除Schedule实例
	 * @param id 需要被删除的Schedule实例的标识属性值
	 */
	void delete(Integer id);
	
	/**
	 * 查询全部的Schedule实例
	 * @return 数据库中全部的Schedule实例
	 */
	List<Schedule> findAllSchedule();
	
	/**
	 * 分页查询
	 * @param offset 开始记录
	 * @param pageSize 记录数限制
	 * @return PageInfo实例
	 */
	public PageInfo getResultByPage(int offset, int pageSize);
}
