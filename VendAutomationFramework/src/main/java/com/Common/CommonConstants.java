package com.Common;

import java.io.File;


public class CommonConstants {

    // Property Files
    public static class PropertyFiles {
        public static String PROPERTIES_FILE_VEND = "vend";
    }

    // Environment Related properties
    public static class EnvironmentDetails {
        public static String TEST_ENVIRONMENT = "test.environment";
        public static String TEST_PLATFORM = "test.platform";
        public static String DETAILED_TEST_PROJECT_ID = "detailed.test.project.id";
        public static String SIT_ENVIRONMENT_PREFIX = "sit";
        public static String PRELIVE_ENVIRONMENT_PREFIX = "prelive";
        public static String PRODUCTION_ENVIRONMENT_PREFIX = "prod";
    }


    // File locations
    public static class FileLocations {
        public static String PROJECT_LOG_LOCATION = System.getProperty("user.dir") + File.separator + "Logs" + File.separator;
        public static String PROJECT_PROPERTIES_LOCATION = System.getProperty("user.dir") + File.separator + "Data" + File.separator + "Properties" + File.separator;
        public static String PROJECT_TEST_DATA_LOCATION = System.getProperty("user.dir") + File.separator + "Data" + File.separator + "TestData" + File.separator;
    }

    // Test Execution Settings
    public static class ExecutionSettings {
        public static String DEFAULT_SLEEP_SECONDS = System.getProperty("default.sleep.seconds");
        public static String DEFAULT_TIMEOUT_SECONDS = System.getProperty("default.timeout.seconds");
        public static String DEFAULT_TOKEN_EXPIRY_SECONDS = System.getProperty("default.token.expiry.seconds");
    }

    // Selenium Settings
    public static class SeleniumSettings {
        public static String DOWNLOAD_LOCATION = "project.download.location";
        public static String PROFILE_LOCATION = "project.profile.location";
    }
}