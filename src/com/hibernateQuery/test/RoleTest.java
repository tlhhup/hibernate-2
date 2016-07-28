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
		role.setName("特殊用户");
		role.setDescription("你好");
		
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
		
		//取别名的方式设置参数 : 别名
//		String hql="from Role where name=:n and description=:d";
		String hql="from Role";
		
		//hql查询 from 必须根 实体类的类名 查询条件必须为实体类的属性名
		Query query = session.createQuery(hql);

		//设置参数
//		query.setString("n", "特殊用户");
//		query.setString("d", "你好");
		//下标是从0开始的
//		query.setString(0, "h");
		
		//执行查询-->集合
//		List<Role> roles = query.list();
		
		//一个数据-->保证查询的结果只有一个
		//Role role = (Role) query.uniqueResult();
		
		//分页  mysql limit offset,size
		//设置查询的位置
		query.setFirstResult(1);
		//设置返回的数据条数
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
		
		//条件查询
		//Restrictions:并且查询条件的
		Criteria criteria = session.createCriteria(Role.class);
		//添加查询条件
		criteria.add(Restrictions.like("name", "%用户"));
		
		List list = criteria.list();
		
		session.close();
		sessionFactory.close();
		System.out.println(list.size());
		
		//缓存
		//一级缓存：session--->默认打开
		//二级缓存：sessionFactory范围-->第三方的框架--->关闭
		//数据源  数据库连接池
	}
	
}
