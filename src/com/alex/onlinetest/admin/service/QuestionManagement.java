package com.alex.onlinetest.admin.service;

import java.util.HashMap;
import java.util.List;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.UserGroup;


public interface QuestionManagement {
	
	/**
	 * 分页查询
	 * @param start 开始记录
	 * @param limit 记录数限制
	 * @return PageInfo实例
	 */
	public PageInfo getResultByPage(int offset, int pageSize);	
	
	/**
	 * 获取所有Question实例
	 * @return 返回List<Question>
	 */
	public List<Question> getAllQuestion();
	
	/**
	 * 添加新的Question
	 * @param question 需要添加的Question实例
	 * @return boolean 添加成功返回True，相反返回False
	 */
	public boolean addQuestion(Question question);
	
	/**
	 * 按识别ID获取Question实例
	 * @param id
	 * @return Question实例
	 */
	public Question getById(int id);
	
	
	/**
	 * 按识别ID删除Question实例
	 * @param id
	 * @return boolean 修改成功返回True，相反返回False
	 */
	public boolean deleteById(int id);
	
	/**
	 * 根据过滤条件返回所有符合条件的结果，过滤条件之间的关系是AND关系
	 * @param filter 过滤条件以Key-Value的形式存在
	 * @return 符合条件的Question实例的List
	 */
	public List<Question> getAllQuestionByAndFilter(HashMap<String, String> filter);
	
}
