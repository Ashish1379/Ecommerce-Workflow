package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	
	// store screenshots in the screenshots folder
	
	public static void storeScreenshot(WebDriver driver, String screenshotName) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
		//System.out.println("running");
		File dest = new File(filePath);
		FileUtils.copyFile(src, dest);
	}

	
	// store screenshots in the screenshots folder and returns the path where the picture is stored
	
	public static String takeScreenshotWithPath(WebDriver driver, String screenshotName) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
		File dest = new File(filePath);
		FileUtils.copyFile(src, dest);
		return filePath;
	}
}
