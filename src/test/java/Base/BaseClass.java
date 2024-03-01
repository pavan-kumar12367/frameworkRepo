package Base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
	public Properties p;
	WebDriver driver;
	String urllink;

	public BaseClass() throws Exception {

		p = new Properties();
		File f = new File(".//src//main//java//configfiles//config.properties");
		FileInputStream file = new FileInputStream(f);
		p.load(file);
		urllink = p.getProperty("url");
	}

	public WebDriver Initialization() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(urllink);
		return driver;
	}

}
