package test.pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * Page Object encapsulates the Sign-in page.
 */
public class HomePage {
    private WebDriver driver;

    private By bySaleMenu = By.cssSelector("a[title='SALE']");
    private By bySaleCategory = By.id("cat-sale");
    private By bySaleContenidoMenu = By.cssSelector("div[class='contenido-menu']");
    private By bySaleLink = By.tagName("a");
    private By byFooterContainer = By.tagName("footer");
    private By byFooterLinks = By.tagName("a");

    private List<String> saleButtonNamesList = Arrays.asList(new String[]{"Hombre", "Mujer", "Kids", "Jeans", "Pantalones", "Remeras", "Remeras y Polos", "Camperas", "Camisas", "Sweaters y Buzos", "Accesorios", "Faldas y Vestidos", "Girls (4 - 7 años)", "Boys (4 - 7 años)", "Teen Girls (8 - 16 años)", "Teen Boys (8 - 16 años)"});
    private List<String> footerLinkNamesList = Arrays.asList(new String[]{"Preguntas frecuentes", "Cómo comprar", "Consultas de stock", "Envío y seguimiento", "Formas de pago", "Cambios y devoluciones", "Promociones", "Ayuda", "Contacto", "Teléfono e-Shop: (011) 5368 - 2373", "lunes a viernes de 9:00 a 18:00","ventaonline@levi.com.ar", "Sobre Levi's®", "Puntos de venta", "Trabajá con nosotros", "Mayorista", "Términos y condiciones", "Política de privacidad"});

    public HomePage(WebDriver driver){
        this.driver = driver;
        if (!driver.getTitle().equals("Levi's ® Argentina")) {
        throw new IllegalStateException("This is not the Home Page," +
                " current page is: " + driver.getCurrentUrl());
        }
    }

    public HomePage manageProfile() {
        // Page encapsulation to manage profile functionality
        return new HomePage(driver);
    }

    /**
    * List the links of all the a under Sale Button
    * @return void
    */
    public void listSaleLinks(){
        Reporter.log("Preparing to list all Sale Links..",true);
        WebElement objSale = driver.findElement(bySaleCategory);
        WebElement objSaleContainter = objSale.findElement(bySaleContenidoMenu);
        List<WebElement> objSaleList = objSaleContainter.findElements(bySaleLink);
        if(objSaleList.size()>0){
            System.out.println("Elements List:");
            for (WebElement element : objSaleList)
            {
                Reporter.log(element.getAttribute("innerHTML")+": "+element.getAttribute("href"),true);
            }
        }else{
            Reporter.log("No elements to print.",true);
        }
    }

    /**
    * Assert the names of all the a under Sale Button against the buttonNamesList list
    * @return void
    */
    public void assertSaleNames(){
        Reporter.log("Asserting Sale Names..",true);
        WebElement objSale = driver.findElement(bySaleCategory);
        WebElement objSaleContainter = objSale.findElement(bySaleContenidoMenu);
        List<WebElement> objSaleList = objSaleContainter.findElements(bySaleLink);
        for (WebElement element : objSaleList)
        {
            Reporter.log("Asserting.. "+element.getAttribute("innerHTML"),true);
            Assert.assertTrue(saleButtonNamesList.contains(element.getAttribute("innerHTML")));
        }
    }

    /**
    * Assert the names of all the a under Footer against the saleButtonNamesList list
    * @return void
    */
    public void assertFooterNames(){
        Reporter.log("Asserting Footer Names..",true);
        WebElement objFooter = driver.findElement(byFooterContainer);
        List<WebElement> objFooterList = objFooter.findElements(byFooterLinks);
        for (WebElement element : objFooterList)
        {
            Reporter.log("Asserting.. "+element.getText(),true);
            Assert.assertTrue(footerLinkNamesList.contains(element.getText()));
        }
    }    

    /**
    * Check links of all the a under Footer functionality
    * @return void
    */
    public void CheckFooterLinks(){
        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;

        Reporter.log("Checking Footer Links..",true);
        WebElement objFooter = driver.findElement(byFooterContainer);
        List<WebElement> objFooterList = objFooter.findElements(byFooterLinks);
        Iterator<WebElement> it = objFooterList.iterator();
        
        while(it.hasNext()){
          
            url = it.next().getAttribute("href");
            if(url == null || url.isEmpty() || url.startsWith("tel:") || url.startsWith("mailto:")){
                Reporter.log("URL is either not configured for anchor tag or it is empty, skipping..",true);
            }else{
                try {
                    huc = (HttpURLConnection)(new URL(url).openConnection());
                    huc.setRequestMethod("HEAD");
                    huc.connect();
                    respCode = huc.getResponseCode();
                    if(respCode >= 400){
                        Reporter.log("Checking.. "+url+" is a broken link",true);
                    }
                    else{
                        Reporter.log("Checking.. "+url+" is a valid link",true);
                    }
                        
                } catch (MalformedURLException e) {
                    Reporter.log("Malformed URL detected on: "+url,true);
                    //e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    } 

    public void jsExecutorScroll(){
        Reporter.log("JSExecutor Sample..",true);
        //Creating the JavascriptExecutor interface object by Type casting		
        JavascriptExecutor js = (JavascriptExecutor)driver;
        //Vertical scroll down by 600  pixels		
        js.executeScript("window.scrollBy(0,600)");	
    }

    public void jsExecutorPrintSiteURL(){
        Reporter.log("JSExecutor Sample..",true);
        //Creating the JavascriptExecutor interface object by Type casting		
        JavascriptExecutor js = (JavascriptExecutor)driver;
        String url = js.executeScript("return document.URL;").toString();			
        Reporter.log("URL of the site = "+url,true);
    }

    public void jsExecutorDomainName(){
        Reporter.log("JSExecutor Sample..",true);
        //Creating the JavascriptExecutor interface object by Type casting		
        JavascriptExecutor js = (JavascriptExecutor)driver;
        String DomainName = js.executeScript("return document.domain;").toString();			
        Reporter.log("Domain name of the site = "+DomainName,true);
    }

    public void jsExecutorOpenNewUrl(){
        Reporter.log("JSExecutor Sample..",true);
        //Creating the JavascriptExecutor interface object by Type casting		
        JavascriptExecutor js = (JavascriptExecutor)driver;
        //Navigate to new Page i.e to generate access page. (launch new url)		
        js.executeScript("window.location = 'https://www.google.com/'");
        String DomainName = js.executeScript("return document.domain;").toString();			
        Reporter.log("Domain name of the site = "+DomainName,true);
    }

    public void jsExecutorClickElement(){
        Reporter.log("JSExecutor Sample..",true);
        WebElement objSale = driver.findElement(bySaleMenu);
        //Creating the JavascriptExecutor interface object by Type casting		
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", objSale);	
        Reporter.log("Clicked on object: "+objSale.getText(),true);	
    }
}