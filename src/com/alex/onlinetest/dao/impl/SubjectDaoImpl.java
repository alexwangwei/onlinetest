package com.alex.onlinetest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.alex.onlinetest.dao.SubjectDao;
import com.alex.onlinetest.hbm.Role;
import com.alex.onlinetest.hbm.Subject;

public class SubjectDaoImpl implements SubjectDao {

	//定义一个HibernateTemplate 对象，用于执行持久化操作
	private HibernateTemplate ht = null;
	
	//Hibernate 持久化操作所需要的SessionFactory
	private SessionFactory sessionFactory;
	
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
	public Subject get(Integer id) {
		return getHibernateTemplate().get(Subject.class, id);
	}

	@Override
	public Subject get(String subjectName) {
		List<Subject> sub = (List<Subject>)getHibernateTemplate().find("from Subject sub where sub.subjectName=?", subjectName);
		
		if (sub != null && sub.size()>0) {
			return sub.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Integer save(Subject sub) {
		return (Integer)getHibernateTemplate().save(sub);
	}

	@Override
	public void update(Subject sub) {
		getHibernateTemplate().update(sub);
	}

	@Override
	public void delete(Subject sub) {
		getHibernateTemplate().delete(sub);
	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));
	}

	@Override
	public List<Subject> findAllSubject() {
		return (List<Subject>)getHibernateTemplate().find("from Subject sub order by sub.id");
	}

}
