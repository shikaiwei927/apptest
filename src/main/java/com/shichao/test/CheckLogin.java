package com.shichao.test;
import java.util.Scanner;
public class CheckLogin {
	public static void main(String[]args){
		Scanner scan=new Scanner(System.in);// ����ɨ����
		System.out.println("�������¼�û���:");
		String username=scan.nextLine();
		System.out.println("�������¼����:");
		String password=scan.nextLine();
		if(!username.equals("mr")){
			System.out.println("�û����Ƿ�");
		}else if(!password.equals("mrsoft")){
			System.out.println("��¼�������");
			}else{
			System.out.println("��ϲ�㣬��¼��Ϣͨ����֤.");
		}
	}

}
