package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CreateAccountAndLogin;
import pages.HomePageActions;
import pages.SearchProduct;

public class EcommerceTest {
	WebDriver drive;
	WebDriverWait wait;
	HomePageActions objHome;
	CreateAccountAndLogin objCreate;
	SearchProduct objSearch;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\darshita.shukla\\eclipse-workspace\\EcommerceAutomation\\src\\test\\resources\\drivers\\chromedriver.exe");
		drive = new ChromeDriver();
		wait = new WebDriverWait(drive, Duration.ofSeconds(5));
		objHome = new HomePageActions(drive);
		objCreate = new CreateAccountAndLogin(drive, wait);
		objSearch = new SearchProduct(drive);
	}

	@BeforeMethod
	public void beforeMethod() {
		drive.manage().window().maximize();
		drive.get("http://automationpractice.com/index.php");
	}

	@Test
	public void step01_CreateAccountSuccess() throws Exception {
		objHome.setup();
		objHome.goToSignin();
		objCreate.setup();
		objCreate.enterEmailCreateAccount();
		objCreate.enterDetailsCreateAccount();
		String actual = drive.findElement(By.className("info-account")).getText();
		Assert.assertEquals(actual,
				"Welcome to your account. Here you can manage all of your personal information and orders.");
		objCreate.signOut();
	}

	@Test
	public void step02_AccountExistsError() throws Exception {
		objHome.setup();
		objHome.goToSignin();
		objCreate.enterEmailCreateAccount();
		String actual = drive.findElement(By.xpath("//div[@id='create_account_error']")).getText();
//	Assert.assertEquals(actual,
		// "An account using this email address has already been registered. Please
		// enter a valid password or request a new one.");
		System.out.println(actual);
	}

	@Test
	public void step03_InvalidEmailAddressLoginError() throws Exception {
		objHome.setup();
		objHome.goToSignin();
		objCreate.incorrectLogin();
		String actual = drive.findElement(By.xpath("//li[normalize-space()='Invalid email address.']")).getText();
		Assert.assertEquals(actual, "Invalid email address.");
	}

	@Test
	public void step04_SearchProductFeature() throws Exception {
		objHome.setup();
		objSearch.openTshirtTabFromWomen();
		String expected = objSearch.searchProduct();
		String actual = drive
				.findElement(By.xpath(
						"/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[2]/ul[1]/li[1]/div[1]/div[2]/h5[1]/a[1]"))
				.getText();
		Assert.assertEquals(actual, expected);

	}

	@AfterClass
	public void afterClass() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		drive.quit();
	}
}
