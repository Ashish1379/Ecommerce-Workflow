package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {
	// returns true or false whether a element is present or not after waiting
	public static boolean waitForElementAppearence(WebDriver driver , int timeout , By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if(wait.until(ExpectedConditions.visibilityOfElementLocated(element)) != null)
			return true;
		
		return false;
	}
	// returns element after waiting
	public static WebElement waitAndReturnElement(WebDriver driver , int timeout , By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		return webElement;
	}
}
