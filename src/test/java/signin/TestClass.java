package signin;

import globals.RootClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import repository.HomePage;
import repository.LandingPage;
import repository.PracticeAlertPage;
import repository.SignInPage;
import utilities.ExcelUtility;

public class TestClass extends RootClass {


    RootClass rootClass;
    LandingPage landingPage;
    SignInPage signInPage;
    HomePage homePage;

    PracticeAlertPage practiceAlertPage;
    WebDriver driver;


    @BeforeClass(alwaysRun = true)
    public void openURL()
    {
        driver = getBrowser();
    }

    @DataProvider(name = "loginTestData")
    public Object[][] getLoginData()
    {
        Object[][] testDataObjects = null;
        String dataFilePath = userDir + "/src/main/resources/credentials.xlsx";
        ExcelUtility excelUtility = new ExcelUtility();
        try {
            testDataObjects = excelUtility.getTestData(dataFilePath, "logindata");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return testDataObjects;
    }

    @Test(dataProvider = "loginTestData", enabled = true, groups = {"signIn"})
    public void testLogin(String username, String password) throws InterruptedException {
        landingPage = new LandingPage(driver);
        homePage = new HomePage(driver);
        signInPage = new SignInPage(driver);
        Thread.sleep(2000);
        extentTest = extentReports.startTest("Verify login functionality", "Launch the application > Click sign btn " +
                "> enter user name > enter password > click sign");
        landingPage.clickSignInBtn();
        signInPage.enterEmail(username);
        signInPage.enterPassword(password);
        signInPage.clickSignInBtn();
        boolean isBtnDisplayed = homePage.isSignOutBtnDisplayed();
        SoftAssert softAssert = new SoftAssert();
        if (isBtnDisplayed) {
            softAssert.assertTrue(homePage.isSignOutBtnDisplayed());
            homePage.clickSignOutBtn();
        } else if (!username.contains("@")) {
            softAssert.assertEquals(signInPage.getMsg(), signInPage.INVALID_EMAIL_MSG);
        } else if (username.contains("@")) {
            if (password.length() < 5) {
                softAssert.assertEquals(signInPage.getMsg(), signInPage.INVALID_PASSWD_SHORT);
            } else {
                softAssert.assertEquals(signInPage.getMsg(), signInPage.INVALID_PASSWD_MSG);
            }
        }
        softAssert.assertAll();
    }

    //before
    //@Test(alwaysRun = false, enabled = false, groups = {"regression"})
    public void testSignInPageRedirection() throws InterruptedException {
        landingPage = new LandingPage(driver);
        Thread.sleep(2000);
        landingPage.clickSignInBtn();
        landingPage.enterEmail();
        landingPage.clickCreateAcctBtn();
        Assert.assertEquals(landingPage.getRegisterBtnTxt(), "Register");
    }

   // @Test(alwaysRun = true, enabled = false, groups = "signIn")
    public void checkAlert() {
        driver.get("https://chercher.tech/practice/practice-pop-ups-selenium-webdriver");
        practiceAlertPage = new PracticeAlertPage(driver);
        practiceAlertPage.clickAlertButton();
        driver.switchTo().alert().accept();
        practiceAlertPage.clickConfirmationButton();
        driver.switchTo().alert().dismiss();
        driver.switchTo().window("");
        //windows or tabs
        //file upload, file download
    }

   // @AfterMethod(alwaysRun = true)
    public void openApp()
    {
        driver.get(appURL);
    }

    @AfterClass(alwaysRun = true)
    public void quitBrowser()
    {
        driver.quit();
    }
}
