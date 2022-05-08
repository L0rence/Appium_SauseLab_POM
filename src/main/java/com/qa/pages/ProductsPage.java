package com.qa.pages;

import com.qa.baseTest.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends BaseTest {

    //Locators
    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/preceding-sibling::android.view.ViewGroup/android.widget.TextView")
    private MobileElement productTitleTxt;

    //Action Methods
    public String getProductTitle(){
        return getAttribute(productTitleTxt,"text");

    }
   }
