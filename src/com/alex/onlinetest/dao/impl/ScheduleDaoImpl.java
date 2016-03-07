package com.alex.onlinetest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.alex.onlinetest.dao.ScheduleDao;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Schedule;

public class ScheduleDaoImpl implements ScheduleDao {
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
	public Schedule get(Integer id) {
		return getHibernateTemplate().get(Schedule.class, id);
	}

	@Override
	public Integer save(Schedule schedule) {
		return (Integer)getHibernateTemplate().save(schedule);
	}

	@Override
	public void update(Schedule schedule) {
		getHibernateTemplate().update(schedule);

	}

	@Override
	public void delete(Schedule schedule) {
		getHibernateTemplate().delete(schedule);

	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));

	}

	@Override
	public List<Schedule> findAllSchedule() {
		return (List<Schedule>)getHibernateTemplate().find("from Schedule sch order by sch.id");
	}

	@Override
	public PageInfo getResultByPage(int offset, int pageSize) {
		return this.pageResultDaoSupport.findByPage("from Schedule", offset, pageSize);
	}

}
