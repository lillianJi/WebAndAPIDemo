package com.Common;

import com.GenericFunctions;
import com.Utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.Utils.RestApiHelper.SetDefaultHeader;
import static org.apache.cxf.helpers.IOUtils.UTF8_CHARSET;


public class BaseClass extends GenericFunctions {

    public static String currentScenario = "";
    public static HttpResponse response = null;
    public static int responseCode = 0;
    public static String responseMessage = "";


    //get test environment from configuration file
    public static String getTestEnvironment(String env) {
        String testEnvironment = "";

        if (env.equalsIgnoreCase(CommonConstants.EnvironmentDetails.SIT_ENVIRONMENT_PREFIX)) {
            testEnvironment = CommonConstants.EnvironmentDetails.SIT_ENVIRONMENT_PREFIX;
        } else if (env.equalsIgnoreCase(CommonConstants.EnvironmentDetails.PRELIVE_ENVIRONMENT_PREFIX)) {
            testEnvironment = CommonConstants.EnvironmentDetails.PRELIVE_ENVIRONMENT_PREFIX;
        } else if (env.equalsIgnoreCase(CommonConstants.EnvironmentDetails.PRODUCTION_ENVIRONMENT_PREFIX)) {
            testEnvironment = CommonConstants.EnvironmentDetails.PRODUCTION_ENVIRONMENT_PREFIX;
        }
        return testEnvironment;
    }

    //get service base URL
    public static String getBaseURL(String service) {
        String testEnvironment = getTestEnvironment(System.getProperty(CommonConstants.EnvironmentDetails.TEST_ENVIRONMENT));
        String URL = "";
        Properties configurationProperties = PropertyReadHelper.setConfigurationPropertiesFile(testEnvironment);
        URL = configurationProperties.getProperty(service);
        return URL;
    }

    //find test data using datafile and parameters
    public static String getTestData(Properties propertiesFile, String testData) {
        String testEnvironment = getTestEnvironment(System.getProperty(CommonConstants.EnvironmentDetails.TEST_ENVIRONMENT));
        return propertiesFile.getProperty(testEnvironment.toLowerCase() + "." + testData);
    }



}