package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CategoryPage;
import utils.ScreenShot;

public class TestCategoryPage extends BaseTest {
	private CategoryPage cp;

	@BeforeClass
	public void setup() {
		cp = new CategoryPage(driver);
	}

	
	// verifying the category page loaded
	
	@Test(priority = 0)
	public void verifyCategoryPage() throws IOException {

		
		try {
			Assert.assertTrue(cp.verifyCategoryLoaded());
			Reporter.log("!!!verification of category page completed-passed!!!");
		}
		catch(AssertionError e) {
			ScreenShot.storeScreenshot(driver, "verifyCategoryPage");
			Reporter.log("No return to main menu button found-Failed");
		}
		
	}

	
	// clicking on any random product to move towards product list page
	@Test(priority = 1)
	public void selectAnyProductTest() throws IOException {
		
		try {
			Assert.assertTrue(cp.selectProduct());
			Reporter.log("!!!Product is selected-passed!!!");
		}
		catch(AssertionError e) {
			ScreenShot.storeScreenshot(driver, "selectAnyProductTest");
			Reporter.log("No products found in this category-Failed");
		}
	}
}
