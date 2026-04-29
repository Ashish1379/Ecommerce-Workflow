package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BaseTest;
import utils.GenerateRandomNumber;

public class CategoryPage extends BaseTest {
	private WebDriver driver;

	public CategoryPage(WebDriver driver) {
		this.driver = driver;
	}

	// locators for locating elements

	private By returnToHomePagebtn = By.linkText("Return to Main Menu");
	private By productListElement = By.xpath("//table/tbody/tr/td[1]/a");

	// selection any random product from the category page

	public boolean selectProduct() {
		List<WebElement> productList = driver.findElements(productListElement);
		boolean productAvailable = productList.size() > 0;
		if (!productAvailable)
			return false;
		// Generate a random integer between 0 and size
		int randomNumber = GenerateRandomNumber.generateNum(productList.size());
		WebElement product = productList.get(randomNumber);
		product.click();
		return true;

	}

	// verifying the category page by finding the return to home button
	
	public boolean verifyCategoryLoaded() {

		boolean presentBtn = driver.findElements(returnToHomePagebtn).size() > 0;
		return presentBtn;

	}

	// clicking on return to main menu button to move back to home page
	
	public void returnToMainMenu() {
		driver.findElement(returnToHomePagebtn).click();
	}
}
