package com.qa.pages;

import com.qa.baseTest.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {
    //Locators
    @AndroidFindBy(accessibility = "test-Username") private MobileElement usernameFld;
    @AndroidFindBy(accessibility = "test-Password") private MobileElement passwordFld;
    @AndroidFindBy(accessibility = "test-LOGIN") private MobileElement loginBtn;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
     private MobileElement errTxt;

    //Action Methods
    public LoginPage enterUserName(String username){
        sendKeys(usernameFld,username);
        return this;
    }
    public LoginPage enterPassword(String password){
        sendKeys(passwordFld,password);
        return this;
    }
    public String getErrText(){
        return getAttribute(errTxt,"text");
    }

    public ProductsPage ClickLoginBtn(){
        click(loginBtn);
        return new ProductsPage();
    }



}
