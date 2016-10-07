package com.shichao.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

/**
 * 智能网关APP修改密码开锁
 */




public class TestLocks {
	static int lockoknum=0;
	static int lockfailnum=0;
	static int LongPwdresetoknum=0;
	static int LongPwdresetfailnum=0;
	private AndroidDriver  driver;
    public static boolean lock(String pwd) throws MalformedURLException{T 
    	boolean flag=false;
    	
    	File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");  //构造方法 parent 抽象路径名 child 路径名字字符串
		File app = new File(appDir, "app-debug.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		//capabilities.setCapability("deviceName","Mi-4c");
		//capabilities.setCapability("deviceName","SCL-TL00H");
	  capabilities.setCapability("deviceName","HM NOTE 1S CT");
		
		capabilities.setCapability("platformVersion", "5.1");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.d3tech.smartgateway");
		capabilities.setCapability("appActivity", "com.d3tech.smartgateway.activity.WelcomeActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
    	try {
			//WebElement el = driver.findElement(By.id("com.d3tech.smartgateway:id/joker_dialog_button_left"));
			//el.click();
			List<WebElement> textFieldsList = driver.findElements(By.id("com.d3tech.smartgateway:id/sk_text_gateway_name"));
			for(WebElement textfield:textFieldsList){
				String temp=textfield.getText();
				//System.out.println(temp);
				if(temp.equals("�������� ")){
					textfield.click();
					break;  
				}
			}
				
			List<WebElement> textkeyList = driver.findElementsByClassName("android.widget.TextView");
			for(WebElement textkey:textkeyList){
			 	if(textkey.getText().equals("��������")){
			 		textkey.click();
			 		break;
			 	}
			}
			//����
			driver.findElement(By.id("com.d3tech.smartgateway:id/sk_image_lock_main_unlock")).click();
			driver.findElement(By.id("com.d3tech.smartgateway:id/sk_edit_lock_unlock_pwd_lockpwd")).sendKeys(pwd);
			driver.findElement(By.id("com.d3tech.smartgateway:id/sk_text_lock_unlock_pwd_confirm")).click();
			String test=driver.findElement(By.id("com.d3tech.smartgateway:id/sk_text_lock_main_lock_enable")).getText();
			System.out.println(test);
			if(test.equals("���ѿ���")){
				flag=true;
				
			}
			
		} 
			// TODO Auto-generated catch block
		
			
			catch (Exception e) {
				e.printStackTrace();
			
		}
    	finally {
    		driver.quit();
    		return flag;
		}
    	
    }

    
    
    public static boolean LongPwdreset(String superpwd,String modifypwd) throws MalformedURLException {
    	boolean flag=false;
    	
    	AndroidDriver  driver;
    	File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "app-debug.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		//capabilities.setCapability("deviceName","Mi-4c");
		//capabilities.setCapability("deviceName","SCL-TL00H");
		capabilities.setCapability("deviceName","HM NOTE 1S CT");
		
		capabilities.setCapability("platformVersion", "4.4");
		//capabilities.setCapability("platformVersion", "5.1.1");
		
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.d3tech.smartgateway");
		capabilities.setCapability("appActivity", "com.d3tech.smartgateway.activity.WelcomeActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(27, TimeUnit.SECONDS);
		
    	try {
			//WebElement el = driver.findElement(By.id("com.d3tech.smartgateway:id/joker_dialog_button_left"));
			//el.click();
			List<WebElement> textFieldsList = driver.findElements(By.id("com.d3tech.smartgateway:id/sk_text_gateway_name"));
			for(WebElement textfield:textFieldsList){
				String temp=textfield.getText();
				//System.out.println(temp);
				if(temp.equals("��������")){
					textfield.click();
					break;
				}
			}
				
			List<WebElement> textkeyList = driver.findElementsByClassName("android.widget.TextView");
			for(WebElement textkey:textkeyList){
			 	if(textkey.getText().equals("��������")){
			 		textkey.click();
			 		break;
			 	}
			}
			//��������������
			driver.findElementById("com.d3tech.smartgateway:id/sk_image_lock_main_pwd").click();
			driver.findElementById("com.d3tech.smartgateway:id/textView33").click();
			List<WebElement> longlist=driver.findElementsById("com.d3tech.smartgateway:id/sk_text_listview_lock_valid_pwd_manager_index");
			for(WebElement longpwd:longlist){
				if(longpwd.getText().equals("002")){
					longpwd.click();
					break;
				}
			}
			
			//�������������� 
			//driver.findElementById("com.d3tech.smartgateway:id/sk_text_listview_lock_valid_pwd_manager_name").click();
			List<WebElement> operalist=driver.findElementsByClassName("android.widget.RelativeLayout");
			for(WebElement opera:operalist){
				if(opera.getText().equals("")){
					opera.click();
					break;
			}
			}
			
			//��ӿ����������
			driver.findElementById("com.d3tech.smartgateway:id/sk_edit_lock_reset_unlock_pwd_inputpwd").sendKeys(modifypwd);
			driver.findElementById("com.d3tech.smartgateway:id/action_settings").click();
			//�����֤����
			driver.findElementById("com.d3tech.smartgateway:id/sk_edit_lock_manager_pwd_magerpwd").sendKeys(superpwd);
			driver.findElementById("com.d3tech.smartgateway:id/sk_text_lock_manager_pwd_confirm").click();
			
			//��֤ͨ������ת��������������
			List<WebElement> testlist=driver.findElementsById("com.d3tech.smartgateway:id/sk_text_listview_lock_valid_pwd_manager_index");
			for(WebElement test:testlist){
				if(test.getText().equals("002")){
					flag=true;
					break;
				}
			}
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally {
    		driver.quit();
    		return flag;
		}
    	
    }
    
    
    
	public static void main(String args[]){
		
		try {
			String superpwd="1234";
			String modifypwd;
			for(int i=0;i<100;i++){
				modifypwd="1111"+i;
				System.out.println(modifypwd);
				if(LongPwdreset(superpwd, modifypwd)){
					LongPwdresetoknum++;
					System.out.println("di "+i+" reset pwd success");
					if(lock(modifypwd)){
						System.out.println("unlock success");
						lockoknum++;
					}
					else {
						System.out.println("unlock failed");
						lockfailnum++;
					}
				}
				else{
					System.out.println("di "+i+" reset pwd failed");
					LongPwdresetfailnum++;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("�����������óɹ�"+LongPwdresetoknum);
			System.out.println("������������ʧ��"+LongPwdresetfailnum);
			System.out.println("�����ɹ�"+lockoknum+"��");
			System.out.println("����ʧ��"+lockfailnum+"��");
		}
	}
    
    
    
    
}
