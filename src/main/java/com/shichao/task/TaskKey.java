package com.shichao.task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
 


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
 
public class TaskKey {
    private  AndroidDriver  driver;
 
    @BeforeMethod(alwaysRun=true)
    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "app-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        //capabilities.setCapability("deviceName","Mi-4c");
        capabilities.setCapability("deviceName","SCL-TL00H");
        capabilities.setCapability("platformVersion", "5.1");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.d3tech.smartgateway");
        capabilities.setCapability("appActivity", "com.d3tech.smartgateway.activity.WelcomeActivity");
    	driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
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
        List<WebElement> textFieldsList = driver.findElements(By.id("com.d3tech.smartgateway:id/sk_text_gateway_name"));
        for(WebElement textfield:textFieldsList){
        	String temp=textfield.getText();
        	System.out.println(temp);
        	if(temp.equals("��������2009")){
        		textfield.click();
        		break;
        	}
        }
        	
        List<WebElement> textkeyList = driver.findElementsByClassName("android.widget.TextView");
		for(WebElement textkey:textkeyList){
		 	if(textkey.getText().equals("�ǰ�����ɫ��")){
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
        
       


