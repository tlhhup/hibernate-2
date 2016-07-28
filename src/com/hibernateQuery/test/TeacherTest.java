package com.hibernateQuery.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.hibernateQuery.entity.Teacher;

public class TeacherTest {

	@Test
	public void delete(){
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Teacher teacher=new Teacher();
		teacher.setId(3);
		session.delete(teacher);
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
	
}
