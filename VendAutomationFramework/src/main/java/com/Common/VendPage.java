package com.Common;

import com.Utils.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class VendPage extends SeleniumHelper {

    WebDriver driver;

    @FindBy(id="signin_username")
    protected WebElement userName;

    @FindBy(id="signin_password")
    protected WebElement userPassword;

    @FindBy(name="signin_submit")
    protected WebElement loginButton;

    @FindBy(linkText="Products")
    protected WebElement productMenu;


    @FindBy(className="vd-button vd-button--primary")
    protected WebElement addProduct;

    public VendPage(WebDriver driver){
        this.driver = driver;
    }

    public void doSignIn(String _userName, String _password){

        userName.sendKeys(_userName);
        userPassword.sendKeys(_password);
        loginButton.click();

    }

    public void goToProductMenu(){

        waitForElementVisibility(productMenu);
        productMenu.click();

    }

    public Boolean verifyAddProduct(){

        return !addProduct.isDisplayed();

    }





}
