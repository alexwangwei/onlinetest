package com.alex.onlinetest.admin.service;

import java.util.Date;
import java.util.List;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Item;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.ResultReport;
import com.alex.onlinetest.hbm.Schedule;
import com.alex.onlinetest.hbm.Task;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;
import com.alex.onlinetest.util.Constant.TASK_STATUS;


public interface ResultReportManagement {
	
	/**
	 * 分页查询
	 * @param start 开始记录
	 * @param limit 记录数限制
	 * @return PageInfo实例
	 */
	public PageInfo getResultByPage(int offset, int pageSize);	
	
	/**
	 * 获取所有Paper实例
	 * @return 返回List<ResultReport>
	 */
	public List<ResultReport> getAllResultReport();
	

	public List<ResultReport> getAllResultReportBySchedule(Schedule schedule);
	
	/**
	 * 根据User返回所有属于该用户的ResultReport实例
	 * @param user
	 * @return 根据User返回所有属于该用户的ResultReport实例
	 */
	public List<ResultReport> getAllResultReportByUser(User user);
	
	/**
	 * 添加新的ResultReport
	 * @param ResultReport 需要添加的ResultReport实例
	 * @return boolean 添加成功返回True，相反返回False
	 */
	public boolean addResultReport(ResultReport rr);
	
	/**
	 * 按识别ID获取ResultReport实例
	 * @param id
	 * @return ResultReport实例
	 */
	public ResultReport getById(int id);
	
	
	/**
	 * 按识别ID删除ResultReport实例
	 * @param id
	 * @return boolean 修改成功返回True，相反返回False
	 */
	public boolean deleteById(int id);
	
	
}
