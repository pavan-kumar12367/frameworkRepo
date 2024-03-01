package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseClass;
import utilities.utility;

public class Register extends BaseClass {
	public Register() throws Exception {
		super();
	}
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver=Initialization();
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

	}

	@Test
	public void RegisterWithMandateFields() {

		driver.findElement(By.id("input-firstname")).sendKeys("Pavan");
		driver.findElement(By.id("input-lastname")).sendKeys("kumar");
		driver.findElement(By.id("input-email")).sendKeys(utility.timestamp());
		driver.findElement(By.id("input-telephone")).sendKeys("123456789");
		driver.findElement(By.id("input-password")).sendKeys("pavan@123");
		driver.findElement(By.id("input-confirm")).sendKeys("pavan@123");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String successmsg = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(successmsg, "Your Account Has Been Created!", "Your Account is not Created!");

	}
	@Test
	public void RegisterWithAllfields() {

		driver.findElement(By.id("input-firstname")).sendKeys("Pavan");
		driver.findElement(By.id("input-lastname")).sendKeys("kumar");
		driver.findElement(By.id("input-email")).sendKeys(utility.timestamp());
		driver.findElement(By.id("input-telephone")).sendKeys("123456789");
		driver.findElement(By.id("input-password")).sendKeys("pavan@123");
		driver.findElement(By.id("input-confirm")).sendKeys("pavan@123");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		

		String successmsg = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(successmsg, "Your Account Has Been Created!", "Your Account is not Created!");

	}


	@AfterMethod
	public void shutdown() {
		driver.quit();
	}

}
