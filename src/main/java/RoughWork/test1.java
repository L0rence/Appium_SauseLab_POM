package RoughWork;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class test1

{
        AppiumDriver driver;


@Test
public void invalidUser()
        {
        MobileElement usernameTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
        MobileElement passwordTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
        MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");
        MobileElement errTxt = (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");


        usernameTxtFld.sendKeys("invalidusername");
        passwordTxtFld.sendKeys("secret_sauce");
        loginBtn.click();

        String actualErrTxt = errTxt.getAttribute("text");
        System.out.println("Acutual error txt - " +actualErrTxt );
        String expectedErrTxt= "Username and password do not match any user in this service.";

        Assert.assertEquals(actualErrTxt,expectedErrTxt);
        }
@Test
public void invalidPassword()
        {
        MobileElement usernameTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
        MobileElement passwordTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
        MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");
        MobileElement errTxt = (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");


        usernameTxtFld.sendKeys("standard_user");
        passwordTxtFld.sendKeys("invalidpass");
        loginBtn.click();

        String actualErrTxt = errTxt.getAttribute("text");
        System.out.println("Acutual error txt - " +actualErrTxt );
        String expectedErrTxt= "Username and password do not match any user in this service.";

        Assert.assertEquals(actualErrTxt,expectedErrTxt);
        }
@Test
public void successfulLogin()
        {

        MobileElement usernameTxtFld1 =   (MobileElement) driver.findElement(By.id("test-Username"));
//        MobileElement usernameTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
        MobileElement passwordTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
        MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");
        MobileElement errTxt = (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");
        MobileElement productTitleTxt = (MobileElement) driver.findElementByXPath("//*[@text='PRODUCTS']");
//        driver.findElement(By.xpath("//*[@text='Female']")).click();
        usernameTxtFld1.sendKeys("standard_user");
        passwordTxtFld.sendKeys("secret_sauce");
        loginBtn.click();

        String actualProductTitle = productTitleTxt.getAttribute("text");
        System.out.println("Acutual product title - " +actualProductTitle );
        String expectedProductTitle= "PRODUCTS";

        Assert.assertEquals(actualProductTitle,expectedProductTitle);
        }

@BeforeClass
public void beforeClass() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps =  new DesiredCapabilities();

        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName","OPPO A75");
        caps.setCapability("automationName","UiAutomator2");
// 		caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        caps.setCapability("udid", "KZ7DRCAYY5NBNZ9L");
// 		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        caps.setCapability("platformVersion", "7.1.1");
// 		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
        caps.setCapability("appPackage","com.swaglabsmobileapp");
        caps.setCapability("appActivity","com.swaglabsmobileapp.SplashActivity");
//        caps.setCapability("app","/Users/chriselyn/Downloads/appium-pageobjectmodel-master/src/test/resources/app/Android.SauceLabs.Mobile.Sample.app.2.2.1.apk");


        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(url,caps);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
//        Thread.sleep(10000);

        }

@AfterClass
public void afterClass()
        {

        }
        }



