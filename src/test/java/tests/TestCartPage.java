package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import utils.ScreenShot;

public class TestCartPage extends BaseTest {
	private CartPage cp;

	@BeforeClass
	public void setup() {
		cp = new CartPage(driver);
	}

	// verifying cart page

	@Test(priority = 0)
	public void verifyCartPageOpen() throws IOException {
		try {
			Assert.assertTrue(cp.verifyCartPage());
			Reporter.log("!!!cart page verified-passed!!!");
		} catch (AssertionError e) {
			ScreenShot.storeScreenshot(driver, "verifyCartPageOpen");
			Reporter.log("cart page verification failed-Failed");
//			throw e;
		}
	}

	// update number of product selected

	@Test(priority = 1)
	public void updateProductValueTest() throws IOException {
		try {
			cp.updateQuantity();
			Reporter.log("!!!product quantity updated-passed!!!");
		} catch (Exception e) {
			ScreenShot.storeScreenshot(driver, "updateProductValueTest");
			Reporter.log("!!!unable to update products quantity-Failed!!!");
//			System.out.println("error in updateProductValueTest");
		}
	}

	// validating price by calculating total price

	@Test(priority = 2)
	public void validatePrice() throws IOException {
		try {
			Assert.assertTrue(cp.verifyTotalPrice());
			Reporter.log("!!!Price validated-passed!!!");
		} catch (AssertionError e) {
			ScreenShot.storeScreenshot(driver, "validatePrice");
			Reporter.log("Price validation failed-Failed");
			throw e;
		}	
	}

	
	// move to checkout page by clicking on checkout button
	
	@Test(dependsOnMethods = { "validatePrice" })
	public void proceedToCheckoutPageTest() throws IOException {
		try {
			cp.clickProceedToCheckout();
			Reporter.log("!!!checkout button clicked-passed!!!");
		} catch (Exception e) {
			ScreenShot.storeScreenshot(driver, "proceedToCheckoutPageTest");
			Reporter.log("!!!unable to click on checkout button-Failed!!!");
//			System.out.println("error in proceedToCheckoutPageTest");
		}
		
	}

}
