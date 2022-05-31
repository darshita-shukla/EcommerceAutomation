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

	public void enterEmailCreateAccount() {
		js.executeScript("arguments[0].scrollIntoView();",
				drive.findElement(By.xpath(property.getProperty("enterEmail.xpath"))));
		drive.findElement(By.id(property.getProperty("enterEmail.id"))).sendKeys("testinghere1@testing.com");
		drive.findElement(By.id(property.getProperty("submitEmailBtn.id")));
	}

	public void enterDetailsCreateAccount() {
		wait.until(ExpectedConditions
				.elementToBeClickable(drive.findElement(By.id(property.getProperty("submitGenderMale.id")))));
		drive.findElement(By.id(property.getProperty("submitGenderMale.id"))).click();
		drive.findElement(By.id(property.getProperty("enterFirstName.id"))).sendKeys("First Name");
		drive.findElement(By.id(property.getProperty("enterLastName.id"))).sendKeys("Last Name");
		drive.findElement(By.id(property.getProperty("enterEmail.id"))).sendKeys("");
		drive.findElement(By.id(property.getProperty("enterPassword.id"))).sendKeys("testing1");
		Select date = new Select(drive.findElement(By.id(property.getProperty("selectDate.id"))));
		date.selectByIndex(9);
		Select month = new Select(drive.findElement(By.id(property.getProperty("selectMonth.id"))));
		month.selectByIndex(7);
		Select year = new Select(drive.findElement(By.id(property.getProperty("selectYear.id"))));
		year.selectByValue("1999");
		drive.findElement(By.id(property.getProperty("enterAddFirstName.id"))).sendKeys("First Name");
		drive.findElement(By.id(property.getProperty("enterAddLastName.id"))).sendKeys("Last Name");
		drive.findElement(By.id(property.getProperty("enterCompany.id"))).sendKeys("Company");
		drive.findElement(By.id(property.getProperty("enterAddress.id"))).sendKeys("Address");
		drive.findElement(By.id(property.getProperty("enterCity.id"))).sendKeys("City");
		Select state = new Select(drive.findElement(By.id(property.getProperty("selectState.id"))));
		state.selectByIndex(3);
		drive.findElement(By.id(property.getProperty("enterPostcode.id"))).sendKeys("123456");
		Select country = new Select(drive.findElement(By.id(property.getProperty("selectCountry.id"))));
		country.selectByValue("India");
		drive.findElement(By.id(property.getProperty("enterPhoneno.id"))).sendKeys("1234567890");
		drive.findElement(By.id(property.getProperty("enterAliasAddress.id"))).sendKeys("Home");
		drive.findElement(By.id(property.getProperty("submitCreateAccountBtn.id"))).click();
	}

}
