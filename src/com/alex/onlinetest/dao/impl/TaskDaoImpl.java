package com.alex.onlinetest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.alex.onlinetest.dao.TaskDao;
import com.alex.onlinetest.hbm.Item;
import com.alex.onlinetest.hbm.Task;
import com.alex.onlinetest.hbm.User;

public class TaskDaoImpl implements TaskDao {
	
	//定义一个HibernateTemplate 对象，用于执行持久化操作
	private HibernateTemplate ht = null;
	
	//Hibernate 持久化操作所需要的SessionFactory
	private SessionFactory sessionFactory;
	
	private PageResultDaoSupport pageResultDaoSupport;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setPageResultDaoSupport(PageResultDaoSupport pageResultDaoSupport) {
		this.pageResultDaoSupport = pageResultDaoSupport;
	}
	
	//初始化 HibernateTemplate 的方法
	private HibernateTemplate getHibernateTemplate() {
		if (ht == null) {
			ht = new HibernateTemplate(sessionFactory);
		}
		return ht;
	}

	@Override
	public Task get(Integer id) {
		return getHibernateTemplate().get(Task.class, id);
	}

	@Override
	public Integer save(Task task) {
		return (Integer)getHibernateTemplate().save(task);
	}

	@Override
	public void update(Task task) {
		getHibernateTemplate().update(task);

	}

	@Override
	public void delete(Task task) {
		getHibernateTemplate().delete(task);

	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));

	}

	@Override
	public List<Task> findAllTask() {
		return (List<Task>)getHibernateTemplate().find("from Task");
	}

	@Override
	public List<Task> findAllTaskByUser(User user) {
		return (List<Task>)getHibernateTemplate().find("from Task ta Where ta.owner=?",user.getLoginId());
	}

	@Override
	public PageInfo getResultByPage(int offset, int pageSize) {
		return this.pageResultDaoSupport.findByPage("from Task", offset, pageSize);
	}

	
	
}
