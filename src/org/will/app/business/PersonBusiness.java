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
			System.out.print("�������գ�");
			sc = new Scanner(System.in);
			//String input = sc.nextLine();
			name.setFirstname("wang");
			System.out.print("����������");
			//input = sc.nextLine();
			name.setLastname("yue");
			person.setName(name);
			System.out.print("���������䣺");
			//String age = sc.nextLine();
			person.setAge(Integer.parseInt("30"));
			System.out.print("���������ѧУ�б��Զ��Ÿ�����");
			//String school = sc.nextLine();
			String school = "���������ѧУ,�����Ƽ���ѧ";
			String[] schools = school.split(",");
			Collections.addAll(person.getSchools(), schools);
			System.out.print("�����뿼�Գɼ�����ʽ(����,100)|(��ѧ,120)|(Ӣ��,130)��");
			//String score = sc.nextLine();
			processScore("(����,100)|(��ѧ,120)|(Ӣ��,130)", person);		
			
			System.out.print("��������ϵ��ַ��");
			//String add = sc.nextLine();
			Address address = new Address("��������·");
			person.setAddress(address);
					
			
			News news = new News();
			news.setTitle("���˼��");
			
			System.out.println("��������˼�飺");
			String title = sc.nextLine();
			news.setContent(title);
			person.setNews(news);
			
			
			//���person�����������Ķ���news������ע����û������ cascade=cascadeType.ALL�Ļ���news��Ҫ��person�������д���뱣�档
			//��Ϊhibernate��ܾͲ����Զ����������ˡ�
			
			//ʵ����Ҫ�������棬�����¼�����ȱ���
			session.save(person);
			//�ٱ�������ı����
			//session.save(news);
			
			tx.commit();
						
			session.close();		
			System.out.print("����ɹ�����1�������룬��������˳���");
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
