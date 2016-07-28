package com.hibernateQuery.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.hibernateQuery.entity.Student;
import com.hibernateQuery.entity.Teacher;

public class StudentTest {

	@SuppressWarnings("deprecation")
	@Test
	public void get(){
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Student student = (Student) session.get(Student.class, "297ea7a1562b3d8401562b3d877a0000");
		System.out.println(student);
		
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void save(){
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//创建了一个教师对象
		Teacher teacher=new Teacher();
		teacher.setName("李四");
		
		//将teacher对象先进行持久化
		//session.save(teacher);
		
		Student student=new Student();
		student.setName("张三1");
		
		//建立关系
		student.setTeacher(teacher);
		
		session.save(student);
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
}
