/**
 * 
 */
package com.alex.onlinetest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.alex.onlinetest.dao.NavigatorDao;
import com.alex.onlinetest.hbm.Navigator;
import com.alex.onlinetest.hbm.Role;


public class NavigatorDaoImpl implements NavigatorDao {
	
	//定义一个HibernateTemplate 对象，用于执行持久化操作
	private HibernateTemplate ht = null;
	
	//Hibernate 持久化操作所需要的SessionFactory
	private SessionFactory sessionFactory;

	//依赖注入
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

	/* (non-Javadoc)
	 * @see com.alex.onlinetest.dao.NavigatorDao#get(java.lang.Integer)
	 */
	@Override
	public Navigator get(Integer id) {
		return getHibernateTemplate().get(Navigator.class, id);
	}

	/* (non-Javadoc)
	 * @see com.alex.onlinetest.dao.NavigatorDao#get(java.lang.String)
	 */
	@Override
	public Navigator get(String nodeName) {
		List<Navigator> navs = (List<Navigator>)getHibernateTemplate().find("from Navigator nav where nav.nodeName=?", nodeName);
		
		if (navs != null && navs.size()>0) {
			return navs.get(0);
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.alex.onlinetest.dao.NavigatorDao#save(com.alex.onlinetest.hbm.Navigator)
	 */
	@Override
	public Integer save(Navigator nav) {
		return (Integer)getHibernateTemplate().save(nav);
	}

	/* (non-Javadoc)
	 * @see com.alex.onlinetest.dao.NavigatorDao#update(com.alex.onlinetest.hbm.Navigator)
	 */
	@Override
	public void update(Navigator nav) {
		getHibernateTemplate().update(nav);
	}

	/* (non-Javadoc)
	 * @see com.alex.onlinetest.dao.NavigatorDao#delete(com.alex.onlinetest.hbm.Navigator)
	 */
	@Override
	public void delete(Navigator nav) {
		getHibernateTemplate().delete(nav);
	}

	/* (non-Javadoc)
	 * @see com.alex.onlinetest.dao.NavigatorDao#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));
	}

	/* (non-Javadoc)
	 * @see com.alex.onlinetest.dao.NavigatorDao#findAllNavigator()
	 */
	@Override
	public List<Navigator> findAllNavigator() {
		return (List<Navigator>)getHibernateTemplate().find("from Navigator");
	}

	/* (non-Javadoc)
	 * @see com.alex.onlinetest.dao.NavigatorDao#findAllNavigatorBySequence()
	 */
	@Override
	public List<Navigator> findAllNavigatorBySequence() {
		List<Navigator> navs = new ArrayList<Navigator>();
		
		List<Navigator> root = findAllRootBySequence();
		
		for (int i=0; i<root.size(); i++) {
			navs.add(root.get(i));
			List<Navigator> nodes = findAllNodesBySequence(root.get(i).getId());
			for (int n=0; n<nodes.size(); n++) {
				navs.add(nodes.get(n));
			}
		}
		
		return navs;
	}

	@Override
	public List<Navigator> findAllNodesBySequence(int parentId) {
		return (List<Navigator>)getHibernateTemplate().find("from Navigator nav where nav.parentId=? order by nav.nodeSequence", parentId);
	}

	@Override
	public List<Navigator> findAllRootBySequence() {
		return findAllNodesBySequence(0);
	}

	
}
