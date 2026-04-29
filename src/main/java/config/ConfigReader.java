package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private Properties properties;
	private FileInputStream fileInputStream;

	// Constructor
	public ConfigReader() {
		try {

			// Relative path to config.properties
			String filePath = "./ObjectRepo/config.properties";

			fileInputStream = new FileInputStream(filePath);

			properties = new Properties();

			// Load properties file

			properties.load(fileInputStream);
			closeFiles();

		} catch (Exception e) {
			System.out.println("Error loading config.properties file");
		}
	}

	// Get Browser Name

	public String getBrowser(int val) {

		String browser = properties.getProperty("browser" + val);

		if (browser != null) {

			return browser;

		} else {
			System.out.println("browser not available");
			return null;
		}
	}

	// Get Application URL

	public String getApplicationURL() {

		String url = properties.getProperty("url");

		if (url != null) {

			return url;

		} else {

			throw new RuntimeException("URL not specified in config.properties");

		}
	}

	// Get Timeout Value

	public int getTimeout() {

		String timeout = properties.getProperty("timeout");

		if (timeout != null) {

			return Integer.parseInt(timeout);

		} else {

			throw new RuntimeException("Timeout not specified in config.properties");

		}
	}

	// returns excel path

	public String getExcelFilePath() {
		String filePath = properties.getProperty("LoginExcelData");
		return filePath;
	}

	// closes open files

	public void closeFiles() throws IOException {
		fileInputStream.close();
	}

	// return value stored in file for the passed key

	public Object getValue(String key) {
		return properties.get(key);
	}

}
