package com.shikai.task;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
 
public class TaskKey {
    private  AndroidDriver<AndroidElement>  driver;
 
    @BeforeMethod(alwaysRun=true)
    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "app-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.UDID, "JTJ4C15A15012272");//devices ID
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "SCL-TL00H"); // 真机名字
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1"); //真机版本
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath()); //对应的被测APK文件路径
        capabilities.setCapability("appPackage", "com.d3tech.smartgateway");
        capabilities.setCapability("appActivity", "com.d3tech.smartgateway.activity.WelcomeActivity");
    	driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
 
    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        driver.quit();
    }
    

    
 
    @Test(groups={"addContact"})
    public void addContact(){
    	//WebElement el = driver.findElement(By.id("com.d3tech.smartgateway:id/joker_dialog_button_left"));
        //el.click();
        List<AndroidElement> textFieldsList = driver.findElements(By.id("com.d3tech.smartgateway:id/sk_text_gateway_name"));
        for(AndroidElement textfield:textFieldsList){
        	String temp=textfield.getText();
        	System.out.println(temp);
        	if(temp.equals("智能网关2009")){
        		textfield.click();
        		break;
        	}
        }
        	
        List<AndroidElement> textkeyList = driver.findElementsByClassName("android.widget.TextView");
		for(AndroidElement textkey:textkeyList){
		 	if(textkey.getText().equals("那把银白色的")){
		 		textkey.click();
		 		break;
		 	}
		}
  		driver.findElement(By.id("com.d3tech.smartgateway:id/sk_image_lock_main_unlock")).click();
  		driver.findElement(By.id("com.d3tech.smartgateway:id/sk_edit_lock_unlock_pwd_lockpwd")).sendKeys("1234");
  		driver.findElement(By.id("com.d3tech.smartgateway:id/sk_text_lock_unlock_pwd_confirm")).click();
  		String test=driver.findElement(By.id("com.d3tech.smartgateway:id/sk_text_lock_main_lock_enable")).getText();
  		System.out.println(test);
    }
}
        
       


