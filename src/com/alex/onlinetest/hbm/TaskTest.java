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

public class TaskTest extends TestCase {
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
	
	
	public void testAddTask() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Task task = new Task();
		
		task.setOwner("Admin");
		task.setSchedule(session.get(Schedule.class, 3));
		List<Item> items = new ArrayList<Item>();
		
		Item item = new Item();
		item.setTask(task);
		item.setQuestion(session.get(Question.class, 1));
		items.add(item);
		
		//持久化
		session.save(task);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void testdelTask() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.delete(session.get(Task.class, 1));
		
		session.getTransaction().commit();
		session.close();
	}

}
