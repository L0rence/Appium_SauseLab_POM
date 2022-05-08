package com.qa.baseTest;

import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class BaseTest
{

    protected static AppiumDriver driver;
    protected static Properties props;
    protected static HashMap<String,String> strings = new HashMap<String, String>();
    InputStream inputStream;
    InputStream stringis;
    TestUtils testUtils;

    public BaseTest(){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    @Parameters({"platformName","platformVersion","deviceName"})

    @BeforeTest
    public void beforeTest(String platformName,String platformVersion,String deviceName) throws IOException {
        try {
            props = new Properties();
            String propFileName = "config.properties";
            String xmlFileName = "string/string.xml";

            inputStream =getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);

            stringis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
            testUtils= new TestUtils();
            strings =testUtils.parseStringXML(stringis);
            DesiredCapabilities caps =  new DesiredCapabilities();

            caps.setCapability("platformName", platformName);
            caps.setCapability("deviceName","pixel30");
            caps.setCapability("automationName",props.getProperty("androidAutomationName"));
            caps.setCapability("udid", "emulator-5554");
            caps.setCapability("platformVersion", platformVersion);
            caps.setCapability("appPackage",props.getProperty("androidAppPackage"));
            caps.setCapability("appActivity",props.getProperty("androidAppActivity"));
//            URL appUrl = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));

            String appUrl =getClass().getResource(props.getProperty("androidAppLocation")).getFile(); System.out.println("app ur is : "+ appUrl);
            caps.setCapability("app",appUrl);
            URL url = new URL(props.getProperty("appiumURL"));
            driver = new AndroidDriver(url,caps);
        }
        catch(Exception e){
            e.printStackTrace();
        }finally {
            if(inputStream !=null){
                inputStream.close();
            }
            if(stringis !=null){
                stringis.close();
            }
        }
    }

    public void waitForVisibility(MobileElement e){
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(e));
    }
    public void click(MobileElement e){
        waitForVisibility(e);
        e.click();
    }
    public void sendKeys(MobileElement e,String txt){
        waitForVisibility(e);
        e.sendKeys(txt);
    }
    public String getAttribute(MobileElement e, String attribute){
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }
    @AfterTest
    public void afterTest() {
        driver.quit();
    }


}




