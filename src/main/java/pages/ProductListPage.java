package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.GenerateRandomNumber;

public class ProductListPage {
	private WebDriver driver;

	public ProductListPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// 
	private By returnToCategoryBtn = By.partialLinkText("Return to");

	
	// click on return to category page button
	public void returnToCategoryPage() {
		driver.findElement(returnToCategoryBtn).click();
	}

	public boolean validateProductListPage() {
		boolean returnBtnPresent = driver.findElements(returnToCategoryBtn).size() > 0;
		return returnBtnPresent;
	}

	public boolean selectProduct() {
		List<WebElement> productList = driver.findElements(By.xpath("//table/tbody/tr/td[1]/a"));
		// Generate a random integer between 0 and size
		int randomNumber = GenerateRandomNumber.generateNum(productList.size());

		WebElement product = productList.get(randomNumber);
		product.click();
		boolean  productAvailable= productList.size()>0;
		return productAvailable;
	}
}
