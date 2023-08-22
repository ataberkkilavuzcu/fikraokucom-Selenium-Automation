package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;

public class MainPage extends BasePage{

    public static boolean continueNextCat = true;
    private By categoriesSize = By.xpath("//body/aside[1]/ul[1]/li");
    private static boolean continueApp = true;
    

    public MainPage() throws InterruptedException {
        super();
        initializeApp();
    }

    public void clickCategory() throws InterruptedException {
        CategoryPage categoryPage = new CategoryPage();
        int size = driver.findElements(categoriesSize).size();
        for(int i = 1 ; i < size; i++){
            By allCategories = By.xpath("//body/aside[1]/ul[1]/li["+i+"]/a");
            waitForElement(allCategories, 1000);
            WebElement elementCat = driver.findElement(allCategories);
            click(elementCat);
            Thread.sleep(5000);
            categoryPage.goToNextPage();
            if(i == size - 1){
                continueApp = false;
            }
        }
    }

    private void initializeApp() throws InterruptedException, TimeoutException{
        launch("https://www.xn--fkraoku-rfb.com/");
        while(continueApp){
             clickCategory();       
        }
    }

}
