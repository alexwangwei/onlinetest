package com.alex.onlinetest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.alex.onlinetest.dao.RoleDao;
import com.alex.onlinetest.hbm.Role;

public class RoleDaoImpl implements RoleDao {
	
	//定义一个HibernateTemplate 对象，用于执行持久化操作
	private HibernateTemplate ht = null;
	
	//Hibernate 持久化操作所需要的SessionFactory
	private SessionFactory sessionFactory;
	
	private PageResultDaoSupport pageResultDaoSupport;

	//依赖注入
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
	public Role get(Integer id) {
		return getHibernateTemplate().get(Role.class, id);
	}

	@Override
	public Role get(String roleName) {
		List<Role> roles = (List<Role>)getHibernateTemplate().find("from Role r where r.roleName=?", roleName);
		
		if (roles != null && roles.size()>0) {
			return roles.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Integer save(Role role) {
		return (Integer)getHibernateTemplate().save(role);
	}

	@Override
	public void update(Role role) {
		getHibernateTemplate().update(role);
	}

	@Override
	public void delete(Role role) {
		getHibernateTemplate().delete(role);
	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));
	}

	@Override
	public List<Role> findAllRole() {
		return (List<Role>)getHibernateTemplate().find("from Role");
	}

	@Override
	public PageInfo getResultByPage(int offset, int pageSie) {

		return this.pageResultDaoSupport.findByPage("from Role", offset, pageSie);
	}
}
