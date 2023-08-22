package Pages;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ataberkkilavuzcu.Driver.DriverChrome;

public class BasePage {
    
    ChromeDriver driver = DriverChrome.getDriver();
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    //waiting maximum of 105 seconds for loading page and getting elements from page.
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(105)); 

    public BasePage(){
        
    }
    
    public void launch(String url){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(url);
    }
    public void waitUntilClickableAndClick(By by){
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    public void click(WebElement element){   
        scrollIntoView(element);
        element.click();
    }
    
    public void sendKeys(By by, String text){
        driver.findElement(by).sendKeys(text);
    }

    public String getText(By by){
        return driver.findElement(by).getText();
    }

    public void waitForElement(By by, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));        
    }

    public void scrollIntoView(WebElement element){
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }
    
    public void switchToTab(int tabIndex){
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabIndex));
    }
}
