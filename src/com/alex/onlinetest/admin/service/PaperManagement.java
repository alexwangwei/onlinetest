package com.alex.onlinetest.admin.service;

import java.util.List;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.UserGroup;


public interface PaperManagement {
	
	/**
	 * 分页查询
	 * @param start 开始记录
	 * @param limit 记录数限制
	 * @return PageInfo实例
	 */
	public PageInfo getResultByPage(int offset, int pageSize);	
	
	/**
	 * 获取所有Paper实例
	 * @return 返回List<Question>
	 */
	public List<Paper> getAllPaper();
	
	/**
	 * 添加新的Paper
	 * @param paper 需要添加的Paper实例
	 * @return boolean 添加成功返回True，相反返回False
	 */
	public boolean addPaper(Paper paper);
	
	/**
	 * 按识别ID获取Paper实例
	 * @param id
	 * @return Paper实例
	 */
	public Paper getById(int id);
	
	
	/**
	 * 按识别ID删除Paper实例
	 * @param id
	 * @return boolean 修改成功返回True，相反返回False
	 */
	public boolean deleteById(int id);
	
	/**
	 * 判断Paper是否所有题目都是客观题
	 * @param id Paper实例对应的id
	 * @return True则表明Paper全部题目为客观题，False则表明有主观题
	 */
	public boolean isAllObjective(int id);
	
}
