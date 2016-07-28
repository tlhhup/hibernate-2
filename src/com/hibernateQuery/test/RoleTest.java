package com.hibernateQuery.test;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.hibernateQuery.entity.Role;
import com.hibernateQuery.entity.User;

public class RoleTest {

	@Test
	public void save(){
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Role role=new Role();
		role.setName("�����û�");
		role.setDescription("���");
		
		session.save(role);
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void get(){
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		User user = (User) session.get(User.class, 3);
		
		session.close();
		sessionFactory.close();
		Set<Role> roles = user.getRoles();
		System.out.println(roles.size());
	}
	
	@Test
	public void query(){
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		//ȡ�����ķ�ʽ���ò��� : ����
//		String hql="from Role where name=:n and description=:d";
		String hql="from Role";
		
		//hql��ѯ from ����� ʵ��������� ��ѯ��������Ϊʵ�����������
		Query query = session.createQuery(hql);

		//���ò���
//		query.setString("n", "�����û�");
//		query.setString("d", "���");
		//�±��Ǵ�0��ʼ��
//		query.setString(0, "h");
		
		//ִ�в�ѯ-->����
//		List<Role> roles = query.list();
		
		//һ������-->��֤��ѯ�Ľ��ֻ��һ��
		//Role role = (Role) query.uniqueResult();
		
		//��ҳ  mysql limit offset,size
		//���ò�ѯ��λ��
		query.setFirstResult(1);
		//���÷��ص���������
		query.setMaxResults(10);
		
		List<Role> roles = query.list();
		
		session.close();
		sessionFactory.close();
		
		System.out.println(roles.size());
	}
	
	@Test
	public void Criteria(){
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		//������ѯ
		//Restrictions:���Ҳ�ѯ������
		Criteria criteria = session.createCriteria(Role.class);
		//��Ӳ�ѯ����
		criteria.add(Restrictions.like("name", "%�û�"));
		
		List list = criteria.list();
		
		session.close();
		sessionFactory.close();
		System.out.println(list.size());
		
		//����
		//һ�����棺session--->Ĭ�ϴ�
		//�������棺sessionFactory��Χ-->�������Ŀ��--->�ر�
		//����Դ  ���ݿ����ӳ�
	}
	
}
