package org.will.app.business;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSessionFactory
{
	public static HibernateSessionFactory sessFactory = null;
	
	SessionFactory sf = null;
	
	//session�����̰߳�ȫ�ģ��������������ThreadLocal���������ж��̴߳���
	public final ThreadLocal<Session> session = new ThreadLocal<Session>();
	
	
	public static HibernateSessionFactory getInstance()
	{
		synchronized (HibernateSessionFactory.class)
		{
			if(sessFactory == null)
			{
				sessFactory = new HibernateSessionFactory();
			}
			
		}
		return sessFactory;
	}
	
	
	private HibernateSessionFactory()
	{		
		sf = getSessionFactory();
	}
	
	public Session getCurrentSession()
	{
		Session s = session.get();
		if(s == null)
		{
			s = sf.openSession();
			session.set(s);
		}
		
		return s;
	}
	
	public void closeCurrentSession()
	{
		Session s = session.get();
		if(s != null)
		{
			s.close();
			session.set(null);
		}
		
		
	}
	
	
    /*
     * �õ� Hibernate SessionFactroy DB��������
     */
	private SessionFactory getSessionFactory()
	{
		StandardServiceRegistry standardRegistry = 
				new StandardServiceRegistryBuilder().configure().build();
		Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder()
				.applyImplicitNamingStrategy(ImplicitNamingStrategyComponentPathImpl.INSTANCE)
				.build();
		
		return metadata.getSessionFactoryBuilder().build();
	}
	
	
}
