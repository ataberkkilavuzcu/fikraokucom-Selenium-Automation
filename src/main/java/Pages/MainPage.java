package Pages;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.hssf.record.chart.CategorySeriesAxisRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import DataStore.ExcelWriting;

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
        for(int i = 10 ; i < size; i++){
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

    private void initializeApp() throws InterruptedException{
        launch("https://www.xn--fkraoku-rfb.com/");


        
        while(continueApp){
             clickCategory();       
        }
    }
    // public static void main(String[] args) throws InterruptedException {
    //       MainPage mainPage = new MainPage();
    //       mainPage.launch("https://www.xn--fkraoku-rfb.com/");
    //       CategoryPage categoryPage = new CategoryPage();
    //       mainPage.clickCategory();
    //     // // System.out.println(categoryPage.pageSize());
    //     // // categoryPage.getTitles();
    //       categoryPage.goToNextPage();

       
        
    // }
}
