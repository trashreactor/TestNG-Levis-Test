package test.suites;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import test.pages.HomePage;

/***
 * Tests login feature
 */
public class TestSale {

    private WebDriver driver;

    @BeforeSuite
    public void setup(){
        System.setProperty("webdriver.gecko.driver","C:\\Users\\troham\\Programs\\Programacion\\Git\\geckodriver.exe"); 
        driver = new FirefoxDriver();
        driver.get("https://www.levi.com.ar/");
        //Maximize window		
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void closeDriver(){
        driver.close();
        driver.quit();
    }

    @Test
    public void test_Sale_List_Links() {
        HomePage homePage = new HomePage(driver);
        homePage.listSaleLinks();
    }
  
    @Test
    public void test_Sale_Assert_Names() {
        HomePage homePage = new HomePage(driver);
        homePage.assertSaleNames();
    }

    @Test
    public void test_Footer_Assert_Names() {
        HomePage homePage = new HomePage(driver);
        homePage.assertFooterNames();
    }

    @Test
    public void test_Footer_Check_Links() {
        HomePage homePage = new HomePage(driver);
        homePage.CheckFooterLinks();
    }

    @Test
    public void test_jsExecutor_Scroll() {
        HomePage homePage = new HomePage(driver);
        homePage.jsExecutorScroll();
    }

    
    @Test
    public void test_jsExecutor_Print_URL() {
        HomePage homePage = new HomePage(driver);
        homePage.jsExecutorPrintSiteURL();
    }
    
    
    @Test
    public void test_jsExecutor_Print_Domain_Name() {
        HomePage homePage = new HomePage(driver);
        homePage.jsExecutorDomainName();
    }

    
    @Test
    public void test_jsExecutor_Open_new_URL() {
        HomePage homePage = new HomePage(driver);
        homePage.jsExecutorOpenNewUrl();
    }

    
    @Test
    public void test_jsExecutor_Click_Element() {
        HomePage homePage = new HomePage(driver);
        homePage.jsExecutorClickElement();
    }
  }