
package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LandingPage;
import utils.ScreenShot;

public class TestLandingPage extends BaseTest {
	private LandingPage l;

	// creating object of pages class to be used in this test
	@BeforeClass
	public void setup() {
		l = new LandingPage(driver);
	}

	// verify whether we are on landing page or not
	// go to home page by clicking on the link

	@Test(priority = 0)
	public void testLandingPage() throws IOException {
		try {
			;
			Assert.assertTrue(l.verifyLandingPageLoaded());
			Reporter.log("!!!Landing page verification passed!!!");
//			System.out.println("Hii");
		} catch (AssertionError e) {
			ScreenShot.storeScreenshot(driver, "landingPageVerification");
			Reporter.log("Landing page verification faild");
			throw e;
		}
	}

	// clicking on link to enter home page

	@Test(priority = 1)
	public void navigateToHome() throws IOException {
		try {
			l.clickEnterStore();
			Reporter.log("!!!Succefully navigated to home page!!!");
//			System.out.println("Hii1");
		} catch (Exception e) {
			ScreenShot.storeScreenshot(driver, "navigateToHome");
			Reporter.log("Navigation To home page failed");
			System.out.println("error in navigateToHome");
		}
	}
}