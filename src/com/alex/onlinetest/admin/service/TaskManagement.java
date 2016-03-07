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


public interface TaskManagement {
	
	/**
	 * 分页查询
	 * @param start 开始记录
	 * @param limit 记录数限制
	 * @return PageInfo实例
	 */
	public PageInfo getResultByPage(int offset, int pageSize);	
	
	/**
	 * 获取所有Paper实例
	 * @return 返回List<Task>
	 */
	public List<Task> getAllTask();
	

	/**
	 * 根据User返回所有属于该用户的Task实例
	 * @param user
	 * @return 根据User返回所有属于该用户的Task实例
	 */
	public List<Task> getAllTaskByUser(User user);
	
	/**
	 * 根据User返回所有属于该用户的Active状态的Task实例
	 * @param user
	 * @return
	 */
	public List<Task> getAllActiveTaskByUser(User user);
	
	/**
	 * 添加新的Task
	 * @param Task 需要添加的Task实例
	 * @return boolean 添加成功返回True，相反返回False
	 */
	public boolean addTask(Task task);
	
	/**
	 * 按识别ID获取Task实例
	 * @param id
	 * @return Task实例
	 */
	public Task getById(int id);
	
	
	/**
	 * 按识别ID删除Task实例
	 * @param id
	 * @return boolean 修改成功返回True，相反返回False
	 */
	public boolean deleteById(int id);
	
	/**
	 * 根据Task返回该Task所对应的所有问题的编号，并且该编号按Question的Sequence从小到大排序
	 * @param task
	 * @return 根据Task返回该Task所对应的所有问题的编号，并且该编号按Question的Sequence从小到大排序
	 */
	public List<Integer> getSortedQuestionId(Task task);
	
	public boolean updateTaskActualStart(Integer tid, Date date);
	
	public boolean updateTaskActualFinish(Integer tid, Date date);
	
	public boolean updateTaskDynamic(Integer tid, Date date);
	
	public Integer updatePercentage(Integer tid);
	
	public boolean updateTaskStatus(Integer tid, TASK_STATUS status);
	
	public boolean updateTaskItem(Integer tid, Integer qid, String answer, Integer elapsed);
	
	public Item getAnswerOfUser(Integer tid, Integer qid);
	
	public Integer getAvailableSeconds(Integer tid);
	
	/**
	 * 批改Task所对应的实例
	 * @param tid
	 */
	public ResultReport reviewTask(Integer tid);
	
}
