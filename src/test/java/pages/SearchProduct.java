package pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SearchProduct {
	Properties property=new Properties();
	private WebDriver drive;
	WebElement womenTab;
	WebElement tshirtTab;
	String productName="";
	
	public SearchProduct(WebDriver drive) {
		this.drive=drive;
	}
	
	public void setup() throws Exception {
		File src = new File("src\\test\\resources\\pageObjectRepository\\SearchProduct.properties");
		FileInputStream objFile = new FileInputStream(src);
		property.load(objFile);
	}
	
	public void openTshirtTabFromWomen() throws Exception {
		Actions action= new Actions(drive);
		womenTab=drive.findElement(By.xpath(property.getProperty("womenTab.xpath")));
		tshirtTab=drive.findElement(By.xpath(property.getProperty("tshirtTab.xpath")));
		action.moveToElement(womenTab).moveToElement(tshirtTab).click().perform();
		Thread.sleep(3000);
	}
	
	public String searchProduct() {
		productName=drive.findElement(By.xpath(property.getProperty("productName.xpath"))).getText();
		drive.findElement(By.id(property.getProperty("searchBar.id"))).sendKeys(productName);
		drive.findElement(By.name(property.getProperty("searchBtn.name"))).click();
		return productName;
	}
}
