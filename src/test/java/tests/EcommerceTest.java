package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CreateAccountAndLogin;
import pages.HomePageActions;

public class EcommerceTest {
	WebDriver drive;
	WebDriverWait wait;
	HomePageActions objHome;
	CreateAccountAndLogin objCreate;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\darshita.shukla\\eclipse-workspace\\EcommerceAutomation\\src\\test\\resources\\drivers\\chromedriver.exe");
		drive = new ChromeDriver();
		wait = new WebDriverWait(drive, Duration.ofSeconds(5));
		objHome = new HomePageActions(drive);
		objCreate = new CreateAccountAndLogin(drive, wait);
		drive.get("http://automationpractice.com/index.php");
		drive.manage().window().maximize();
	}

	@Test
	public void clickSignIn() throws Exception {
		objHome.setup();
		objHome.goToSignin();
	}

	@Test
	public void CreateAccount() throws Exception {
		objCreate.setup();
		objCreate.enterEmailCreateAccount();
		objCreate.enterDetailsCreateAccount();
	}
	
	@AfterClass
	public void quit() {
		drive.quit();
	}
}
