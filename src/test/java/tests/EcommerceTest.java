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
import org.testng.annotations.DataProvider;
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

	@Test(dataProvider = "TestData1")
	public void step01_CreateAccountSuccess(String email, String fname, String lname, String password, String company,
			String address, String city, String pcode, String phno, String alias) throws Exception {
		objHome.setup();
		objHome.goToSignin();
		objCreate.setup();
		objCreate.enterEmailCreateAccount(email);
		objCreate.enterDetailsCreateAccount(fname, lname, password, company, address, city, pcode, phno, alias);
		String actual = drive.findElement(By.className("info-account")).getText();
		Assert.assertEquals(actual,
				"Welcome to your account. Here you can manage all of your personal information and orders.");
		objCreate.signOut();
	}

	@Test(dataProvider="TestData2")
	public void step02_AccountExistsError(String email) throws Exception {
		objHome.setup();
		objHome.goToSignin();
		objCreate.enterEmailCreateAccount(email);
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

	@DataProvider(name = "TestData1")
	public Object[][] getDataFromDataprovider1() {
		return new Object[][] {
				{ "testinghere@16.com", "First Name", "Last Name", "testing1", "Company", "Address", "City", "12345",
						"1234567890", "Home" },
				{ "testinghere@17.com", "First Name", "Last Name", "testing1", "Company", "Address", "City", "12345",
						"1234567890", "Home" } };
	}
	@DataProvider(name="TestData2")
	public Object[][] getDataFromDataprovider2() {
		return new Object[][] {
				{ "testinghere@16.com" },
				{ "testinghere@17.com" } };
	}
}
