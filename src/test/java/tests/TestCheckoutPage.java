package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CheckoutPage;
import utils.ExcelUtils;
import utils.ScreenShot;

public class TestCheckoutPage extends BaseTest {

	private CheckoutPage cp;

	@BeforeClass
	public void setup() {
		cp = new CheckoutPage(driver);
	}

	@DataProvider(name = "paymentData")
	public Object[][] getData() throws IOException {
		ExcelUtils excel = new ExcelUtils("Payment Data");
		return excel.getData();
	}

	// verifying checkout page by matching heading text
	
	@Test(priority = 0)
	public void verifyCheckoutPage() throws IOException {
		try {
			Assert.assertTrue(cp.verifyCheckoutPage());
			Reporter.log("!!!checkout page verified-passed!!!");
		} catch (AssertionError e) {
			ScreenShot.storeScreenshot(driver, "verifyCheckoutPage");
			Reporter.log("unable to verify checkout page-Failed");
			throw e;
		}
	}

	
	// fetching data from excel and performing payment one by one

	@Test(priority = 1, dataProvider = "paymentData")
	public void sendPaymentData(String cardType, String cardNumber, String expiryDate, String firstName,
			String lastName, String address1, String address2, String city, String state, String ZIP, String country,
			String yesOrNo) throws InterruptedException, IOException {

		try {
			cp.sendPaymentData(cardType, cardNumber, expiryDate, firstName, lastName, address1, address2, city, state,
					ZIP, country, yesOrNo);
			Thread.sleep(1000);
			driver.navigate().back();
			if (yesOrNo.equalsIgnoreCase("yes")) {
				Thread.sleep(1000);
				driver.navigate().back();

			}
			Reporter.log("!!!" + firstName + "'s payment data sent successfully- passed!!!");
			System.out.println(firstName + " payment tested!!!");
		} catch (Exception e) {
			ScreenShot.storeScreenshot(driver, firstName+" payment failed");
			Reporter.log("failed to send " + firstName + "'s payment data-Failed");
			System.out.println("error in sendPaymentData");
		}

	}

	// moving to payment page after filling details

	@Test(dependsOnMethods = { "sendPaymentData" })
	public void moveToPaymentPageTest() throws IOException {
		try {
			cp.clickContinue();
			Reporter.log("!!!clicked on continue button-passed!!!");
		} catch (Exception e) {
			ScreenShot.storeScreenshot(driver, "moveToPaymentPageTest");
			Reporter.log("unable to click on continue  button-Failed");
			System.out.println("error in clickContinue");
		}

	}
}
