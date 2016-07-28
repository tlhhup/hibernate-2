# hibernate-2
5. 关系映射
	1. 一对多：one-many
		1. 实体类中：定义set集合

			public class Teacher implements Serializable {

				private static final long serialVersionUID = 1L;
			
				private int id;
				private String name;
				private String address;
			
				// 构建与学生一对多的关系
				private Set<Student> students;
		2. 映射文件中

			 	<!-- 与学生的一对多关系 
		        	lazy:执行查询教师记录的时候就一并查询学生表中的数据得到学生的信息
		        -->
		        <set name="students" lazy="false">
		            <key column="teacherId"/>
		            <one-to-many class="com.hibernate.entity.Student"/>
		        </set>
	2. 多对一：many-one------->
		1. 实体类

				// 构建与教师的多对一关系：外键
				private Teacher teacher;
		2. 映射文件

				<!-- 与教师的多对一关系:外键
		        	 column：外键的名称
		        	 cascade：级联,执行该操作时候，一并其他的操作
		         -->
		        <many-to-one name="teacher" column="teacherId" cascade="delete"/>
	3. 一对一：one-one
		1. 实体类
			
				// 与用户的一对一关系
				private User user;
		2. 映射文件

				<class name="IDCard" table="idCards">
			        <id name="id" column="id" type="int">
			            <!-- 
			            	foreign：外键，值来源于另一张表,设置数据来源
			             -->
			            <generator class="foreign">
			                <param name="property">user</param>
			            </generator>
			        </id>
			        <property name="number" column="number" type="string" length="100"/>
			        
			        <!-- 
			        	多对一的特殊情况
			         -->
			        <one-to-one name="user" constrained="true"/>
			    </class>
	4. 多对多：many-many------>
		1. 实体类
			
				// 与角色的多对多关系
				private Set<Role> roles;
		2. 映射文件
				
				<!-- 与角色的多对多关系:中间表
		        	table:中间表的名称
		         -->
		        <set name="roles" table="user_role_links" cascade="save-update" lazy="false">
		            <!-- 中间表的用户的id所对应的字段 -->
		            <key column="userId"/>
		            <many-to-many column="roleID" class="Role"/>
		        </set>
	5. 级联操作
		
		Casade用来说明当对主对象进行某种操作时是否对其关联的从对象也作类似的操作 java代码中设置了对象关联之后有效  两对象有关联的时候可以配置
      none,all,save-update ,delete, lock,refresh,evict,replicate,persist,
      merge,delete-orphan(one-to-many) 。一般对many-to-one,many-to-many不设置级联，在<one-to-one>和<one-to-many>中设置级联
	6. 在one-to-many或many-to-many设置对象关联的时候配置inverse="true"(及对该属性的设置不理会)则表名one这边放弃对关系的维护交由many端维护
	7. hibernate javabean中的集合必须定义为接口类型

		集合映射set list bag(和set相同，java代码中数据类型使用list) map array(和list相同，Java代码中数据类型使用对象的数组)
		  使用list的时候要在set的方式的前提上多加一个配置信息list-index(定义一个列存储数据加入的顺序)
		  使用map的时候要在set的方式的前提上多加一个配置信息map-key(定义一个列存储数据加入的key)
5. hql查询
	1. hql查询:hibernate query language
		1. hibernate分页处理
			
				//设置查询的位置
				query.setFirstResult(1);
				//设置返回的数据条数
				query.setMaxResults(10);
		2. hql查询 from 必须根 实体类的类名 查询条件必须为实体类的属性名
				
				 String hql="from Role where name=:n and description=:d";
	2. 条件查询:
		1. Criteria对象
		2. 组织条件：Restrictions

				//条件查询
				//Restrictions:拼接查询条件的
				Criteria criteria = session.createCriteria(Role.class);
				//添加查询条件
				criteria.add(Restrictions.like("name", "%用户"));
				
				List list = criteria.list();
