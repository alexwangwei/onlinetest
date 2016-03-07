package com.alex.onlinetest.dao;

import java.util.HashMap;
import java.util.List;

import com.alex.onlinetest.dao.impl.DynamicResult;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Question;

public interface QuestionDao {

	/**
	 * 根据标识属性来加载Question实例
	 * @param id 需要加载的Question实例的标识属性值
	 * @return 指定标识属性对应的Question实例
	 */
	Question get(Integer id);
	
	/**
	 * 持久化指定的Question实例
	 * @param Question 需要被持久化的Question实例
	 * @return Question 实例被持久化后的标识属性值
	 */
	Integer save(Question question);
	
	/**
	 * 修改指定的Question实例
	 * @param question 需要被修改的Question实例
	 */
	void update(Question question);
	
	/**
	 * 删除指定的Question实例
	 * @param question需要被删除的Question实例
	 */
	void delete(Question question);
	
	/**
	 * 根据标识属性删除Question实例
	 * @param id 需要被删除的Question实例的标识属性值
	 */
	void delete(Integer id);
	
	/**
	 * 查询全部的Question实例
	 * @return 数据库中全部的Question实例
	 */
	List<Question> findAllQuestion();
	
	/**
	 * 分页查询
	 * @param offset 开始记录
	 * @param pageSize 记录数限制
	 * @return PageInfo实例
	 */
	public PageInfo getResultByPage(int offset, int pageSize);
	
	
	/**
	 * 根据过滤条件来获得满足条件的Question实例
	 * @param filter 过滤条件是一个HashMap，Key代表过滤字段，Value代表过滤的值
	 * @return List<Question>满足条件的所有Question实例
	 */
	public DynamicResult findAllQuestionByFilter(HashMap<String,String> filter);
	
}
