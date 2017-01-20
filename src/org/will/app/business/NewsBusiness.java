package org.will.app.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.will.app.domain.News;

public class NewsBusiness
{
	private Scanner sc;
	
	//private SessionFactory sf;
	
	public NewsBusiness()
	{
		//sf = HibernateSessionFactory.getInstance().getSessionFactory();
	}
	

	public void menu()
	{		
		
		this.showMenu();
		
		sc = new Scanner(System.in);
		String res = sc.nextLine();
		switch(res)
		{
			case "1":
				inputContent();
				break;
			case "2":
				showContent();
				break;
			default:
				showMenu();
				break;
		}
		
	}
	
	private void showContent()
	{
		//Session sess = sf.openSession();
		Session sess = HibernateSessionFactory.getInstance().getCurrentSession();
		Transaction tr = sess.beginTransaction();
		
		News news = (News)sess.get(News.class,15);
				
		tr.commit();
		//sess.close();
		HibernateSessionFactory.getInstance().closeCurrentSession();
		System.out.print(news.getFullcontent()+"\n");
		
		
	}
	
	private void inputContent()
	{
		String res;
		do
		{
			News news = new News();
			
			System.out.print("���������(���س�ȷ��):"); 
			String resTitle = sc.nextLine();
			news.setTitle(resTitle);
			
			System.out.println("����������(���س�ȷ��):");
			String resContent = sc.nextLine();
			news.setContent(resContent);			
			//����
			Session sess = HibernateSessionFactory.getInstance().getCurrentSession();	
			Transaction tx = sess.beginTransaction();			
			sess.save(news);		
			tx.commit();
			HibernateSessionFactory.getInstance().closeCurrentSession();
			//sess.close();
			System.out.println("����ɹ���");
			System.out.println("�������밴1����������˳���");
			res = sc.nextLine();
		} while (res.equals("1"));
		
		
	}
	
	private void showMenu()
	{
		List<String> funtions = new ArrayList<String>();
		funtions.add("1.��������");
		funtions.add("2.��ȡ����");
		System.out.println("��ѡ���ܣ�");
		
		for (String f : funtions)
		{
			System.out.print(f +"\n");
		}
		System.out.println("�밴������ѡ���ܣ����س�ȷ����");
	}
}
