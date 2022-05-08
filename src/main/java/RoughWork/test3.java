package RoughWork;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class test3 {
    AppiumDriver driver;
    @Test
    public void invalidUserName(){
        MobileElement usernameFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
        MobileElement passwordFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
        MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

        usernameFld.sendKeys("invalidUserName");
        passwordFld.sendKeys("secret_sauce");
        loginBtn.click();

        MobileElement errTxt = (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");
        String actualErrTxt = errTxt.getAttribute("text");
        String expectedErrTxt = "Username and password do not match any user in this service.";
        Assert.assertEquals(actualErrTxt,expectedErrTxt);
    }
    @Test
    public void invalidPassword(){
        MobileElement usernameFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
        MobileElement passwordFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
        MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

        usernameFld.sendKeys("standard_user");
        passwordFld.sendKeys("secret_sauce12");
        loginBtn.click();

        MobileElement errTxt = (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");
        String actualErrTxt = errTxt.getText();
        System.out.println("actual err txt - " + actualErrTxt);
        String expectedErrTxt = "Username and password do not match any user in this service.";
        Assert.assertEquals(actualErrTxt,expectedErrTxt);
    }

    @Test
    public void successfulLogin(){
        MobileElement usernameFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
        MobileElement passwordFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
        MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

        usernameFld.sendKeys("standard_user");
        passwordFld.sendKeys("secret_sauce");
        loginBtn.click();

        MobileElement successTxt = (MobileElement) driver.findElementByXPath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/preceding-sibling::android.view.ViewGroup/android.widget.TextView");
        String actualProductTitle = successTxt.getAttribute("text");
        System.out.println("product title - " + actualProductTitle);
        String expectedProductTxt = "PRODUCTS";
        Assert.assertEquals(actualProductTitle,expectedProductTxt);

    }

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        DesiredCapabilities caps =  new DesiredCapabilities();
        caps.setCapability("platformName","Android" );
        caps.setCapability("deviceName","Pixel30");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("udid", "emulator-5554");
        caps.setCapability("platformVersion", "11");
        caps.setCapability("appPackage","com.swaglabsmobileapp");
        caps.setCapability("appActivity","com.swaglabsmobileapp.SplashActivity");
//        caps.setCapability("app","/Users/chriselyn/Downloads/Android.SauceLabs.Mobile.Sample.app.2.2.1.apk");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url,caps);
        String sessionId = driver.getSessionId().toString();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//        caps.setCapability("automationName",props.getProperty("androidAutomationName"));
//        caps.setCapability("appPackage",props.getProperty("androidAppPackage"));
//        caps.setCapability("appActivity",props.getProperty("androidAppActivity"));

//        URL appUrl = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
//        caps.setCapability("app",appUrl);
//        URL url = new URL(props.getProperty("appiumURL"));

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
