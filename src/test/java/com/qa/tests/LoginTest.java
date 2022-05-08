package com.qa.tests;

import com.qa.baseTest.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    ProductsPage productsPage;
    InputStream datais;
    JSONObject loginUsers;

    @BeforeClass
    public void beforeClass() throws Exception {
        //Load String data from Json
        try {
            String dataFileName = "data/loginUsers.json";
            datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener tokener = new JSONTokener(datais);
            loginUsers = new JSONObject(tokener);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (datais != null) {
                datais.close();
            }
        }
    }

    @AfterClass
    public void afterClass() {
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        loginPage = new LoginPage(); // Print the method name
        System.out.println("\n" + "************** Starting Test:" + m.getName() + "***************" + "\n");
    }

    @AfterMethod
    public void afterMethod() {
    }

    @Test
    public void invalidUserName() {

        loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
        loginPage.ClickLoginBtn();

        String actualErrTxt = loginPage.getErrText();
        String expectedErrTxt = strings.get("err_invalid_username_or_password");
        System.out.println("actual err txt - " + actualErrTxt + "\n" + "expected err txt - " + expectedErrTxt);
        Assert.assertEquals(actualErrTxt, expectedErrTxt);
    }

    @Test
    public void invalidPassword() {
        loginPage.enterUserName(loginUsers.getJSONObject("invalidPassword").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
        loginPage.ClickLoginBtn();

        String actualErrTxt = loginPage.getErrText();
        String expectedErrTxt = strings.get("err_invalid_username_or_password");
        System.out.println("actual err txt - " + actualErrTxt + "\n" + "expected err txt - " + expectedErrTxt);
        Assert.assertEquals(actualErrTxt, expectedErrTxt);
    }

    @Test
    public void successfulLogin() {
        loginPage.enterUserName(loginUsers.getJSONObject("validCredentials").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("validCredentials").getString("password"));
        productsPage = loginPage.ClickLoginBtn();

        String actualProductTitle = productsPage.getProductTitle();
        String expectedProductTxt = strings.get("product_title");
        System.out.println("product title - " + actualProductTitle + "\n" + "expected err txt - " + expectedProductTxt);
        Assert.assertEquals(actualProductTitle, expectedProductTxt);

    }

}
