package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseClass;

public class Search extends BaseClass {

	public Search() throws Exception {
		super();

	}

	WebDriver driver;

	@BeforeMethod
	public void search() {
		driver = Initialization();
	}

	@Test
	
	public void searchWithValidProducts() {
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(
				By.xpath("//button[@type='button' and contains(@class, 'btn-default') and contains(@class, 'btn-lg')]"))
				.click();
		Boolean status = driver.findElement(By.linkText("HP LP3065")).isDisplayed();
		Assert.assertTrue(status);

	}

	@Test
	public void searchWithEmptyFields() {
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(
				By.xpath("//button[@type='button' and contains(@class, 'btn-default') and contains(@class, 'btn-lg')]"))
				.click();
		Boolean status = driver
				.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']"))
				.isDisplayed();
		Assert.assertTrue(status);

	}

	@AfterMethod

	public void shutDown() {

		driver.close();
	}
}
