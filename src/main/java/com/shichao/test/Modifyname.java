package com.shichao.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class Modifyname {

	public Modifyname() {//构造方法
		// TODO Auto-generated constructor stub
	}
	
	public   void Testsha() {//方法
		try {
			AndroidDriver  driver;
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "apps");
			File app = new File(appDir, "smartgateway.apk");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("deviceName","SCL-TL00H");
			capabilities.setCapability("platformVersion", "5.1.1");
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage", "com.d3tech.smartgateway");
			capabilities.setCapability("appActivity", "com.d3tech.smartgateway.activity.WelcomeActivity");
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			try {
				List<WebElement> buttonList = driver.findElementsByClassName("android.widget.ImageButton");
				buttonList.get(0).click();
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				driver.quit();
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
