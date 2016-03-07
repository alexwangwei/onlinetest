package com.alex.onlinetest.hbm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.alex.onlinetest.util.Constant;

import junit.framework.TestCase;

public class ScheduleTest extends TestCase {
	private SessionFactory sessionFactory;

	@Override
	protected void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}

	@Override
	protected void tearDown() throws Exception {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}
	
	
	public void testAddSchdule() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
		Schedule sch = new Schedule();
		
		sch.setDisableDate(new Date());
		sch.setDuration(45);
		sch.setEffectiveDate(new Date());
		sch.setName("20150913");
		sch.setStatus(Constant.SCHEDULE_STATUS.ACTIVE);
		sch.setPaper(session.get(Paper.class, 1));
		
		List<UserGroup> ug = new ArrayList<UserGroup>();
		
		ug.add(session.get(UserGroup.class, 10));
		ug.add(session.get(UserGroup.class, 11));
		
		
		
		//持久化
		session.save(sch);
		
		session.getTransaction().commit();
		session.close();
	}
	
	
	public void testDelSchedule() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.delete(session.get(Schedule.class, 2));
		
		session.getTransaction().commit();
		session.close();
	}

}
