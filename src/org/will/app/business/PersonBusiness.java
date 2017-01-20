package org.will.app.business;

import java.util.Collections;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.will.app.domain.Address;
import org.will.app.domain.Name;
import org.will.app.domain.News;
import org.will.app.domain.Person;

public class PersonBusiness
{
	private Scanner sc;

	public void menu()
	{
		insertPerson();
	}
	
	private void insertPerson()
	{
		Session session = HibernateSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Person person = new Person();
		Name name = new Name();
				
		do
		{
			System.out.print("请输入姓：");
			sc = new Scanner(System.in);
			//String input = sc.nextLine();
			name.setFirstname("wang");
			System.out.print("请输入名：");
			//input = sc.nextLine();
			name.setLastname("yue");
			person.setName(name);
			System.out.print("请输入年龄：");
			//String age = sc.nextLine();
			person.setAge(Integer.parseInt("30"));
			System.out.print("请输入教育学校列表，以逗号隔开：");
			//String school = sc.nextLine();
			String school = "德阳外国语学校,北京科技大学";
			String[] schools = school.split(",");
			Collections.addAll(person.getSchools(), schools);
			System.out.print("请输入考试成绩，格式(语文,100)|(数学,120)|(英语,130)：");
			//String score = sc.nextLine();
			processScore("(语文,100)|(数学,120)|(英语,130)", person);		
			
			System.out.print("请输入联系地址：");
			//String add = sc.nextLine();
			Address address = new Address("北京建国路");
			person.setAddress(address);
					
			
			News news = new News();
			news.setTitle("个人简介");
			
			System.out.println("请输入个人简介：");
			String title = sc.nextLine();
			news.setContent(title);
			person.setNews(news);
			
			
			//如果person对象所关联的对象news对象，在注解总没有设置 cascade=cascadeType.ALL的话，news需要在person保存后再写代码保存。
			//因为hibernate框架就不会自动级联保存了。
			
			//实体需要单独保存，主表记录必须先保存
			session.save(person);
			//再保存关联的表对象
			//session.save(news);
			
			tx.commit();
						
			session.close();		
			System.out.print("保存成功！按1继续输入，按任意键退出。");
		}
		while(sc.nextLine().equals("1"));
	}
	
	private void processScore(String target,Person person)
	{
		String[] ab = target.split("\\|");
		
		for (String t : ab) {
			int i = t.length();
			t = t.substring(1, i-1);
			String[] t1 = t.split(",");
			person.getScores().put(t1[0].trim(),Float.parseFloat(t1[1].trim()));			
		}	
	}
	
}
