
package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;
	private HomePage hp;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		hp = new HomePage(driver);
	}

	// locators for locating elements

	private By clickSignin = By.linkText("Sign In");
	private By username = By.name("username");
	private By password = By.name("password");
	private By loginBtn = By.name("signon");
	private By loginErrorMsg = By.xpath("//*[@id='Content']/ul/li");

	// checking loginBtn is present or not for verifying login page

	public boolean verifyLoginPage() {

		List<WebElement> buttons = driver.findElements(loginBtn);
		boolean present = (buttons.size() > 0);
		return present;
	}

	// click on sign in

	public void clickSignIn() {
		driver.findElement(clickSignin).click();
	}

	// entering username

	public void enterUsername(String user) {
		driver.findElement(username).clear();
		driver.findElement(username).sendKeys(user);
	}

	// entering password

	public void enterPassword(String pass) {
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(pass);
	}

	// clicking on login
	public void clickLogin() {
		driver.findElement(loginBtn).click();
	}

	// method for calling all the login required functions in one
	public void login(String user, String pass) {
		hp.clickSignIn();
		enterUsername(user);
		enterPassword(pass);
		clickLogin();
	}

	// getting Login error message

	public String getLoginErrorMessage() {
		return driver.findElement(loginErrorMsg).getText();
	}

}