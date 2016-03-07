package com.alex.onlinetest.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.alex.onlinetest.dao.UserDao;
import com.alex.onlinetest.hbm.Role;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;

public class UserDaoImpl implements UserDao {
	
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
	public User get(Integer id) {
		return getHibernateTemplate().get(User.class, id);
	}

	@Override
	public Integer save(User user) {
		return (Integer)getHibernateTemplate().save(user);
	}

	@Override
	public void update(User user) {
		getHibernateTemplate().update(user);
	}

	@Override
	public void delete(User user) {
		getHibernateTemplate().delete(user);
	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));
	}

	@Override
	public List<User> findAllUser() {
		return (List<User>)getHibernateTemplate().find("from User");
	}

	@Override
	public User findByLoginId(String loginId) {
		User user = new User();
		List<User> users = (List<User>)getHibernateTemplate().find("from User u where u.loginId=?", loginId);
		
		if (users == null && users.size()<=0) return null;
		user = users.get(0);
		
		return user;
	}

	@Override
	public User findByLoginIdAndPassword(String loginId, String password) {
		List<User> users = (List<User>)getHibernateTemplate().find("from User u where u.loginId=? and u.password=?", loginId, password);
		
		if (users != null && users.size()>0) {
			return users.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public PageInfo getResultByPage(int offset, int pageSie) {

		return this.pageResultDaoSupport.findByPage("from User", offset, pageSie);
	}

}
