package com.StepDefinitions;

import com.Utils.SeleniumHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class WebSteps extends SeleniumHelper {


    @Then("^I login Vend \"([^\"]*)\" as manager use \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iLoginVendAsManagerUseAnd(String vendurl, String userName, String password) throws Exception {
        //navigate to URL and do sign in
        GoToUrl(vendurl);
        vendPage.doSignIn(userName,password);
        Thread.sleep(10000);
    }

    @Then("^I go to product page$")
    public void iGoToProductPage() throws Exception {

        vendPage.goToProductMenu();
        Thread.sleep(5000);

    }

    @And("^I should not be able to add product$")
    public boolean iShouldNotBeAbleToAddProduct() {

        try {
            SeleniumHelper.driver.findElement(By.linkText("Add Product"));
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

}
