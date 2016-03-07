package com.alex.onlinetest.dao.test;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alex.onlinetest.dao.RoleDao;
import com.alex.onlinetest.hbm.Role;
import com.alex.onlinetest.hbm.User;

import junit.framework.TestCase;

public class RoleDaoImplTest extends TestCase {

	public void testRoleDao() throws Exception {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		RoleDao userdao = (RoleDao)context.getBean("roleDao");
		Role test = new Role();
		test = userdao.get("test");
		if (test != null) {
			System.out.println("test.getId:" + test.getId());
			userdao.delete(test.getId());
		}
	}
}
