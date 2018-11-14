package shoppingTest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(xpath = "//*[@id=\"email\"]")
    private WebElement login;

    @FindBy(xpath = "//*[@id=\"passwd\"]")
    private WebElement pass;

    public WebElement getPass() {
        return pass;
    }
    public void clickButtonPass(){
        pass.click();
    }

    public WebElement getLogin() {
        return login;
    }

    public void sendKeysToLogin(String loginS){
        login.sendKeys(loginS);
    }
}
