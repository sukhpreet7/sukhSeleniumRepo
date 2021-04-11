package repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {

    WebDriver driver;

    //Constructor
    public LandingPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //protected

    @FindBy(how = How.CLASS_NAME, using = "login")
    private WebElement signInBtn;

    @FindBy(how = How.ID, using = "email_create")
    private WebElement email;

    @FindBy(how = How.ID, using = "SubmitCreate")
    private WebElement createAnAccountBtn;

    @FindBy(how = How.CSS , using = "#submitAccount>span")
    private WebElement registerBtn;

    public void enterEmail()
    {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(email));
        email.sendKeys("test1235@gmail.com");
    }

    public void clickCreateAcctBtn() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(createAnAccountBtn));
        createAnAccountBtn.click();

    }

    public void clickSignInBtn() {
        //selenium waits // implicit
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(signInBtn));
        signInBtn.click();
    }

    public String getRegisterBtnTxt() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(registerBtn));
        String actualText = registerBtn.getText();
        return actualText;
    }
}
