package pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageActions{
	Properties property=new Properties();
	private WebDriver drive;
	
	public HomePageActions(WebDriver drive) {
		this.drive=drive;
	}
	
	public void setup() throws Exception {
		File src = new File("src\\test\\resources\\pageObjectRepository\\HomePage.properties");
		FileInputStream objFile = new FileInputStream(src);
		property.load(objFile);
	}
	public void goToSignin() {
		drive.findElement(By.linkText(property.getProperty("signIn.linktext"))).click();
	}
}
