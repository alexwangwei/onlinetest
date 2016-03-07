package com.alex.onlinetest.hbm;

import java.util.ArrayList;
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

public class QuestionTest extends TestCase {
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

	public void testSaveQuestion() {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Question q1 = new Question();
		q1.setCreateDTime(new Date());
		
		//admin
		q1.setCreator(session.get(User.class, 4).getLoginId());
		
		q1.setDescription("测试问题");
		q1.setQuestionLevel(Constant.QUESTION_LEAVEL.MIDDLE);
		q1.setQuestionType(Constant.QUESTION_TYPE.SINGLE);
		q1.setRefAnswer("test");
		q1.setStatus(Constant.QUESTION_STATUS.DRAFT);
		//web
		q1.setSubject(session.get(Subject.class, 16));
		q1.setValue(5);
		
		List<QuestionOption> oplist = new ArrayList<QuestionOption>();
		QuestionOption op1 = new QuestionOption();
		QuestionOption op2 = new QuestionOption();
		op1.setOptionDescription("选项1");
		op1.setSequence(1);
		op1.setQuestion(q1);
		op2.setOptionDescription("选项2");
		op2.setSequence(2);
		op2.setQuestion(q1);
		oplist.add(op1);
		oplist.add(op2);
		
		session.save(q1);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void testDelQuestion() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.delete(session.get(Question.class, 1));
		
		session.getTransaction().commit();
		session.close();
	}
}
