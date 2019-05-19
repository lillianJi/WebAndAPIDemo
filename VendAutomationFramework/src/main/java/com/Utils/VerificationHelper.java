package com.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;


public class VerificationHelper {

    private static Logger log = LogManager.getLogger(VerificationHelper.class.getName());

    public static void EqualsVerification(String verificationName, String expectedValue, String actualValue) {
        boolean verificationStatus = (expectedValue.equals(actualValue));
        Assert.assertEquals(verificationName, expectedValue, actualValue);
    }

    public static void EqualsVerification(String verificationName, Boolean expectedBoolValue, Boolean actualBoolValue) {
        String expectedValue, actualValue;
        expectedValue = expectedBoolValue == null ? "null" : expectedBoolValue.toString();
        actualValue = actualBoolValue == null ? "null" : actualBoolValue.toString();
        EqualsVerification(verificationName, expectedValue, actualValue);
    }

    public static void NotEqualsVerification(String verificationName, String expectedValue, String actualValue) {
        boolean verificationStatus = (!expectedValue.equals(actualValue) ? true : false);
        Assert.assertNotEquals(verificationName, expectedValue, actualValue);
    }


    public static void ContainsVerification(String verificationName, String expectedValue, String actualValue) {
        boolean verificationStatus = (expectedValue.contains(actualValue) | actualValue.contains(expectedValue));
        Assert.assertTrue(verificationName, (expectedValue.contains(actualValue) | actualValue.contains(expectedValue)));
    }

    public static void ContainsMessageVerification(String verificationName, String expectedValue, String actualValue) {
        Assert.assertTrue(verificationName, actualValue.contains(expectedValue));
    }

}
