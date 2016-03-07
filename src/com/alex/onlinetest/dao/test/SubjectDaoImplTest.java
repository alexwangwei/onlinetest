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
import com.alex.onlinetest.dao.SubjectDao;
import com.alex.onlinetest.hbm.Role;
import com.alex.onlinetest.hbm.User;

import junit.framework.TestCase;

public class SubjectDaoImplTest extends TestCase {

	public void testSubjectDao() throws Exception {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SubjectDao subDao = (SubjectDao)context.getBean("subjectDao");
		subDao.delete(subDao.get("测试").getId());
	}
}
