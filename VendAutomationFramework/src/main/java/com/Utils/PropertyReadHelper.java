package com.Utils;

import com.Common.CommonConstants;
import com.GenericFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;


public class PropertyReadHelper {

    public static Properties props = null;

    public static Properties setConfigurationPropertiesFile(String environment) {
        props = new Properties();

        // Retrieve data from file
        try {
            props.load(new FileInputStream(CommonConstants.FileLocations.PROJECT_PROPERTIES_LOCATION + environment + File.separator + "config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static Properties setPropertiesFile(String propFileName) {
        props = new Properties();

        // Retrieve data from file
        try {
            props.load(new FileInputStream(CommonConstants.FileLocations.PROJECT_PROPERTIES_LOCATION + propFileName + ".properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static void setApplicationPropertiesToSystemVariables() {
        if (System.getProperty("test.environment") == null || System.getProperty("test.environment") == "") {
            Properties TestProperties = getAppProperties();
            if (TestProperties == null) {
                Properties VendProperties = setPropertiesFile(CommonConstants.PropertyFiles.PROPERTIES_FILE_VEND);
                Properties EnvironmentProperties = setConfigurationPropertiesFile(VendProperties.getProperty("test.environment"));
                TestProperties = new Properties();
                TestProperties.putAll(VendProperties);
                TestProperties.putAll(EnvironmentProperties);
            }
            for (String name : TestProperties.stringPropertyNames()) {
                String value = TestProperties.getProperty(name);
                System.setProperty(name, value);
            }
        }

        if (GenericFunctions.TestRunStartTime == null) {
            GenericFunctions.TestRunStartTime = new Date();
        }
    }

    public static Properties getAppProperties() {
        props = new Properties();

        // Retrieve data from file
        try {
            props.load(new FileInputStream(System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes" + File.separator + "app.properties"));
        } catch (IOException e) {
            return null;
        }
        return props;
    }

    public static Properties setTestDataFile(String testDataFileName) {
        props = new Properties();

        // Retrieve data from file
        try {
            props.load(new FileInputStream(CommonConstants.FileLocations.PROJECT_TEST_DATA_LOCATION + testDataFileName + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
