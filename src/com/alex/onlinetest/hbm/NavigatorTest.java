package com.alex.onlinetest.hbm;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.alex.onlinetest.util.Constant;

import junit.framework.TestCase;

public class NavigatorTest extends TestCase {
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

	public void testSaveNav() {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Navigator usermgnt = new Navigator();
		usermgnt.setNodeName("用户管理");
		usermgnt.setNodeSequence(1);
		session.save(usermgnt);
		
		Navigator qbmgnt = new Navigator();
		qbmgnt.setNodeName("题库管理");
		qbmgnt.setNodeSequence(2);
		session.save(qbmgnt);
		
		Navigator tpmgnt = new Navigator();
		tpmgnt.setNodeName("试卷管理");
		tpmgnt.setNodeSequence(3);
		session.save(tpmgnt);
		
		Navigator schmgnt = new Navigator();
		schmgnt.setNodeName("考试排期管理");
		schmgnt.setNodeSequence(4);
		session.save(schmgnt);
		
		session.getTransaction().commit();
		session.close();

	}
	
	public void testAddUserMgnt() {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Navigator grpmgnt = new Navigator();
		grpmgnt.setNodeName("组名维护");
		grpmgnt.setNodeSequence(1);
		grpmgnt.setParentId(1);
		session.save(grpmgnt);
		
		Navigator tpmgnt = new Navigator();
		tpmgnt.setNodeName("用户组管理");
		tpmgnt.setNodeSequence(2);
		tpmgnt.setParentId(1);
		session.save(tpmgnt);
		
		Navigator schmgnt = new Navigator();
		schmgnt.setNodeName("用户角色维护");
		schmgnt.setNodeSequence(3);
		schmgnt.setParentId(1);
		session.save(schmgnt);
		
		Navigator usrsmgnt = new Navigator();
		usrsmgnt.setNodeName("用户名单管理");
		usrsmgnt.setNodeSequence(4);
		usrsmgnt.setParentId(1);
		session.save(usrsmgnt);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void testAddQuestionBankMgnt() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Navigator grpmgnt = new Navigator();
		grpmgnt.setNodeName("题目主题维护");
		grpmgnt.setNodeSequence(1);
		grpmgnt.setParentId(2);
		session.save(grpmgnt);
		
		Navigator tpmgnt = new Navigator();
		tpmgnt.setNodeName("题目难易程度维护");
		tpmgnt.setNodeSequence(2);
		tpmgnt.setParentId(2);
		session.save(tpmgnt);
		
		Navigator schmgnt = new Navigator();
		schmgnt.setNodeName("题目维护");
		schmgnt.setNodeSequence(3);
		schmgnt.setParentId(2);
		session.save(schmgnt);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void testAddTpMgnt() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Navigator grpmgnt = new Navigator();
		grpmgnt.setNodeName("创建试卷");
		grpmgnt.setNodeSequence(1);
		grpmgnt.setParentId(3);
		session.save(grpmgnt);
		
		Navigator tpmgnt = new Navigator();
		tpmgnt.setNodeName("试卷列表");
		tpmgnt.setNodeSequence(2);
		tpmgnt.setParentId(3);
		session.save(tpmgnt);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void testAddSchMgnt() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Navigator grpmgnt = new Navigator();
		grpmgnt.setNodeName("创建考试排期");
		grpmgnt.setNodeSequence(1);
		grpmgnt.setParentId(4);
		session.save(grpmgnt);
		
		Navigator tpmgnt = new Navigator();
		tpmgnt.setNodeName("考试排期列表");
		tpmgnt.setNodeSequence(2);
		tpmgnt.setParentId(4);
		session.save(tpmgnt);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void testUpdate() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Navigator nav = session.get(Navigator.class, 18);
		nav.setUrl("newpaper");
		session.update(nav);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void testAddItem() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Navigator grpmgnt = new Navigator();
		grpmgnt.setNodeName("手工创建试卷-AJAX");
		grpmgnt.setNodeSequence(4);
		grpmgnt.setParentId(3);
		session.save(grpmgnt);
		
		session.getTransaction().commit();
		session.close();
	}
}
