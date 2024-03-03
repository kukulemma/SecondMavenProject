package com.tutorialsninja.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
	
public WebDriver driver;
	
	@BeforeMethod
	public void loginSetup() {
		
		driver = new ChromeDriver();
		//driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		
	}
	
	@Test(priority=1)
	public void verifyLoginWithValidCredentials() {
	
		driver.findElement(By.id("input-email")).sendKeys("mahlet_lemma@yahoo.com");
		driver.findElement(By.id("input-password")).sendKeys("123abcde");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		
		
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
	
		driver.findElement(By.id("input-email")).sendKeys("ppusb@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("psub565789");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		
		
	}
	
	@Test(priority=3)
	public void verifyLoginWithValidEmailInvalidPassword() {
	
		driver.findElement(By.id("input-email")).sendKeys("mahlet_lemma@yahoo.com");
		driver.findElement(By.id("input-password")).sendKeys("Sell56789");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		
		
	}
	
	@Test(priority=4)
	public void verifyLoginWithInvalidEmailValidPassword() {
	
		driver.findElement(By.id("input-email")).sendKeys("mgmz2307@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("123abcde");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		
		
	}
	
	@Test(priority=5)
	public void verifyLoginWithNoCredentials() {
	
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}	






