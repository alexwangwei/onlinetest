package com.alex.onlinetest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.alex.onlinetest.dao.ItemDao;
import com.alex.onlinetest.hbm.Item;
import com.alex.onlinetest.hbm.Task;

public class ItemDaoImpl implements ItemDao {
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
	public Item get(Integer id) {
		return getHibernateTemplate().get(Item.class, id);
	}

	@Override
	public Integer save(Item item) {
		return (Integer)getHibernateTemplate().save(item);
	}

	@Override
	public void update(Item item) {
		getHibernateTemplate().update(item);

	}

	@Override
	public void delete(Item task) {
		getHibernateTemplate().delete(task);

	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));

	}

	@Override
	public List<Item> findAllItem() {
		return (List<Item>)getHibernateTemplate().find("from Item");
	}

	@Override
	public List<Item> findAllItemByTask(Integer taskid) {
		return null;
	}

	@Override
	public PageInfo getResultByPage(int offset, int pageSize) {
		return this.pageResultDaoSupport.findByPage("from Task", offset, pageSize);
	}

}
