package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserFactory {
	// returns a chrome driver
	public WebDriver launchChrome() {
		return new ChromeDriver();
	}

	// returns a edge driver
	public WebDriver launchEdge() {
		return new EdgeDriver();
	}
}
