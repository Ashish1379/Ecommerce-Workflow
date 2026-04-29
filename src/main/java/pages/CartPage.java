package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import config.ConfigReader;
import utils.GenerateRandomNumber;

public class CartPage {
	private WebDriver driver;
	private int productNum = 1;
	private ConfigReader configReader;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		configReader = new ConfigReader();
	}

	// locators for the cart page

	private By heading = By.xpath("//*[@id=\"Cart\"]/h2");
	private By returnToMainmenuBtn = By.linkText("Return to Main Menu");
	private By value = By.xpath("//*[@id=\'Cart\']/form/table/tbody/tr[2]/td[5]/input");
	private By listPrice = By.xpath("//*[@id=\'Cart\']/form/table/tbody/tr[2]/td[6]");
	private By totalPrices = By.xpath("//*[@id=\'Cart\']/form/table/tbody/tr[2]/td[7]");
	private By removeProductBtn = By.linkText("Remove");
	private By subTotalPrice = By.xpath("//td[@colspan='7']");
	private By updateTotal = By.name("updateCartQuantities");
	private By proceedToCheckoutBtn = By.linkText("Proceed to Checkout");

	// validating price by calculating and fetching total price and then matching it

	public boolean verifyTotalPrice() {
		boolean priceMatched = false;
		try {
			String priceOfTotalItem = driver.findElement(totalPrices).getText();
//			priceOfTotalItem = priceOfTotalItem.substring(priceOfTotalItem.indexOf('$') + 1);
			priceOfTotalItem = priceOfTotalItem.substring(priceOfTotalItem.indexOf('$') + 1).replaceAll(",", "");

			double priceOfOneItem = getPriceofUnit();
			double totalPriceFromCart1 = getCartTotal();
			double totalPriceFromCart2 = Double.parseDouble(priceOfTotalItem);
			double calculatedTotalPrice = productNum * priceOfOneItem;
//		Thread.sleep(6000);
			priceMatched = ((totalPriceFromCart1 == totalPriceFromCart2)
					&& (totalPriceFromCart2 == calculatedTotalPrice));
//			System.out.println(
//					totalPriceFromCart1 + " " + totalPriceFromCart2 + " " + calculatedTotalPrice + " " + priceMatched);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return priceMatched;

	}

	// update any random quantity

	public void updateQuantity() {
		// Generate a random integer between 2 and 20

		int num = GenerateRandomNumber.generateNum(2, 21);
		productNum = num;
		driver.findElement(value).clear();
		driver.findElement(value).sendKeys(productNum + "");
		driver.findElement(updateTotal).click();
	}

	// click on remove button to remove the product

	public void removeProduct() {
		driver.findElement(removeProductBtn).click();
	}

	// returns the total value of the selected products
	public double getCartTotal() {
		String totalPrice = driver.findElement(subTotalPrice).getText();
//		totalPrice = totalPrice.substring(totalPrice.indexOf('$') + 1);
		totalPrice = totalPrice.substring(totalPrice.indexOf('$') + 1).replaceAll(",", "");
		double tp = Double.parseDouble(totalPrice);

		return tp;
	}

	// returns the value of the selected product for 1 unit

	public double getPriceofUnit() {
		String priceOfOneItem = driver.findElement(listPrice).getText();
		priceOfOneItem = priceOfOneItem.substring(priceOfOneItem.indexOf('$') + 1).replaceAll(",", "");
		double pooi = Double.parseDouble(priceOfOneItem);
		return pooi;
	}

	// click checkout button

	public void clickProceedToCheckout() {
		driver.findElement(proceedToCheckoutBtn).click();
	}

	// click on return to main menu button

	public void returnToMainMenu() {
		driver.findElement(returnToMainmenuBtn).click();
	}

	// verifying cart page by matching heading and by update total button and

	public boolean verifyCartPage() {
		String actualHeadingText = driver.findElement(heading).getText();
		String expectedHeadingText = (String) configReader.getValue("site.cartPage.headingText");

		boolean headingMatched = actualHeadingText.equals(expectedHeadingText);
		boolean updateTotalPresent = driver.findElements(updateTotal).size() > 0;
		boolean checkoutBtnPresent = driver.findElements(proceedToCheckoutBtn).size() > 0;

		return (headingMatched && checkoutBtnPresent && updateTotalPresent);
	}
}
