package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ProductPage;
import utils.ScreenShot;

public class TestProductPage extends BaseTest {
	private ProductPage pp;

	@BeforeClass
	public void setup() {
		pp = new ProductPage(driver);
	}

	// verifying product page
	@Test(priority = 0)
	public void verifyProductPage() throws IOException {

		try {
			Assert.assertTrue(pp.verifyProductPageLoaded());
			Reporter.log("!!!verification of product page done-passed!!!");
		} catch (AssertionError e) {
			ScreenShot.storeScreenshot(driver, "verifyProductPage");
			Reporter.log("product page is not verified-Failed");
			throw e;
		}
	}

	@Test(priority = 1)
	public void addProductToCartTest() throws IOException {

		try { 
			pp.addProductToCart();
			Reporter.log("!!!product added to cart-passed!!!");
		} catch (Exception e) {
			ScreenShot.storeScreenshot(driver, "addProductToCartTest");
			Reporter.log("failed to add product to cart-Failed");
			System.out.println("error in addProductToCartTest");
		}
	}
}
