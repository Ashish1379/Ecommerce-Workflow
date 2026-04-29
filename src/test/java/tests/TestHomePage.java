package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import config.ConfigReader;
import pages.HomePage;
import pages.LoginPage;
import utils.ScreenShot;

public class TestHomePage extends BaseTest {
	private HomePage hp;
	private LoginPage lp;
	private ConfigReader configReader;

	// always check if user is logged in or not
	@BeforeClass
	public void setup() {
		configReader = new ConfigReader();
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		// Make sure logged in before testing home page
		while (!hp.isLoggedIn()) {
			hp.clickSignIn();
			lp.login("Jhonn1", "Jhonn1");
		}
	}

	// verifying homepage by checking url and presence of signin or sign out button

	@Test(priority = 0)
	public void verifyHomepage() throws InterruptedException, IOException {
		try {
			Assert.assertTrue(hp.validateHomePage());
			Reporter.log("!!!Home Page validation passed!!!");
		} catch (AssertionError e) {
			ScreenShot.storeScreenshot(driver, "verifyHomepage");
			Reporter.log("Home Page validation fails");
			throw e;
		}
	}

	// searching something from search box on home page

	@Test(priority = 1)
	public void searchOnHomepageTest() throws IOException {
		try {
			String search = (String) configReader.getValue("site.categoryPage.searchToken");
			Assert.assertTrue(hp.searchProduct(search));
			Reporter.log("!!!searching for pets passed!!!");
		} catch (AssertionError e) {
			ScreenShot.storeScreenshot(driver, "searchOnHomepageTest");
			Reporter.log("searching for pets failed");
			throw e;
		}

	}

	// clicking on any random category

	@Test(priority = 2)
	public void clickOnAnyCategoryTest() throws IOException {
		try {
			Assert.assertTrue(hp.clickOnCategory());
			Reporter.log("!!!Able to click on category-passed!!!");
		} catch (AssertionError e) {
			ScreenShot.storeScreenshot(driver, "clickOnAnyCategoryTest");
			Reporter.log("clicking on category failed");
			throw e;
		}

	}

}
