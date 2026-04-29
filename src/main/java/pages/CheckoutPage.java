package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import config.ConfigReader;

public class CheckoutPage {
	private WebDriver driver;
	private ConfigReader configReader;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		configReader = new ConfigReader();
	}

	// loactors for checkout page

	private By tableHeading = By.xpath("//*[@id=\"Catalog\"]/form/table/tbody/tr[1]/th");
	private By cardType = By.name("order.cardType");
	private By cardNumber = By.name("order.creditCard");
	private By expiryDate = By.name("order.expiryDate");
	private By firstName = By.name("order.billToFirstName");
	private By lastName = By.name("order.billToLastName");
	private By address1 = By.name("order.billAddress1");
	private By address2 = By.name("order.billAddress2");
	private By city = By.name("order.billCity");
	private By state = By.name("order.billState");
	private By zipCode = By.name("order.billZip");
	private By country = By.name("order.billCountry");
	private By shipToDifferentAddress = By.name("shippingAddressRequired");
	private By continueBtn = By.name("newOrder");

	// verifying checkout page by comparing heading text

	public boolean verifyCheckoutPage() {
		String expectedHeading = (String) configReader.getValue("site.checkoutPage.headingText");
		String actualHeading = driver.findElement(tableHeading).getText();

		boolean headingMatched = expectedHeading.equals(actualHeading);
		return headingMatched;
	}

	// select card type

	public void selectCardType(String data) {
		WebElement cards = driver.findElement(cardType);
		Select select = new Select(cards);
		select.selectByVisibleText(data);
	}

	// send card number

	public void enterCardNumber(String data) {
		driver.findElement(cardNumber).clear();
		driver.findElement(cardNumber).sendKeys(data);

	}

	// send expiry date for card
	public void enterExpiryDate(String data) {
		driver.findElement(expiryDate).clear();
		driver.findElement(expiryDate).sendKeys(data);
	}

	// sends first name of customer

	public void enterFirstName(String data) {
		driver.findElement(firstName).clear();
		driver.findElement(firstName).sendKeys(data);
	}

	// sends last name of customer

	public void enterLastName(String data) {
		driver.findElement(lastName).clear();
		driver.findElement(lastName).sendKeys(data);
	}

	// send address1

	public void enterAddress1(String data) {
		driver.findElement(address1).clear();
		driver.findElement(address1).sendKeys(data);
	}

	// send address2

	public void enterAddress2(String data) {
		driver.findElement(address2).clear();
		driver.findElement(address2).sendKeys(data);
	}

	// sends city data

	public void enterCity(String data) {
		driver.findElement(city).clear();
		driver.findElement(city).sendKeys(data);
	}

	// send state data

	public void enterState(String data) {
		driver.findElement(state).clear();
		driver.findElement(state).sendKeys(data);
	}

	// send zip code

	public void enterZip(String data) {
		driver.findElement(zipCode).clear();
		driver.findElement(zipCode).sendKeys(data);
	}

	// sends country name
	public void enterCountry(String data) {
		driver.findElement(country).clear();
		driver.findElement(country).sendKeys(data);
	}

	// select if shipping for some one else or not

	public void shipToDifferentAddress(String data) {
		WebElement checkBox = driver.findElement(shipToDifferentAddress);
		if (data.equalsIgnoreCase("yes")) {
			if (!checkBox.isSelected()) {
				checkBox.click();
			}
		} 
		else {
			if (checkBox.isSelected()) {
				checkBox.click();
			}
		}
	}

	// click on continue for moving to payment page

	public void clickContinue() {
		driver.findElement(continueBtn).click();
	}

	// sending payment data to site by invoking all methods required

	public void sendPaymentData(String cardType, String cardNumber, String expiryDate, String firstName,
			String lastName, String address1, String address2, String city, String state, String ZIP, String country,
			String yesOrNo) {
		selectCardType(cardType);
		enterCardNumber(cardNumber);
		enterExpiryDate(expiryDate);
		enterFirstName(firstName);
		enterLastName(lastName);
		enterAddress1(address1);
		enterAddress2(address2);
		enterCity(city);
		enterState(state);
		enterZip(ZIP);
		enterCountry(country);
		shipToDifferentAddress(yesOrNo);
		clickContinue();
		if (yesOrNo.equalsIgnoreCase("yes")) {
			clickContinue();
		}

	}

}
