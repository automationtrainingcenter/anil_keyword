package ebanking.keyword_driven;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import utilities.LocatorHelper;
import utilities.ReportListener;

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
		// create an object of ReportListener class
		ReportListener listener = new ReportListener();
		// create an object of EventFiringWebDriver class
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
		// register listerner class object with edriver
		edriver.register(listener);

		driver = edriver;

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

	// switchToFrame
	public void switchToFrame(String locType, String locValue, String testData) {
		// locate the frame
		WebElement frame = driver.findElement(LocatorHelper.locate(locType, locValue));
		driver.switchTo().frame(frame);
	}

	// switchToDefaultContent
	public void switchToDefaultContent(String locType, String locValue, String testData) {
		driver.switchTo().defaultContent();
	}

	// moveToElement
	public void moveToElement(String locType, String locValue, String testData) {
		Actions actions = new Actions(driver);
		// locate the element to which we want to move
		WebElement element = driver.findElement(LocatorHelper.locate(locType, locValue));
		actions.moveToElement(element).build().perform();
	}

	// moveToElementAndClick
	public void moveToElementAndClick(String locType, String locValue, String testData) {
		Actions actions = new Actions(driver);
		// locate the element to which we want to move
		WebElement element = driver.findElement(LocatorHelper.locate(locType, locValue));
		actions.moveToElement(element).click().build().perform();
	}

	public void call(String methodName, String locType, String locValue, String testData) {
		Method[] methods = this.getClass().getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				try {
					method.invoke(this, locType, locValue, testData);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
		}
	}

//	public static void main(String[] args) throws InterruptedException {
//		Keywords keywords = new Keywords();
//		keywords.call("openBrowser", "", "", "chrome");
//		keywords.call("navigate", "", "", "http://www.google.com");
//		keywords.call("closeBrowser", "", "", "");
//	}

}
