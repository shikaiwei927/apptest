package com.shikai.task;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
public class Demo2 {
		static int upgradeOknum=0;
		static int upgradefailnum=0;
		private AndroidDriver<AndroidElement>  driver;
		@BeforeClass
		public  void setUp() throws Exception {
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "app");
			File app = new File(appDir, "smartgateway.apk");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability(MobileCapabilityType.UDID, "JTJ4C15A15012272");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"SCL-TLOOH");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			capabilities.setCapability("unicodeKeyboard",true);
			capabilities.setCapability("resetKeyboard", true);
		    capabilities.setCapability("norest", true);
		    driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		@AfterClass(alwaysRun=true)
		public void tearDown() throws Exception{
			driver.quit();
		}
		public void upgrade() throws Exception{
					
			driver.findElementsByClassName("android.widget.ImageButton").get(0).click(); //找到能进入更多功能按钮
			List<AndroidElement> textkeyLists = driver.findElementsByClassName("android.widget.TextView");//点击固件升级
				for(AndroidElement textkey:textkeyLists){
				 	if(textkey.getText().equals("固件升级")){
				 		textkey.click();
				 		break;
				 	}
				}
			List<AndroidElement> list = driver.findElementsByClassName("android.widget.TextView");//点击确定
			for(AndroidElement textkey:list){
			 	if(textkey.getText().equals("立即更新")){
			 		textkey.click();
//						 		System.out.println("ss");
//						 		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//						 		String TimeString = time.format(new java.util.Date());
//								System.out.println(TimeString);
			 		break;
			 	}
			}
	    }
		@Test
		public  void upgradenumber() throws Exception{
			try {
				int i=0;
				while(true){
					i++;
					System.out.println("第"+i+"upgrade");
					SimpleDateFormat time=new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                    String TimeString = time.format(new java.util.Date());
                    System. out.println(TimeString );
					upgrade();
					Thread.sleep(200000);
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
		
			
					


