package repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PracticeAlertPage {

    WebDriver driver;


    //Constructor
    public PracticeAlertPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.NAME, using = "alert")
    private WebElement alertButton;

    @FindBy(how = How.NAME, using = "confirmation")
    private WebElement confirmationBtn;

    public void clickAlertButton() {
        WebDriverWait waitForBtn = new WebDriverWait(driver, 60);
        waitForBtn.until(ExpectedConditions.elementToBeClickable(alertButton));
        alertButton.click();
    }

    public void clickConfirmationButton() {
        WebDriverWait waitForBtn = new WebDriverWait(driver, 60);
        waitForBtn.until(ExpectedConditions.elementToBeClickable(confirmationBtn));
        confirmationBtn.click();
    }
}
