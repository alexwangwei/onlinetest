package com.alex.onlinetest.admin.service;

import java.util.List;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.Schedule;
import com.alex.onlinetest.hbm.UserGroup;


public interface ScheduleManagement {
	
	/**
	 * 分页查询
	 * @param start 开始记录
	 * @param limit 记录数限制
	 * @return PageInfo实例
	 */
	public PageInfo getResultByPage(int offset, int pageSize);	
	
	/**
	 * 获取所有Paper实例
	 * @return 返回List<Schedule>
	 */
	public List<Schedule> getAllSchedule();
	
	/**
	 * 添加新的Schedule
	 * @param Schedule 需要添加的Schedule实例
	 * @return boolean 添加成功返回True，相反返回False
	 */
	public boolean addSchedule(Schedule schedule);
	
	/**
	 * 按识别ID获取Schedule实例
	 * @param id
	 * @return Schedule实例
	 */
	public Schedule getById(int id);
	
	
	/**
	 * 按识别ID删除Schedule实例
	 * @param id
	 * @return boolean 修改成功返回True，相反返回False
	 */
	public boolean deleteById(int id);
	
	/**
	 * 更新Schedule状态
	 * @param schedule
	 * @return
	 */
	public boolean updateScheduleStatus(Schedule schedule);
	
}
