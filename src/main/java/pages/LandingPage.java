
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import config.ConfigReader;

public class LandingPage {

	private WebDriver driver;
	private ConfigReader configReader;

	// creating variable of configReader and assigning driver

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		configReader = new ConfigReader();
	}

	// locators for locating elements

	private By heading = By.xpath("/html/body/div/h2");
	private By linkToHomePage = By.linkText("Enter the Store");
	private By footer = By.xpath("//*[@id='Content']/p[2]/sub");

	// verifying the landing page by title, heading and footerText
	public boolean verifyLandingPageLoaded() {
		String expectedTitle = (String) configReader.getValue("site.landingPage.title");
		String currTitle = driver.getTitle();

		boolean titleMatch = expectedTitle.equals(currTitle);

		String expectedHeading = (String) configReader.getValue("site.landingPage.heading");
		String actualHeading = driver.findElement(heading).getText();

		boolean headingMatch = expectedHeading.equals(actualHeading);

		String expectedFooterText = (String) configReader.getValue("site.landingPage.footer");
		String actualFooterText = driver.findElement(footer).getText();

		boolean footerTextMatched = expectedFooterText.equals(actualFooterText);

		return (titleMatch && headingMatch && footerTextMatched);
	}

	// to click and go to home page

	public void clickEnterStore() {
		driver.findElement(linkToHomePage).click();
	}
}
