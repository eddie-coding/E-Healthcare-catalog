package devops.medical.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

public class HomePageTest {

	private WebDriver driver;
	String appURL1 = "http://localhost:8080/medical/";
	String appURL2 = "http://localhost:8080/medical/patientlogin";
	String appURL3 = "http://localhost:8080/medical/patientregister";

	@BeforeClass
	public void testSetUp() {
		driver = new ChromeDriver();
	}

	@Test
	public void verifyHomePageTitle() {
		driver.navigate().to(appURL1);
		String getTitle = driver.getTitle();
		Assert.assertEquals(getTitle, "Welcome");
	}

	@Test
	public void verifyPatientLogin() {
		driver.navigate().to(appURL2);
		String getTitle = driver.getTitle();
		Assert.assertEquals(getTitle, "Patient Login");
	}

	@Test
	public void verifyPatientRegistration() {
		driver.navigate().to(appURL3);
		String getTitle = driver.getTitle();
		Assert.assertEquals(getTitle, "Registration");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
