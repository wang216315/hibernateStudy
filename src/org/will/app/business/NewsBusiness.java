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
			
			System.out.print("请输入标题(按回车确认):"); 
			String resTitle = sc.nextLine();
			news.setTitle(resTitle);
			
			System.out.println("请输入内容(按回车确认):");
			String resContent = sc.nextLine();
			news.setContent(resContent);			
			//保存
			Session sess = HibernateSessionFactory.getInstance().getCurrentSession();	
			Transaction tx = sess.beginTransaction();			
			sess.save(news);		
			tx.commit();
			HibernateSessionFactory.getInstance().closeCurrentSession();
			//sess.close();
			System.out.println("保存成功！");
			System.out.println("继续输入按1，按任意键退出。");
			res = sc.nextLine();
		} while (res.equals("1"));
		
		
	}
	
	private void showMenu()
	{
		List<String> funtions = new ArrayList<String>();
		funtions.add("1.增加文章");
		funtions.add("2.读取文章");
		System.out.println("请选择功能：");
		
		for (String f : funtions)
		{
			System.out.print(f +"\n");
		}
		System.out.println("请按照数字选择功能，按回车确定：");
	}
}
