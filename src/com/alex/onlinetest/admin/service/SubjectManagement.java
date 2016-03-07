package com.alex.onlinetest.admin.service;

import java.util.List;

import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Subject;
import com.alex.onlinetest.hbm.UserGroup;


public interface SubjectManagement {
	
	
	/**
	 * 获取所有Subject实例
	 * @return 返回List<Subject>
	 */
	public List<Subject> getAllSubject();
	
	/**
	 * 添加新的Subject
	 * @param Subject 需要添加的Subject实例
	 * @return boolean 添加成功返回True，相反返回False
	 */
	public boolean addSubject(Subject subject);
	
	/**
	 * 修改Subject的名字
	 * @param sub 需要修改的Subject实例
	 * @param newname 需要修改的名字
	 * @return boolean 修改成功返回True，相反返回False
	 */
	public boolean updateSubjectName(Subject sub, String newname);
	
	/**
	 * 按识别ID获取Subject实例
	 * @param id
	 * @return Subject实例
	 */
	public Subject getById(int id);
	
	/**
	 * 按subjectName获取Subject实例
	 * @param subjectName
	 * @return Subject实例
	 */
	public Subject getBySubjectName(String subjectName);
	
	
	/**
	 * 按识别ID删除Subject实例
	 * @param id
	 * @return boolean 修改成功返回True，相反返回False
	 */
	public boolean deleteById(int id);
	
	/**
	 * 按subjectName删除Subject实例
	 * @param subjectName
	 * @return boolean 修改成功返回True，相反返回False
	 */
	public boolean deleteBySubjectName(String subjectName);
}
