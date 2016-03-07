package com.alex.onlinetest.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

public class HibernateCallB implements HibernateCallback<List> {
	
	private String hql;
	private Object[] value;
	
	
	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public Object[] getValue() {
		return value;
	}

	public void setValue(Object[] value) {
		this.value = value;
	}

	@Override
	public List doInHibernate(Session session) throws HibernateException {
		//执行Hibernate分页查询
		Query query = session.createQuery(hql);
		//为hql语句传入参数
		if (value != null) {
			for (int i=0; i<value.length; i++) {
				query.setParameter(i, value[i]);
			}
		}
		
		List result = query.list();		
		return result;
	}

	public HibernateCallB(String hql, Object[] value) {
		this.hql = hql;
		this.value = value;
	}

	public HibernateCallB() {

	}
	

	
}
