package com.hibernateQuery.test;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.hibernateQuery.entity.IDCard;
import com.hibernateQuery.entity.User;

public class IDCardTest {

	@Test
	public void save(){
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		User user=new User();
		user.setUserName("ÀîËÄ");
		
		session.save(user);
		
		IDCard idCard=new IDCard();
		idCard.setNumber(UUID.randomUUID().toString());
	
		idCard.setUser(user);
		
		session.save(idCard);
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
}
