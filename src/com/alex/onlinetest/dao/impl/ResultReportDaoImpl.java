package com.alex.onlinetest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.alex.onlinetest.dao.ResultReportDao;
import com.alex.onlinetest.hbm.Item;
import com.alex.onlinetest.hbm.ResultReport;

public class ResultReportDaoImpl implements ResultReportDao {
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
	public ResultReport get(Integer id) {
		return getHibernateTemplate().get(ResultReport.class, id);
	}

	@Override
	public Integer save(ResultReport rr) {
		return (Integer)getHibernateTemplate().save(rr);
	}

	@Override
	public void update(ResultReport rr) {
		getHibernateTemplate().update(rr);

	}

	@Override
	public void delete(ResultReport rr) {
		getHibernateTemplate().delete(rr);

	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));

	}

	@Override
	public List<ResultReport> findAllResultReport() {
		return (List<ResultReport>)getHibernateTemplate().find("from ResultReport");
	}

	@Override
	public PageInfo getResultByPage(int offset, int pageSize) {
		return this.pageResultDaoSupport.findByPage("from Task", offset, pageSize);
	}

}
