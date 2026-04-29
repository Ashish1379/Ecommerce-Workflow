package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ProductListPage;
import utils.ScreenShot;

public class TestProductListPage extends BaseTest {
	private ProductListPage plp;

	@BeforeClass
	public void setup() {
		plp = new ProductListPage(driver);
	}

	// verifying the productlist page

	@Test(priority = 0)
	public void verifyProductListPage() throws IOException {
		try {
			Assert.assertTrue(plp.validateProductListPage());
			Reporter.log("!!!verification of productlist page done-passed!!!");
		} catch (AssertionError e) {
			ScreenShot.storeScreenshot(driver, "verifyProductListPage");
			Reporter.log("No return to category button present-Failed");
			throw e;
		}
	}

	// select any product by clicking on it

	@Test(priority = 1)
	public void clickOnProductTest() throws InterruptedException, IOException {
		
		try {
			Assert.assertTrue(plp.selectProduct());
			Reporter.log("!!!Product selected-passed!!!");
		} catch (AssertionError e) {
			ScreenShot.storeScreenshot(driver, "clickOnProductTest");
			Reporter.log("No product appears-Failed");
			throw e;
		}
	}
}
