package org.will.app.main;

import java.util.Scanner;

import org.will.app.business.NewsBusiness;
import org.will.app.business.PersonBusiness;

public class Menu
{
	private Scanner sc;

	public void showMenu()
	{
		System.out.println("��ѡ����Ҫ����������ͣ�\n"+"1.���� 2.����,��q�˳�����");
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
