package com.alex.onlinetest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.alex.onlinetest.dao.PageResult;
import com.alex.onlinetest.dao.UserGroupDao;
import com.alex.onlinetest.hbm.Role;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;

public class UserGroupDaoImpl implements UserGroupDao {
	
	//定义一个HibernateTemplate 对象，用于执行持久化操作
	private HibernateTemplate ht = null;
	
	//Hibernate 持久化操作所需要的SessionFactory
	private SessionFactory sessionFactory;
	
	private PageResultDaoSupport pageResultDaoSupport;
	
	

	public void setPageResultDaoSupport(PageResultDaoSupport pageResultDaoSupport) {
		this.pageResultDaoSupport = pageResultDaoSupport;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//初始化 HibernateTemplate 的方法
	private HibernateTemplate getHibernateTemplate() {
		if (ht == null) {
			ht = new HibernateTemplate(sessionFactory);
		}
		return ht;
	}
	

	@Override
	public UserGroup get(Integer id) {
		return getHibernateTemplate().get(UserGroup.class, id);
	}

	@Override
	public UserGroup get(String groupName) {
		List<UserGroup> ugroups = (List<UserGroup>)getHibernateTemplate().find("from UserGroup ug where ug.groupName=?", groupName);
		
		if (ugroups != null && ugroups.size()>0) {
			return ugroups.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Integer save(UserGroup userGroup) {
		return (Integer)getHibernateTemplate().save(userGroup);
	}

	@Override
	public void update(UserGroup userGroup) {
		getHibernateTemplate().update(userGroup);
	}

	@Override
	public void delete(UserGroup userGroup) {
		getHibernateTemplate().delete(userGroup);
	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));
	}

	@Override
	public List<UserGroup> findAll() {
		return (List<UserGroup>)getHibernateTemplate().find("from UserGroup ug order by ug.id");
	}
	
	@Override
	public PageInfo getResultByPage(int offset, int pageSie) {

		return this.pageResultDaoSupport.findByPage("from UserGroup", offset, pageSie);
	}

}
