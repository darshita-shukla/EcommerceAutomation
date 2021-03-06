package pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountAndLogin {
	Properties property = new Properties();
	private WebDriver drive;
	WebDriverWait wait;
	JavascriptExecutor js = (JavascriptExecutor) drive;

	public CreateAccountAndLogin(WebDriver drive, WebDriverWait wait) {
		this.drive = drive;
		this.wait = wait;
	}

	public void setup() throws Exception {
		File src = new File("src\\test\\resources\\pageObjectRepository\\CreateAccountAndLogin.properties");
		FileInputStream objFile = new FileInputStream(src);
		property.load(objFile);
	}

	public void enterEmailCreateAccount(String email) throws Exception {
		Thread.sleep(3000);
		drive.findElement(By.cssSelector(property.getProperty("enterEmail.css"))).sendKeys(email);
		drive.findElement(By.xpath(property.getProperty("submitEmailBtn.xpath"))).click();
	}

	public void enterDetailsCreateAccount(String fname, String lname, String password, String company, String address,
			String city, String pcode, String phno, String alias) throws Exception {
		Thread.sleep(5000);
//		wait.until(ExpectedConditions
//				.elementToBeClickable(drive.findElement(By.id(property.getProperty("submitGenderMale.id")))));
		drive.findElement(By.id(property.getProperty("submitGenderMale.id"))).click();
		drive.findElement(By.id(property.getProperty("enterFirstName.id"))).sendKeys(fname);
		drive.findElement(By.id(property.getProperty("enterLastName.id"))).sendKeys(lname);
		drive.findElement(By.id(property.getProperty("enterEmail.id"))).sendKeys("");
		drive.findElement(By.id(property.getProperty("enterPassword.id"))).sendKeys(password);
		Select date = new Select(drive.findElement(By.id(property.getProperty("selectDate.id"))));
		date.selectByIndex(9);
		Select month = new Select(drive.findElement(By.id(property.getProperty("selectMonth.id"))));
		month.selectByIndex(7);
		Select year = new Select(drive.findElement(By.id(property.getProperty("selectYear.id"))));
		year.selectByValue("1999");
		drive.findElement(By.id(property.getProperty("enterAddFirstName.id"))).sendKeys(fname);
		drive.findElement(By.id(property.getProperty("enterAddLastName.id"))).sendKeys(lname);
		drive.findElement(By.id(property.getProperty("enterCompany.id"))).sendKeys(company);
		drive.findElement(By.id(property.getProperty("enterAddress.id"))).sendKeys(address);
		drive.findElement(By.id(property.getProperty("enterCity.id"))).sendKeys(city);
		Select state = new Select(drive.findElement(By.id(property.getProperty("selectState.id"))));
		state.selectByIndex(3);
		drive.findElement(By.id(property.getProperty("enterPostcode.id"))).sendKeys(pcode);
		Select country = new Select(drive.findElement(By.id(property.getProperty("selectCountry.id"))));
		country.selectByIndex(1);
		drive.findElement(By.id(property.getProperty("enterPhoneno.id"))).sendKeys(phno);
		drive.findElement(By.id(property.getProperty("enterAliasAddress.id"))).sendKeys(alias);
		drive.findElement(By.id(property.getProperty("submitCreateAccountBtn.id"))).click();
	}

	public void signOut() {
		drive.findElement(By.linkText(property.getProperty("signOutBtn.linktext"))).click();
	}

	public void incorrectLogin() {
		drive.findElement(By.id(property.getProperty("enterLoginEmail.id"))).sendKeys("testing1com");
		drive.findElement(By.id(property.getProperty("enterLoginPassword.id"))).sendKeys("testing1");
		drive.findElement(By.id(property.getProperty("submitLoginBtn.id"))).click();
	}

}
