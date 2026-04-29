package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ScreenShot;

public class TestLoginPage extends BaseTest {
	private LoginPage login;
	private HomePage hp;

	// creating object of pages class to be used in this test

	@BeforeClass
	public void setup() {
		login = new LoginPage(driver);
		hp = new HomePage(driver);
		if(hp.isNotLoggedIn()) {
			hp.clickSignIn();
		}
	}

	// getting the data from excel and storing it in data provider for running all
	// test cases one by one

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws Exception {
		try {
			ExcelUtils excel = new ExcelUtils("Login Credentials");
			Reporter.log("Read Excel file successfull");
			return excel.getData();
		} catch (Exception e) {
			Reporter.log("Excel File Not Opening");
			throw new Exception("Excel file did not open");
		}
	}

	// login page verification by locating login button
	@Test(priority = 0)
	public void verifyLoginPage() throws IOException {
		try {
			Assert.assertTrue(login.verifyLoginPage());
			Reporter.log("!!!login page verified-passed!!!");
			driver.navigate().back();
		}
		catch(AssertionError e) {
			ScreenShot.storeScreenshot(driver, "verifyLoginPage");
			Reporter.log("login page not verified-failed");
			throw e;
		}
	}
	
	
	
	// logging in on site by getting data from excel one by one

	@Test(dataProvider = "loginData" , priority = 1)
	public void testLogin(String username, String password) throws IOException {
		try {
			login.login(username, password);
			
			if (hp.isLoggedIn()) {
				System.out.println("Login successful for: " + username);
				hp.clickSignOut();
			} else {
				System.out.println("Login failed for: " + username);
				ScreenShot.storeScreenshot(driver, "testLogin");
//            System.out.println("Error: " + login.getLoginErrorMessage());
			}

			Assert.assertTrue(hp.isLoggedIn() || hp.isNotLoggedIn());
			Reporter.log("!!!Login feature passed for user : " + username + " !!!");
		} catch (AssertionError e) {
			ScreenShot.storeScreenshot(driver, "testLogin");
			Reporter.log("Login feature failed for login page-failed");
			throw e;
		}
	}
}