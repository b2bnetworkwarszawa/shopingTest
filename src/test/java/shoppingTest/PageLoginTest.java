package shoppingTest;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import shoppingTest.pages.LoginPage;

public class PageLoginTest {

    private WebDriver driver;
    private LoginPage loginPage;//null

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "resource/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    @Test
    public void loginTest() {
        loginPage.sendKeysToLogin("b2b");
        loginPage.clickButtonPass();
    }
}
