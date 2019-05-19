package com.StepDefinitions;

import com.Common.BaseClass;
import com.Utils.LogHelper;
import com.Utils.VerificationHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;

import static java.lang.Thread.sleep;


public class CommonSteps extends BaseClass {
    private static Logger log = LogManager.getLogger(CommonSteps.class.getName());


    @Then("^I should get API response with the Status code of \"([^\"]*)\"$")
    public static void verifyAPIResponse(String ExpectedCode) {
        VerificationHelper.EqualsVerification("Response Code Verification", String.valueOf(Integer.parseInt(ExpectedCode)), String.valueOf(responseCode));
    }


    @Then("^I should get API response with the Status code \"([^\"]*)\" with the error message of \"([^\"]*)\"$")
    public static void iShouldGetAPIResponseWithTheStatusCodeWithTheErrorMessageOf(String ExpectedCode, String ExpectedMessage) {
        verifyAPIResponse(ExpectedCode);

        if (!(responseCode == 200 || responseCode == 202 || responseCode == 201)) {
            VerificationHelper.ContainsVerification("Response message Verification", String.valueOf(ExpectedMessage), String.valueOf(responseMessage));
        }
    }


}
