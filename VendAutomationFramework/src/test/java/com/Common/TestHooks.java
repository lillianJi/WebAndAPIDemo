package com.Common;

import com.GenericFunctions;
import com.Utils.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.Utils.SeleniumHelper.Setup;
import static com.Utils.SeleniumHelper.closeAll;


public class TestHooks {

    public static Logger log = LogManager.getLogger(TestHooks.class.getName());
    private static boolean isNewFeature = false;

    @Before()
    public void setupBefore(Scenario scenario) {
        try {
            PropertyReadHelper.setApplicationPropertiesToSystemVariables();

            // Collecting the executing test details for reporting
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
            String rawFeatureName = scenario.getId().split(";")[0].replace("-", " ").replace("service tests", "");

            String runningTestFeatureName = rawFeatureName.substring(0, 1).toUpperCase() + rawFeatureName.substring(1).trim();
            if (GenericFunctions.CurrentTestResult.getProjectName() == null || GenericFunctions.CurrentTestResult.getProjectName().equals("")) {
                LogHelper.info(log, "Starting Test Execution.");
                isNewFeature = true;
            } else if (GenericFunctions.CurrentTestResult.getProjectName().equals(runningTestFeatureName)) {
                isNewFeature = false;
            } else {
                isNewFeature = true;
            }

            if (isNewFeature) {
                GenericFunctions.CurrentTestResult = new ReportTestResult();
                GenericFunctions.CurrentTestResult.setProjectId(System.getProperty(CommonConstants.EnvironmentDetails.DETAILED_TEST_PROJECT_ID));
                GenericFunctions.CurrentTestResult.setProjectName(runningTestFeatureName);
                GenericFunctions.CurrentTestResult.setPlatform(System.getProperty(CommonConstants.EnvironmentDetails.TEST_PLATFORM).toUpperCase());
                GenericFunctions.CurrentTestResult.setEnvironment(System.getProperty(CommonConstants.EnvironmentDetails.TEST_ENVIRONMENT).toUpperCase());
                GenericFunctions.CurrentFeatureStartTime = new Date();
                GenericFunctions.CurrentTestResult.setStartTime(DateFormat.format(GenericFunctions.CurrentFeatureStartTime));
                GenericFunctions.CurrentTestResult.setTotalTestCount(1);
                GenericFunctions.CurrentTestResult.setFailTestCount(0);
                GenericFunctions.CurrentTestResult.setPassTestCount(0);
                GenericFunctions.TestResults.add(GenericFunctions.CurrentTestResult);
            } else {
                GenericFunctions.CurrentTestResult.setTotalTestCount(GenericFunctions.CurrentTestResult.getTotalTestCount() + 1);
            }

            BaseClass.currentScenario = scenario.getName();
            LogHelper.info(log, "Executing Test Scenario: " + BaseClass.currentScenario);
            GenericFunctions.FullTestCount += 1;


            // Initialize a browser sessions if test is a web test
            if (scenario.getSourceTagNames().toString().toUpperCase().contains("WEB")) {
                Setup();
            }

        } catch (Exception ex) {
        }
    }

    @After()
    public void tearDown(Scenario scenario) {
        try {
            // close w if test is a web test
            if (scenario.getSourceTagNames().toString().toUpperCase().contains("WEB")) {
                closeAll();
            }
            // Count the test results
            if (scenario.isFailed()) {
                GenericFunctions.FailTestCount += 1;
                GenericFunctions.CurrentTestResult.setFailTestCount(GenericFunctions.CurrentTestResult.getFailTestCount() + 1);
            } else {
                GenericFunctions.PassTestCount += 1;
                GenericFunctions.CurrentTestResult.setPassTestCount(GenericFunctions.CurrentTestResult.getPassTestCount() + 1);
            }
            GenericFunctions.CurrentTestResult.setRunStatus("Total: " + GenericFunctions.CurrentTestResult.getTotalTestCount() + " | Pass: " + GenericFunctions.CurrentTestResult.getPassTestCount() + " | Fail: " + GenericFunctions.CurrentTestResult.getFailTestCount());
            GenericFunctions.CurrentTestResult.setRunDuration((new Date().getTime() - GenericFunctions.CurrentFeatureStartTime.getTime()) / 1000);
            GenericFunctions.TestResults.set((GenericFunctions.TestResults.size() - 1), GenericFunctions.CurrentTestResult);

        }catch (Exception ex) {
        }

    }
}
