package com.alex.onlinetest.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class PageResultDaoSupport extends HibernateDaoSupport {
	
	/**
	 * 根据查询的hql生成统计sql
	 * @param hql
	 * @return count sql
	 */
	private String getCountSql(String hql) {
		
		String countsql = hql.trim();
		
		//hql是否带有order by
		if (countsql.toUpperCase().contains("ORDER BY")) {
			//含有order by，去掉order by
			countsql = hql.substring(0, countsql.indexOf("ORDER BY"));
		}
		return "SELECT COUNT(*) " + countsql;
	}
	
	/**
	 * 根据hql获取记录总数 
	 * @param hql
	 * @param value 参数
	 * @return 记录总数
	 */
	private int getTotal(String hql, Object[] value) {
		
		//通过一个HibernateCallback对象执行查询
		HibernateTemplate ht = this.getHibernateTemplate();
		HibernateCallB hb = new HibernateCallB();
		
		hb.setHql(hql);
		hb.setValue(value);
		
		List list = ht.execute(hb);
		
		return Integer.parseInt(list.get(0).toString());
	}
	
	/**
	 * 使用hql语句进行分页查询
	 * @param hql 需要查询的hql
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public PageInfo findByPage(final String hql, final int offset, final int pageSize) {
		getTotal(getCountSql(hql), null);
		//通过一个HibernateCallback对象执行查询
		List list = (List)this.getHibernateTemplate().execute(new HibernateCallback()
		{
			//实现HibernateCallback接口必须实现的方法
			public Object doInHibernate(Session session) throws HibernateException {
				//执行Hibernate分页查询
				List result = session.createQuery(hql)
						.setFirstResult(offset)
						.setMaxResults(pageSize)
						.list();
				return result;
			}
		});
		
		return new PageInfo(list, getTotal(getCountSql(hql), null), pageSize);
	}
	
	/**
	 * 使用hql语句进行分页查询
	 * @param hql 要查询的hql
	 * @param value 如果hql有一个参数需要传入，value就是传入hql语句的参数
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public PageInfo findByPage(final String hql, final Object value, final int offset, final int pageSize) {
		
		//通过一个HibernateCallback对象执行查询
		List list = (List)this.getHibernateTemplate().execute(new HibernateCallback()
		{
			//实现HibernateCallback接口必须实现的方法
			public Object doInHibernate(Session session) throws HibernateException {
				//执行Hibernate分页查询
				List result = session.createQuery(hql)
						//为hql语句传入参数
						.setParameter(0, value)
						.setFirstResult(offset)
						.setMaxResults(pageSize)
						.list();
				return result;
			}
		});
		
		Object[] obj = new Object[1];
		obj[1] = value;
		return new PageInfo(list, getTotal(getCountSql(hql), obj), pageSize);
	}
	
	/**
	 * 使用hql语句进行分页查询
	 * @param hql 要查询的hql
	 * @param value 如果hql有多个个参数需要传入，value就是传入hql语句的参数
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public PageInfo findByPage(final String hql, final Object[] value, final int offset, final int pageSize) {
		
		//通过一个HibernateCallback对象执行查询
		List list = (List)this.getHibernateTemplate().execute(new HibernateCallback()
		{
			//实现HibernateCallback接口必须实现的方法
			public Object doInHibernate(Session session) throws HibernateException {
				//执行Hibernate分页查询
				Query query = session.createQuery(hql);
				//为hql语句传入参数
				for (int i=0; i<value.length; i++) {
					query.setParameter(i, value[i]);
				}
				List result = query.setFirstResult(offset).setMaxResults(pageSize).list();		
				return result;
			}
		});
		
		return new PageInfo(list, getTotal(getCountSql(hql), value), pageSize);
	}
}
