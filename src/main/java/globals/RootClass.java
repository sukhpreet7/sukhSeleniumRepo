package globals;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import utilities.FrameworkUtils;

public class RootClass {

    public static WebDriver driver;
    boolean driverActive = true;
    //public String appURL = "http://automationpractice.com/index.php";
    public String appURL = "https://www.ontarioimmigration.gov.on.ca/oinp_index/resources/app/guest/index.html#!/";

    public static ExtentTest extentTest;
    public static String userDir = System.getProperty("user.dir");
    public static String testReportPath = userDir + "\\target\\FinalReport.html";
    public static String screenshotsFolder = userDir + "\\target\\Screenshots\\";
    public static ExtentReports extentReports = new ExtentReports(testReportPath, true);
    FrameworkUtils frameworkUtils;
    public static String screenshotPath;

    @BeforeClass(alwaysRun = true)
    public void launchBrowser() throws InterruptedException {
        String browser = "GC";
        extentTest = extentReports.startTest("Launch Browser " + browser);
        frameworkUtils = new FrameworkUtils();
        switch (browser) {
            //GUI-mode
            case "GC":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                extentTest.log(LogStatus.INFO, "Google Chrome launched successfully.");
                break;

            case "MF":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                extentTest.log(LogStatus.INFO, "Mozilla Firefox launched successfully.");
                break;

            //Non-GUI mode
            case "headless":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
                extentTest.log(LogStatus.INFO, "Google Chrome launched successfully in Non-GUI mode.");
        }
        driver.manage().window().maximize();
        //opening the url
        driver.get(appURL);
        Thread.sleep(2000);
        frameworkUtils.addScreenshotToReport(browser);
        extentTest.log(LogStatus.INFO, screenshotPath);
        extentReports.endTest(extentTest);
        extentReports.flush();
    }

    @AfterMethod(alwaysRun = true, enabled = true)
    public void returnTestResult(ITestResult result, ITestContext context) {
        String methodName = result.getName();
        String testName = result.getTestName();

        switch (result.getStatus())
        {
            case 1:
                System.out.println("Test Passed");
                extentTest.log(LogStatus.PASS, "Test Passed");
                frameworkUtils.addScreenshotToReport(methodName);
                extentTest.log(LogStatus.INFO, screenshotPath);
                break;

            case 2:
                System.out.println("Test Failed");
                frameworkUtils.addScreenshotToReport(methodName);
                extentTest.log(LogStatus.FAIL, "Test Failed");
                extentTest.log(LogStatus.INFO, screenshotPath);
                break;

            case 3:
                frameworkUtils.addScreenshotToReport(methodName);
                extentTest.log(LogStatus.SKIP, "Test Skipped");
                extentTest.log(LogStatus.INFO, screenshotPath);
                System.out.println("Test Skipped");
        }
        extentReports.endTest(extentTest);
        extentReports.flush();
    }

    public WebDriver getBrowser() {
        return driver;
    }

    @AfterClass()
    public void closeBrowser() {
        driver.quit();
    }
}
