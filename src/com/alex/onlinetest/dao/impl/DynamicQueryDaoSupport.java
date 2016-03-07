package com.alex.onlinetest.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.alex.onlinetest.hbm.Question;

public class DynamicQueryDaoSupport extends HibernateDaoSupport {

	
	public DynamicResult findByFilterAnd(HashMap<String, String> filter, String classname) {
		
		DynamicQueryCallback dqcallback = new DynamicQueryCallback();
		
		dqcallback.setClassname(classname);
		dqcallback.setFilter(filter);
		
		List list = (List)this.getHibernateTemplate().execute(dqcallback);
		
		return new DynamicResult(list.size(),list);
	}
}
