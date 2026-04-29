
package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import config.ConfigReader;
import utils.BrowserFactory;

public class BaseTest {

	protected static WebDriver driver;
	private ConfigReader reader = new ConfigReader();
	private BrowserFactory browserFactory;

	// open desired browser and navigate to the url
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter name of browser: (chrome/edge)only");
//		browser = sc.nextLine().trim();
//		sc.close();
		browserFactory = new BrowserFactory();
		if (browser.equalsIgnoreCase("chrome")) {

//			driver = new ChromeDriver();
			driver = browserFactory.launchChrome();

		} else if (browser.equalsIgnoreCase("edge")) {

//			driver = new EdgeDriver();
			driver = browserFactory.launchEdge();
		}
		String url = reader.getApplicationURL();
		System.out.println("Running \"" + url + "\" on " + browser + " browser");
		driver.manage().window().maximize();
		driver.get(url);
	}

	// close the browser after running all tests
	@AfterTest
	public void teardown() {
		if (driver != null) {
			driver.quit();
			System.out.println("Driver closed successfully");
		}
	}
}
