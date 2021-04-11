package repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

    WebDriver driver;

    //Constructor
    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static final String INVALID_EMAIL_MSG = "Invalid email address.";
    public static final String INVALID_PASSWD_SHORT = "Invalid password.";
    public static final String INVALID_PASSWD_MSG = "Authentication failed.";

    //protected

    @FindBy(how = How.ID, using = "email")
    private WebElement email;

    @FindBy(how = How.ID, using = "passwd")
    private WebElement password;

    @FindBy(how = How.ID, using = "SubmitLogin")
    private WebElement signInBtn;

    @FindBy(how = How.XPATH, using = "//*[@id='center_column']/div[1]/ol/li")
    private WebElement errorMsg;

    public void enterEmail(String e) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(email));
        email.clear();
        email.sendKeys(e);
    }

    public void enterPassword(String p)
    {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(password));
        password.clear();
        password.sendKeys(p);
    }

    public void clickSignInBtn() {
        //
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(signInBtn));
        signInBtn.click();
    }

    public String getMsg()
    {
        String msg = errorMsg.getText();
        return msg;
    }

}


