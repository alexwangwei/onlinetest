package com.alex.onlinetest.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.alex.onlinetest.dao.QuestionDao;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.Role;

public class QuestionDaoImpl implements QuestionDao {
	
	//定义一个HibernateTemplate 对象，用于执行持久化操作
	private HibernateTemplate ht = null;
	
	//Hibernate 持久化操作所需要的SessionFactory
	private SessionFactory sessionFactory;
	
	private PageResultDaoSupport pageResultDaoSupport;
	
	private DynamicQueryDaoSupport dynamicQueryDaoSupport;
	
	//IoC
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setPageResultDaoSupport(PageResultDaoSupport pageResultDaoSupport) {
		this.pageResultDaoSupport = pageResultDaoSupport;
	}

	public void setDynamicQueryDaoSupport(
			DynamicQueryDaoSupport dynamicQueryDaoSupport) {
		this.dynamicQueryDaoSupport = dynamicQueryDaoSupport;
	}

	//初始化 HibernateTemplate 的方法
	private HibernateTemplate getHibernateTemplate() {
		if (ht == null) {
			ht = new HibernateTemplate(sessionFactory);
		}
		return ht;
	}
	
	@Override
	public Question get(Integer id) {
		return getHibernateTemplate().get(Question.class, id);
	}

	@Override
	public Integer save(Question question) {
		return (Integer)getHibernateTemplate().save(question);
	}

	@Override
	public void update(Question question) {
		getHibernateTemplate().update(question);

	}

	@Override
	public void delete(Question question) {
		getHibernateTemplate().delete(question);

	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));

	}

	@Override
	public List<Question> findAllQuestion() {
		return (List<Question>)getHibernateTemplate().find("from Question qs order by qs.id");
	}

	@Override
	public PageInfo getResultByPage(int offset, int pageSize) {
		return this.pageResultDaoSupport.findByPage("from Question", offset, pageSize);
	}

	@Override
	public DynamicResult findAllQuestionByFilter(
			HashMap<String, String> filter) {
		return this.dynamicQueryDaoSupport.findByFilterAnd(filter, "Question");
	}

	

	
}
