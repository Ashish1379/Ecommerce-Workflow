package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BaseTest;

public class ProductPage extends BaseTest {
	private WebDriver driver;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
	}

	// locators for the product page
	private By addToCartBtn = By.linkText("Add to Cart");
	private By priceElement = By.xpath("//table/tbody/tr[6]");

	// clicking on add to cart button

	public void addProductToCart() {
		driver.findElement(addToCartBtn).click();
	}

	// getting price of the product

	public String getProductPrice() {
		return driver.findElement(priceElement).getText();
	}

	// verifying the product page by selecting price is available or not
	public boolean verifyProductPageLoaded() {
		boolean avialabilityOf$ = getProductPrice().contains("$");
		return avialabilityOf$;
	}
}
