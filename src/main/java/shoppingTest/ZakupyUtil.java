package shoppingTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZakupyUtil {
	
	public static boolean waitForAjax(WebDriver driver) {

	    WebDriverWait wait = new WebDriverWait(driver, 30);

	    ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
	      public Boolean apply(WebDriver driver) {
	        try {
	          return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
	        }
	        catch (Exception e) {
	     
	          return true;
	        }
	      }
	    };


	    ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
	      public Boolean apply(WebDriver driver) {
	        return ((JavascriptExecutor)driver).executeScript("return document.readyState")
	        .toString().equals("complete");
	      }
	    };

	  return wait.until(jQueryLoad) && wait.until(jsLoad);
	}
	
	public static void klikniecieLogowania(WebDriver driver) {
		////*[@id="header"]/div[2]/div/div/nav/div[2]/a
		//#header > div.nav > div > div > nav > div:nth-child(2) > a
		WebElement element = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a"));

		element.click();// developer musi sam wiedzie� �e znaleziony element jest klikalny

	}

	public static void logowanie(WebDriver driver) {
		klikniecieLogowania(driver);
		WebElement email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		email.sendKeys("b2bnetworkwarszawa@gmail.com");

		WebElement haslo = driver.findElement(By.cssSelector("#passwd"));
		haslo.sendKeys("n3tw0rk2017");

		WebElement submit = driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]/span/i"));
		submit.click();
	}
	public static void przekierowanieNaWomen(WebDriver driver) {
		WebElement women = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a"));
		women.click();
	}
	
	public static void clickSizeSWSekcjiWomen(WebDriver driver) {
		List<WebElement> checksWithSize = driver.findElements(By.xpath("//*[@id=\"ul_layered_id_attribute_group_1\"]"));
		for(WebElement el : checksWithSize) {
			if(el.getText().contains("S")) {
				el.click();
			}
		}
	}
	
	public static void clickObrazek(WebDriver driver, int nrObrazka) {
													 //*[@id="center_column"]/ul/li[2]/div/div[1]/div/a[1]/img
		WebElement el = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li["+nrObrazka+"]/div/div[1]/div/a[1]/img"));
		el.click();
	}
	
	public static void clickProceedToCheckout(WebDriver driver) {
		WebElement el = driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span"));
		el.click();
	}
	
	public static void przekierowanieDoKoszyka(WebDriver driver) {
		driver.navigate().to("http://automationpractice.com/index.php?controller=order");
	}
	
	public static List<WebElement> listaElementowKtoreSaGotoweDoZakupu(WebDriver driver){
		WebElement tabela = driver.findElement(By.xpath("//*[@id=\"cart_summary\"]/tbody"));
		List<WebElement> produkty = tabela.findElements(By.tagName("tr"));
		return produkty;
		
	}
	
	public static void clickDodaniePierwszeElementuZKoszyka(WebDriver driver) {
		WebElement mno = driver.findElement(By.xpath("//*[@id=\"cart_quantity_up_1_1_0_31337\"]/span/i"));
		mno.click();
	}
	
	
	
	public static double policzSumeProduktowWKoszyku(WebDriver driver) {
		double sumUnitPrice = 0;
		for(WebElement tr : listaElementowKtoreSaGotoweDoZakupu(driver)) {
			System.out.println("---------");
//			System.out.println(tr.getText());
			for(WebElement td : tr.findElements(By.tagName("td"))){
				System.out.println(td.getText());
			}
			String unitPrice = tr.findElements(By.tagName("td")).get(3).getText();
			WebElement input = driver.findElement(By.xpath("//*[@id=\"product_1_1_0_31337\"]/td[5]/input[2]"));
			int mnoznik =Integer.parseInt(input.getAttribute("value"));
			sumUnitPrice = sumUnitPrice + (Double.parseDouble(unitPrice.substring(1, unitPrice.length()))*mnoznik);
			System.out.println("unit price "+tr.findElements(By.tagName("td")).get(3).getText());
			
		}
		return sumUnitPrice;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static double cenaPierwszegoProduktuWKoszyku(WebDriver driver) {
		WebElement element = driver.findElement(By.xpath("//*[@id=\"product_price_6_31_31337\"]/span"));
		String wycietaCena = element.getText().substring(1);//$12.00
		return Double.parseDouble(wycietaCena);
	}
	
	public static double totalSum(WebDriver driver) {
		WebElement el = driver.findElement(By.xpath("//*[@id=\"total_product\"]"));
		String total = el.getText();
		return Double.parseDouble(total.substring(1));
		
	}
	

}
