package devops.medical.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
<<<<<<< HEAD
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HomePageTest {

=======
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;	
import org.openqa.selenium.htmlunit.HtmlUnitDriver;	

public class HomePageTest {
	
>>>>>>> e3d78c3480cca805c259eaf795118e46df2e8f67
	private WebDriver driver;
	String appURL1 = "http://localhost:8080/medical/";
	String appURL2 = "http://localhost:8080/medical/patientlogin";
	String appURL3 = "http://localhost:8080/medical/patientregister";
<<<<<<< HEAD

=======
	
>>>>>>> e3d78c3480cca805c259eaf795118e46df2e8f67
	@BeforeClass
	public void testSetUp() {
		driver = new HtmlUnitDriver();
	}
<<<<<<< HEAD

=======
	
>>>>>>> e3d78c3480cca805c259eaf795118e46df2e8f67
	@Test
	public void verifyHomePageTitle() {
		driver.get(appURL1);
		String getTitle = driver.getTitle();
		Assert.assertEquals(getTitle, "Welcome");
	}
<<<<<<< HEAD

=======
	
>>>>>>> e3d78c3480cca805c259eaf795118e46df2e8f67
	@Test
	public void verifyPatientLogin() {
		driver.get(appURL2);
		String getTitle = driver.getTitle();
		Assert.assertEquals(getTitle, "Patient Login");
	}
<<<<<<< HEAD

=======
	
>>>>>>> e3d78c3480cca805c259eaf795118e46df2e8f67
	@Test
	public void verifyPatientRegistration() {
		driver.get(appURL3);
		String getTitle = driver.getTitle();
		Assert.assertEquals(getTitle, "Registration");
	}
<<<<<<< HEAD

=======
	
>>>>>>> e3d78c3480cca805c259eaf795118e46df2e8f67
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
<<<<<<< HEAD

}
=======
	
}
>>>>>>> e3d78c3480cca805c259eaf795118e46df2e8f67
