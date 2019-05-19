package com;

import com.Common.CommonConstants;
import com.Common.ReportTestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GenericFunctions {

    private static WebDriver driver;

    public static Logger log = LogManager.getLogger(GenericFunctions.class.getName());

    public static int defaultSleepSeconds = Integer.parseInt(CommonConstants.ExecutionSettings.DEFAULT_SLEEP_SECONDS);
    public static int defaultTimeOutSeconds = Integer.parseInt(CommonConstants.ExecutionSettings.DEFAULT_TIMEOUT_SECONDS);
    public static int defaultTokenExpirySeconds = Integer.parseInt(CommonConstants.ExecutionSettings.DEFAULT_TOKEN_EXPIRY_SECONDS);

    public static int FullTestCount = 0;
    public static int PassTestCount = 0;
    public static int FailTestCount = 0;
    public static Date TestRunStartTime = null;
    public static Date CurrentFeatureStartTime;

    public static List<ReportTestResult> TestResults = new ArrayList<>();
    public static ReportTestResult CurrentTestResult = new ReportTestResult();



}
