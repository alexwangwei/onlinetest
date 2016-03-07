package com.alex.onlinetest.dao.test;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alex.onlinetest.admin.service.ScheduleManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.dao.RoleDao;
import com.alex.onlinetest.hbm.Role;
import com.alex.onlinetest.hbm.Schedule;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;

import junit.framework.TestCase;

public class ScheduleTest extends TestCase {

	public void testSchedule() throws Exception {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ScheduleManagement sche = (ScheduleManagement)context.getBean("scheduleManagement");
		
		Schedule schedule = sche.getById(1);
		Set<UserGroup> ug = schedule.getUsergroup();
		System.out.println("用户组个数："+ug.size());
	}
}
