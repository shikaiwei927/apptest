package com.shichao.test;

import java.util.Scanner;

public class InputCode {
	public static void main(String[]args){
	Scanner  scanner=new Scanner(System.in);
	System.out.println("请输入你的身份证号:");
	String line=scanner.nextLine();
	System.out.println("原来你身份号是"+line.length()+"位数字呀");
	
	

}
}