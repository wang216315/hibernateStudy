package org.will.app.business;

import java.util.Collections;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.will.app.domain.Address;
import org.will.app.domain.Car;
import org.will.app.domain.Name;
import org.will.app.domain.News;
import org.will.app.domain.Person;
import org.will.app.domain.Phone;

public class PersonBusiness
{
	private Scanner sc;
	Session session;
	
	public PersonBusiness()
	{
		session = HibernateSessionFactory.getInstance().getCurrentSession();
	}

	public void menu()
	{
		insertPerson();
	}
	
	private void insertPerson()
	{
		
		
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
			
			processWorkAddress(person);
			processCar(person);
			processWorkPhone(person,session);
			
			
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
	
	private void processCar(Person person)
	{		
		do
		{
			System.out.println("������������Ϣ(Ʒ��,ϵ��,�ͺ�):");
			String res = sc.nextLine();
			String[] results = res.split(",");
			Car car = new Car();			
			car.setCarBrand(results[0]);
			car.setCarSeries(results[1]);
			car.setCarModel(results[2]);
			person.getCars().add(car);
			car.setPerson(person);
			System.out.print("�ٴ������밴1,�����������������Ϣ���롣");			
		}
		while(sc.nextLine().equals("1"));
		
		
	}

	
	private void processWorkPhone(Person person,Session session)
	{
		System.out.println("�����빤���绰������绰�Զ��ŷָ���");
		String res = sc.nextLine();
		String[] phones = res.split(",");
		for (String a : phones)
		{
			Phone phone = new Phone();
			phone.setPhoneMode("����");
			phone.setPhoneNumber(a);		
			person.getWorkPhones().add(phone);				
		}
	}
	
	private void processWorkAddress(Person person)
	{
		System.out.println("�����빤����ַ�������ַ�Զ��ŷָ���");
		String res = sc.nextLine();
		String addresses[] = res.split(",");
		for (String a : addresses)
		{
			Address address = new Address(a);
			person.getWorkAddress().add(address);
		}
		
	}
	
	private void processScore(String target,Person person)
	{
		String[] ab = target.split("\\|");
		
		for (String t : ab)
		{
			int i = t.length();
			t = t.substring(1, i-1);
			String[] t1 = t.split(",");
			person.getScores().put(t1[0].trim(),Float.parseFloat(t1[1].trim()));			
		}	
	}
	
}
;