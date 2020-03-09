package ebanking.keyword_driven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import utilities.LocatorHelper;

public class Keywords {

	WebDriver driver;

//	openBrowser
	public void openBrowser(String locType, String locValue, String testData) {
		if (testData.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
			driver = new ChromeDriver();
		} else if (testData.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".//drivers//geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			throw new RuntimeException("Browser Name must be either chrome or firefox");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

//	navigate
	public void navigate(String locType, String locValue, String testData) {
		driver.navigate().to(testData);
	}

//	fillTextBox
	public void fillTextBox(String locType, String locValue, String testData) {
		WebElement ele = driver.findElement(LocatorHelper.locate(locType, locValue));
		ele.clear();
		ele.sendKeys(testData);
	}

//	click
	public void click(String locType, String locValue, String testData) {
		driver.findElement(LocatorHelper.locate(locType, locValue)).click();
	}

//	selectByText
	public void selectByText(String locType, String locValue, String testData) {
		Select select = new Select(driver.findElement(LocatorHelper.locate(locType, locValue)));
		select.selectByVisibleText(testData);
	}

//	acceptAlert
	public void acceptAlert(String locType, String locValue, String testData) {
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
	}

//	closeBrowser
	public void closeBrowser(String locType, String locValue, String testData) {
		if (driver.getWindowHandles().size() > 1) {
			driver.quit();
		} else {
			driver.close();
		}
	}
	
	
//	public static void main(String[] args) throws InterruptedException {
//		Keywords keywords = new Keywords();
//		keywords.openBrowser("", "", "chrome");
//		keywords.navigate("", "", "http://primusbank.qedgetech.com/");
//		Thread.sleep(3000);
//		keywords.fillTextBox("id", "txtuId", "Admin");
//		keywords.fillTextBox("id", "txtPword", "Admin");
//		keywords.click("id", "login", "");
//		Thread.sleep(3000);
//		keywords.closeBrowser("", "", "");
//	}

}
