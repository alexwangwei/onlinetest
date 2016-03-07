package com.alex.onlinetest.dao.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alex.onlinetest.admin.service.PaperManagement;
import com.alex.onlinetest.dao.PaperDao;
import com.alex.onlinetest.dao.RoleDao;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.Role;
import com.alex.onlinetest.hbm.User;

import junit.framework.TestCase;

public class PaperDaoImplTest extends TestCase {

	public void testDeletePaperById() throws Exception {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PaperManagement pm = (PaperManagement)context.getBean("paperManagement");
		
		Paper pa = pm.getById(1);
		Map<Integer, Question> maps = pa.getQuestions();
		System.out.print("a");
	}
}
