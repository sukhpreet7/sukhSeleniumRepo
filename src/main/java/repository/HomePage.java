package repository;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {


    WebDriver driver;

    //Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //protected

    @FindBy(how = How.CLASS_NAME, using = "logout")
    private WebElement logoutBtn;


    public boolean isSignOutBtnDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            wait.until(ExpectedConditions.visibilityOf(logoutBtn));
            boolean isLogoutBtnDisplayed = logoutBtn.isDisplayed();
            return true;
        }
        catch (TimeoutException e)
        {
            return false;
        }
    }

    public void clickSignOutBtn()
    {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(logoutBtn));
        logoutBtn.click();
    }

}

