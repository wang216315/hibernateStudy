package org.will.app.main;

import java.util.Scanner;

import org.will.app.business.NewsBusiness;
import org.will.app.business.PersonBusiness;

public class Menu
{
	private Scanner sc;

	public void showMenu()
	{
		System.out.println("请选择你要的输入的类型：\n"+"1.新闻 2.个人,按q退出程序。");
		sc = new Scanner(System.in);
		String choice = sc.nextLine();
		
			switch(choice)
			{
			 case "1":
				new NewsBusiness().menu();
				break;
			 case "2":
				new PersonBusiness().menu();
				break;
			 case "q":
				System.exit(0);
				break;
			 default:
				this.showMenu();			 
			}
						
	}
}
