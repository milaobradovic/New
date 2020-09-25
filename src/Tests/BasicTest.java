package Tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.time.LocalDate;

import org.openqa.selenium.WebDriverException;
;



public abstract class BasicTest {


	protected WebDriver driver;
	protected WebDriverWait wait;
	protected String baseURL="";



		@BeforeClass
		public void beforeClass() {
			System.setProperty("webdriver.chrome.driver",
					"driver-lib\\chromedriver.exe");
			
			this.driver = new ChromeDriver();
			this.wait = new WebDriverWait(driver, 30);
			this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		}

    	@AfterClass
    	public void afterClass() {
    	}
    	
    	@AfterMethod
    	public void afterTest(ITestResult result) throws IOException{
    		if (result.getStatus() == ITestResult.FAILURE) {
    			File scrFile=((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
    			FileHandler.copy(scrFile, new File ("C:\\Users\\SoficKole\\Desktop\\Java\\New\\Screenshots\\.png"));
    			LocalDate myObj=LocalDate.now();
    			   System.out.println("Sacuvan je screenshot!");
    		}else {
    			System.out.println("Test je uspesan.");	
    		}
 
    	}

}
