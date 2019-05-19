package com.Utils;

import com.Common.CommonConstants;
import com.Common.VendPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import com.GenericFunctions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

import static org.junit.Assert.fail;

public class SeleniumHelper {

    static protected VendPage vendPage;

    public static Logger log = LogManager.getLogger(SeleniumHelper.class.getName());

    private static String desiredBrowser = System.getProperty("default.browser");
    private static String desiredPlatform = System.getProperty("default.platform");
    public static WebDriver driver;

    public static WebDriver getDriver(){
        return driver;
    }

    private static String getDesiredBrowser() {
        return desiredBrowser;
    }

    private static void setDesiredBrowser(String browserName) {
        desiredBrowser = browserName;
    }

    private static String getDesiredPlatform() {
        return desiredPlatform;
    }

    private static void setDesiredPlatform(String newDesiredPlatform) {
        desiredPlatform = newDesiredPlatform;
    }

    private static void setBrowserOnFocus() {
        ((JavascriptExecutor) SeleniumHelper.driver).executeScript("window.focus();");
    }


    public static WebDriver Setup() {
        // Driver setup
        setSystemProperties();
        try{
            initializeDriver();
        }catch (Exception e){

        }
        vendPage = PageFactory.initElements(driver,VendPage.class);
        return driver;
    }

    public static WebDriver initializeDriver() {
        try {
            // Initiate Driver
            LogHelper.info(log, "Initialize the web driver.");
            String browser;
            String _browser = System.getProperty("browser");
            if (null != _browser && !_browser.isEmpty()) {
                browser = _browser;
            } else {
                browser = getDesiredBrowser();
            }

            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("useAutomationExtension", false);
                    options.addArguments("--disable-infobars");
                    HashMap<String, Object> chromePrefs = new HashMap<>();
                    chromePrefs.put("profile.default_content_settings.popups", 0);
                    chromePrefs.put("download.default_directory", System.getProperty(CommonConstants.SeleniumSettings.DOWNLOAD_LOCATION));
                    options.setExperimentalOption("prefs", chromePrefs);
                    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    SeleniumHelper.driver = new ChromeDriver(options);
                    break;
                case "edge":
                    if (System.getProperty("os.name").contains("Windows 10")) {
                        SeleniumHelper.driver = new EdgeDriver();
                    } else {
                        fail("Edge not supported on this OS");
                    }
                    break;
                case "ie":
                    if (System.getProperty("os.name").contains("Win")) {
                        DesiredCapabilities cap = new DesiredCapabilities();
                        cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                        cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                        SeleniumHelper.driver = new InternetExplorerDriver(cap);
                    } else {
                        fail("ie is only supported on windows");
                    }
                    break;
                case "firefox":
                    File profileDirectory = new File(System.getProperty(CommonConstants.SeleniumSettings.PROFILE_LOCATION));
                    FirefoxProfile firefoxProfile = new FirefoxProfile(profileDirectory);
                    firefoxProfile.setPreference("browser.download.dir",
                            System.getProperty(CommonConstants.SeleniumSettings.DOWNLOAD_LOCATION));
                    firefoxProfile.setPreference("browser.download.folderList", 2);
                    firefoxProfile.setPreference("pdfjs.disabled", true);
                    firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
                    firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                            "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    firefoxProfile.setPreference("browser.helperApps.neverAsk.openFile",
                            "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    DesiredCapabilities ffCapabilities = DesiredCapabilities.firefox();
                    ffCapabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
                    SeleniumHelper.driver = new FirefoxDriver(ffCapabilities);
                    break;
                case "safari":
                    SafariOptions safariOptions = new SafariOptions();
                    safariOptions.setUseTechnologyPreview(true);
                    SeleniumHelper.driver = new SafariDriver(safariOptions);
                    break;
                default:
                    fail("Unknown browser");
            }
            driver.manage().window().maximize();
            setBrowserOnFocus();
            return SeleniumHelper.driver;
        } catch (Exception e) {
            LogHelper.error(log, e.getMessage());
            Assert.fail("Initializing driver failed. No point of continuing test. Message: " + e.getMessage());
            return null;
        }
    }

    public static void closeAll() {
        if (SeleniumHelper.driver != null)
            SeleniumHelper.driver.manage().deleteAllCookies();
            SeleniumHelper.driver.close();
    }

    private static void setSystemProperties() {
        String path = System.getProperty("user.dir") + File.separator + "Drivers" + File.separator;
        String platform;
        String _platform = getDesiredPlatform();
        assert _platform != null;
        platform = _platform;

        if (platform.toLowerCase().contains("win")) {
            System.setProperty("webdriver.chrome.driver", path + "win" + File.separator + "chromedriver.exe");
            System.setProperty("webdriver.ie.driver", path + "win" + File.separator + "IEDriverServer.exe");
            System.setProperty("webdriver.firefox.marionette", path + "win" + File.separator + "geckodriver.exe");
        } else if (platform.toLowerCase().contains("mac")) {
            System.setProperty("webdriver.chrome.driver", path + "mac" + File.separator + "chromedriver");
            System.setProperty("webdriver.gecko.driver", path + "mac" + File.separator + "geckodriver");
        } else {
            System.setProperty("webdriver.chrome.driver", path + "linux" + File.separator + "chromedriver");
            System.setProperty("webdriver.gecko.driver", path + "linux" + File.separator + "geckodriver");
        }
    }

    public static void waitForElementVisibility (WebElement element){
        WebDriverWait wait = new WebDriverWait(getDriver(),30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public static void GoToUrl(String url) {
        driver.navigate().to(url);
    }



}