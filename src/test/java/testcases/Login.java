package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseClass;
import utilities.utility;

public class Login extends BaseClass {

	public Login() throws Exception {
		super();
	}

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = Initialization();
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();

	}

	@Test(dataProvider="Testdata")
	public void loginWithValiddata(String emailid,String pwd) {

		driver.findElement(By.id("input-email")).sendKeys(emailid);
		driver.findElement(By.id("input-password")).sendKeys(pwd);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),
				"wrong email id");
	}
	@DataProvider(name ="Testdata")
	public Object[][] Testdata() throws Exception {
		Object[][] data = utility.generateTestData("login");
		return data;
	}

	@Test(enabled = false)
	public void loginWithEmptyFields() {

		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualerror = driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText();
		Assert.assertEquals(actualerror, "Warning: No match for E-Mail Address and/or Password.");
	}

	@Test
	public void loginWithInvalidDetails() {

		driver.findElement(By.id("input-email")).sendKeys(utility.timestamp());
		driver.findElement(By.id("input-password")).sendKeys("pwd");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualerror = driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText();
		Assert.assertEquals(actualerror, "Warning: No match for E-Mail Address and/or Password.");
	}

	@AfterMethod

	public void shutDown() {
		driver.quit();
	}
}
