package com.tutorialsninja.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest {
	
public WebDriver driver;
	
	@BeforeMethod
	public void registerSetup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();	
	}
	
	@Test(priority=1)
	public void verifyRegisterWithNoDetails() {
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualPrivacyPolicyWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedPrivacyPolicyWarningMessage = "Warning: You must agree to the Privacy Policy!";
		Assert.assertEquals(actualPrivacyPolicyWarningMessage, expectedPrivacyPolicyWarningMessage);
		
		String actualFirstNameWarningMessage = driver.findElement(By.cssSelector("input#input-firstname+div")).getText();
		String expectedFirstNameWarningMessage = "First Name must be between 1 and 32 characters!";
		Assert.assertTrue(actualFirstNameWarningMessage.contains(expectedFirstNameWarningMessage));
			
		String actualLastNameWarningMessage = driver.findElement(By.cssSelector("input#input-lastname+div")).getText();
		String expectedLastNameWarningMessage = "Last Name must be between 1 and 32 characters!";
		Assert.assertTrue(actualLastNameWarningMessage.contains(expectedLastNameWarningMessage));
		
		String actualEmailWarningMessage = driver.findElement(By.cssSelector("input#input-email+div")).getText();
		String expectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
		Assert.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));
		
		String actualTelephoneWarningMessage = driver.findElement(By.cssSelector("input#input-telephone+div")).getText();
		String expectedTelephoneWarningMessage = "Telephone must be between 3 and 32 characters!";
		Assert.assertTrue(actualTelephoneWarningMessage.contains(expectedTelephoneWarningMessage));
		
		String actualPasswordWarningMessage = driver.findElement(By.cssSelector("input#input-password+div")).getText();
		String expectedPasswordWarningMessage = "Password must be between 4 and 20 characters!";
		Assert.assertTrue(actualPasswordWarningMessage.contains(expectedPasswordWarningMessage));
	}
	
	@Test(priority=2)
	public void verifyRegisterWithMandatoryDetails() {
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys("Kuku2");
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys("Berta2");
		driver.findElement(By.cssSelector("input#input-email")).sendKeys("lemmamahlet8@gemail.com");
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys("7035876728");
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("basic2vip");
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys("basic2vip");
		driver.findElement(By.cssSelector("a.agree+input")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div#content>p:nth-child(2)")).isDisplayed());
		
	}
	
	@Test(priority=3)
	public void verifyRegisterWithAllDetails() {
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys("Kuku2");
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys("Berta2");
		driver.findElement(By.cssSelector("input#input-email")).sendKeys("lemmamahlet82@gemail.com");
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys("7035876728");
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("basic2vip");
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys("basic2vip");
		driver.findElement(By.xpath("//input[@name = 'newsletter' and @value='1']")).click();
		driver.findElement(By.cssSelector("a.agree+input")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div#content>p:nth-child(2)")).isDisplayed());
	}
	
	@Test(priority=4)
	public void verifyRegisterWithExistingEmail() {
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys("Kuku");
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys("Berta");
		driver.findElement(By.cssSelector("input#input-email")).sendKeys("mahlet_lemma@yahoo.com");
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys("7035226143");
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("123abcde");
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys("123abcde");
		driver.findElement(By.xpath("//input[@name = 'newsletter' and @value='1']")).click();
		driver.findElement(By.cssSelector("a.agree+input")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualExistingEmailWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedExistingEmailWarningMessage = "Warning: E-Mail Address is already registered!";
		Assert.assertTrue(actualExistingEmailWarningMessage.contains(expectedExistingEmailWarningMessage));
		
	}
	
	@Test(priority=5)
	public void verifyRegisterWithWrongConfirmPassword() {
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys("Kuku2");
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys("Breta2");
		driver.findElement(By.cssSelector("input#input-email")).sendKeys("lemmamahlet875@gmail.com");
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys("7035876728");
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("basic2vip");
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys("basic2vip28");
		driver.findElement(By.xpath("//input[@name = 'newsletter' and @value='1']")).click();
		driver.findElement(By.cssSelector("a.agree+input")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input#input-confirm+div")).isDisplayed());
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}


