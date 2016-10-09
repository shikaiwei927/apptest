package com.shikai.task;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Demo1  {
	static int lockoknum=0;  
	static int lockfailnum=0;
	static int LongPwdresetoknum=0;
	static int LongPwdresetfailnum=0;
	static AndroidDriver<AndroidElement> driver;
	public  static void setUp() throws Exception {
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "智能共盒V1.0.6.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.UDID, "JTJ4C15A15012272");//devices ID
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "SCL-TL00H"); // 真机名字
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1"); //真机版本
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath()); //对应的被测APK文件路径
		capabilities.setCapability("appPackage", "com.d3tech.smartgateway");
		capabilities.setCapability("appActivity", "com.d3tech.smartgateway.activity.WelcomeActivity");
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
    public static boolean lock(String pwd) throws Exception{
		 	boolean flag=false;
			
    	try {
			
			List<AndroidElement> textFieldsList = driver.findElements(By.id("sk_text_gateway_name"));
			for(AndroidElement textfield:textFieldsList){
				String temp=textfield.getText();
				if(temp.equals("智能网关")){
					textfield.click();
					break;  
				}
			}
				
			List<AndroidElement> textkeyList = driver.findElements(By.id("sk_listview_device_list_name"));
			for(AndroidElement textkey:textkeyList){
			 	if(textkey.getText().equals("智能门锁")){
			 		textkey.click();
			 		break;
			 	}
			}
			//开锁
			driver.findElement(By.id("sk_image_lock_main_unlock")).click();
			driver.findElement(By.id("sk_edit_lock_unlock_pwd_lockpwd")).sendKeys(pwd);
			driver.findElement(By.id("sk_text_lock_unlock_pwd_confirm")).click();
			String test=driver.findElement(By.id("sk_text_lock_main_lock_enable")).getText();
			System.out.println(test);
			if(test.equals("门已开启")){
				flag=true;
			}
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
    	finally {
    		driver.quit();
    		return flag;
		}
    }
    public static void snapshot(TakesScreenshot drivername, String filename)
 
    {
      String currentPath = System.getProperty("user.dir");
      System.out.println(currentPath);
      File scrFile = drivername.getScreenshotAs(OutputType.FILE);
   
          try {
              System.out.println("save snapshot path is:"+currentPath+"/"+filename);
              FileUtils.copyFile(scrFile, new File(currentPath+"\\"+filename));
          } catch (IOException e) {
         
              System.out.println("Can't save screenshot");
              e.printStackTrace();
          }
          finally
          {
              System.out.println("screen shot finished");
          }
    }
    public static boolean LongPwdreset(String superpwd,String modifypwd) throws Exception {
    	boolean flag=false;
    
    	try {
		
		
			List<AndroidElement> textFieldsList = driver.findElements(By.id("sk_text_gateway_name"));
			for(AndroidElement textfield:textFieldsList){
				String temp=textfield.getText();
		
				if(temp.equals("智能网关")){
					textfield.click();
					break;
				}
			}
				
			List<AndroidElement> textkeyList = driver.findElements(By.id("sk_listview_device_list_name"));
			for(AndroidElement textkey:textkeyList){
			 	if(textkey.getText().equals("智能门锁")){
			 		textkey.click();
			 		break;
			 	}
			}
			
			//长期密码管理
			driver.findElementById("sk_image_lock_main_pwd").click();
			driver.findElementById("textView33").click();
			List<AndroidElement> longlist=driver.findElements(By.id("sk_text_listview_lock_valid_pwd_manager_index"));
			for(AndroidElement longpwd:longlist){
				if(longpwd.getText().equals("001")){
					longpwd.click();
					break;
				}
			}
			
			
			//	重置密码管理界面		
			List<AndroidElement> operalist=driver.findElements(By.className("android.widget.TextView"));
			for(AndroidElement opera:operalist){
				if(opera.getText().equals("重置密码")){
					opera.click();
					break;
			}
			}
			
			//身份验证界面
			driver.findElementById("sk_edit_lock_reset_unlock_pwd_inputpwd").sendKeys(modifypwd);
			driver.findElementById("action_settings").click();
			driver.findElementById("sk_edit_lock_manager_pwd_magerpwd").sendKeys(superpwd);
			driver.findElementById("sk_text_lock_manager_pwd_confirm").click();
			
			
			List<AndroidElement> testlist=driver.findElementsById("sk_text_listview_lock_valid_pwd_manager_index");
			for(AndroidElement test:testlist){
				if(test.getText().equals("001")){
					flag=true;
					break;
				}
			}
		
			
		} catch (Exception e) {
	
			e.printStackTrace();
			String time = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
			snapshot((TakesScreenshot)driver, "images/"+time+".png");
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
						SimpleDateFormat time=new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                        String TimeString = time.format(new java.util.Date());
                        System. out.println(TimeString );

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
			System.out.println("长期密码重置成功"+LongPwdresetoknum);
			System.out.println("长期密码重置失败"+LongPwdresetfailnum);
			System.out.println("开锁成功"+lockoknum+"次");
			System.out.println("开锁失败"+lockfailnum+"次");
		}
	}
   
   
   
   
}






