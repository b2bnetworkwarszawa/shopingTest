package shoppingTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PodsumowanieZakupowTest {
	private WebDriver driver;

	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver", "resource/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php?");
	}

	@Test
	public void poZalogowaniuSieIWybraniuSekcjiDressesIObszaruCasualDodaniePierwszegoElementuDoKoszyka()
			throws InterruptedException {
		ZakupyUtil.logowanie(driver);
		for (int i = 1; i < 3; i++) {
			ZakupyUtil.przekierowanieNaWomen(driver);
			ZakupyUtil.clickSizeSWSekcjiWomen(driver);
			ZakupyUtil.waitForAjax(driver);
			ZakupyUtil.clickObrazek(driver, i);
			ZakupyUtil.waitForAjax(driver);
			ZakupyUtil.clickProceedToCheckout(driver);
			ZakupyUtil.waitForAjax(driver);
			ZakupyUtil.przekierowanieDoKoszyka(driver);
		}
		double cenaProduktu = ZakupyUtil.policzSumeProduktowWKoszyku(driver);
		double total = ZakupyUtil.totalSum(driver);
		// System.out.println(totalD);
		// System.out.println("TOTAL "+ZakupyUtil.totalSum(driver));

		assertEquals(total, cenaProduktu, 2);

	}

	@After
	public void close() {
		// driver.quit();
	}

}
