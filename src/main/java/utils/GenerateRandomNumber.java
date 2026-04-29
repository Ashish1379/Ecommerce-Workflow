package utils;

import java.util.Random;

public class GenerateRandomNumber {
	
	public static int generateNum(int n) {
		Random randomNum = new Random();
		// Generate a random integer between 0 and 20
		int randomNumber = randomNum.nextInt(n);
		return randomNumber;
	}
	public static int generateNum(int start , int end) {
		Random randomNum = new Random();
		// Generate a random integer between 0 and 20
		int randomNumber = randomNum.nextInt(start , end);
		return randomNumber;
	}
}

