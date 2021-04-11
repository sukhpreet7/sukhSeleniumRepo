package globals;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class TestFirstClass {
    private static WebDriver driver;


    @BeforeClass(alwaysRun = true)
    public void launchBrowser()
    {
        String browser = "GC";
        switch (browser)
        {
            //GUI-mode
            case "GC":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "MF":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();

            //phantom-js dependency in pom.xml


                //Non-GUI mode
            case "headless":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        //opening the url
        driver.get("http://automationpractice.com/index.php");
        // driver.findElement(By.className("login")).click();
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
