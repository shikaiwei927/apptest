

package com.shichao.test;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverPropertyInfo;
import java.sql.SQLNonTransientConnectionException;
import java.text.SimpleDateFormat;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;




import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
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

public class Upgrade {

        static int upgradeoknum=0;
        static int upgradefailnum=0;
        public static void testtask(){
                try {
                    boolean flag=false;
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
                    
                    //找到能进入更多功能按钮
                    try {
                    List<WebElement> buttonList = driver.findElementsByClassName("android.widget.ImageButton");
                    buttonList.get(0).click();
                    
                    //点击固件升级
                    List<WebElement> textkeyLists = driver.findElementsByClassName("android.widget.TextView");
                        for(WebElement textkey:textkeyLists){
                             if(textkey.getText().equals("固件升级")){
                                 textkey.click();
                                 break;
                             }
                        }
                      //点击确定
                        List<WebElement> list = driver.findElementsByClassName("android.widget.TextView");
                        for(WebElement textkey:list){
                             if(textkey.getText().equals("确定")){
                                textkey.click();
                                 System.out.println("ss");
                                 SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                 String TimeString = time.format(new java.util.Date());
                                
                                 break;
                             }
                        }   


                    }catch (Exception e) {

                    e.printStackTrace();

                    }
                    finally {
                        driver.quit();

                    }
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }


            public static void main(String args[]){
                try {
                    int i=0;
                    while(true){
                        i++;
                        testtask();
                        Thread.sleep(280000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
    }
