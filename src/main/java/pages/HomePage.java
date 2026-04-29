package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BaseTest;
import config.ConfigReader;
import utils.GenerateRandomNumber;
import utils.Waits;

public class HomePage extends BaseTest {

	private WebDriver driver;
	private ConfigReader configReader;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		configReader = new ConfigReader();
	}

	// locators for locating elements

	private By signIn = By.linkText("Sign In");
	private By myAccount = By.linkText("My Account");
	private By signOut = By.linkText("Sign Out");
	private By welcomeMsg = By.id("WelcomeContent");
	private By searchBox = By.name("keyword");
	private By searchBtn = By.name("searchProducts");
	private By quicklinks = By.id("QuickLinks");
	private By returnToHomePagebtn = By.linkText("Return to Main Menu");

	// check whether user is logged in or not

	public boolean isLoggedIn() {
		try {
			driver.findElement(myAccount);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Checks if user is NOT logged in

	public boolean isNotLoggedIn() {
		return !isLoggedIn();
	}

	// clicking on sign in button

	public void clickSignIn() {
		driver.findElement(signIn).click();
	}

	// Signing Out

	public void clickSignOut() {
		driver.findElement(signOut).click();
	}

	// Get welcome message

	public String getWelcomeMessage() {
		return driver.findElement(welcomeMsg).getText();
	}

	// Validate Home Page (after login)

	public boolean validateHomePage() throws InterruptedException {
		String expectedUrl = (String) configReader.getValue("site.categoryPage.url");
		String actualUrl = driver.getCurrentUrl();
		boolean signInPresent = driver.findElements(signIn).size() > 0;
		boolean signOutPresent = driver.findElements(signOut).size() > 0;

		boolean urlMatch = expectedUrl.equals(actualUrl);

		return (urlMatch && (signInPresent || signOutPresent));
//		System.out.println("Home Page validated successfully");
	}

	// Search functionality

	public boolean searchProduct(String text) {
		driver.findElement(searchBox).sendKeys(text);
		driver.findElement(searchBtn).click();
		
		int timeout = configReader.getTimeout();
//		boolean returnHomepage = driver.findElements(returnToHomePagebtn).size() > 0;
		boolean returnHomepage = Waits.waitForElementAppearence(driver , timeout , returnToHomePagebtn);
//		System.out.println(returnHomepage);
		if (!returnHomepage)
			return false;
		driver.findElement(searchBox).clear();
		CategoryPage cp = new CategoryPage(driver);
		cp.returnToMainMenu();
		return true;

	}

	// clicking on category
	
	public boolean clickOnCategory() {
		WebElement div = driver.findElement(quicklinks);
		List<WebElement> categories = div.findElements(By.tagName("a"));

		// Generate a random integer between 0 and size
		int randomNumber = GenerateRandomNumber.generateNum(categories.size());
		WebElement category = categories.get(randomNumber);
		category.click();
		boolean returnHomepage = driver.findElements(returnToHomePagebtn).size() > 0;
		return returnHomepage;
//        CategoryPage cp = new CategoryPage(driver);
//        cp.returnToMainMenu();

	}
}