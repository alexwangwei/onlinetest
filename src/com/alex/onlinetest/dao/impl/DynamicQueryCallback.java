package com.alex.onlinetest.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.alex.onlinetest.hbm.Question;

public class DynamicQueryCallback implements HibernateCallback<List> {
	
	private HashMap<String, String> filter;
	private String classname;

	
	
	public HashMap<String, String> getFilter() {
		return filter;
	}



	public void setFilter(HashMap<String, String> filter) {
		this.filter = filter;
	}



	public String getClassname() {
		return classname;
	}



	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Override
	public List doInHibernate(Session session) throws HibernateException {
		
//		Criteria criteria = session.createCriteria(Question.class);
//		//根据fliter动态创建And关系
//		Iterator it = filter.entrySet().iterator();
//		while (it.hasNext()) {
//			Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
//			Criterion criterion = Expression.eq(entry.getKey(), entry.getValue());
//			criteria.add(Restrictions.and(criterion));
//		}
//		
//		return criteria.list();	
		String sql = "";
		sql = "select {c.*} from " + this.classname + " as c where";
		Iterator it = filter.entrySet().iterator();
		int i=1;
		while (it.hasNext()) {
			if (i==1) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
				sql = sql + " c." + entry.getKey() + "=" + entry.getValue();
			} else {
				Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
				sql = sql + " and c." + entry.getKey() + "=" + entry.getValue();
			}

		}
		Query query = session.createSQLQuery(sql);
		
		return query.list();
	}

	
}
