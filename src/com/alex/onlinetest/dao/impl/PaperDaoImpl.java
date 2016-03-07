package com.alex.onlinetest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.alex.onlinetest.dao.PaperDao;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Question;

public class PaperDaoImpl implements PaperDao {
	//定义一个HibernateTemplate 对象，用于执行持久化操作
	private HibernateTemplate ht = null;
	
	//Hibernate 持久化操作所需要的SessionFactory
	private SessionFactory sessionFactory;
	
	private PageResultDaoSupport pageResultDaoSupport;
	
	
	//IoC
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
	public Paper get(Integer id) {
		return getHibernateTemplate().get(Paper.class, id);
	}

	@Override
	public Integer save(Paper paper) {
		return (Integer)getHibernateTemplate().save(paper);
	}

	@Override
	public void update(Paper paper) {
		getHibernateTemplate().update(paper);

	}

	@Override
	public void delete(Paper paper) {
		getHibernateTemplate().delete(paper);

	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));

	}

	@Override
	public List<Paper> findAllPaper() {
		return (List<Paper>)getHibernateTemplate().find("from Paper pa order by pa.id");
	}

	@Override
	public PageInfo getResultByPage(int offset, int pageSize) {
		return this.pageResultDaoSupport.findByPage("from Paper", offset, pageSize);
	}

}
