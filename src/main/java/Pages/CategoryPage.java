package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;

import DataStore.ExcelWriting;

import java.time.Duration;
import java.util.*;

public class CategoryPage extends BasePage{


    // next button
    private By nextButton = By.xpath("//a[normalize-space() and contains(text(),'Sonraki')]");

    // to find index of last page to stop loop
    private By lastPage = By.xpath("//div[@class='pagination']//a[last()-1]");

    // titles of jokes 
    private By titles = By.xpath("//body//article//div//h3//a");
    
    // text of jokes
    private By jokes = By.xpath("//body//article//div//p[contains(text(),' ')]");

    private By categoryName = By.xpath("(//h2[contains(text(),'Fıkra')])[1]");

    // To check we are at last page or not
    private By atLastPage = By.xpath("//span[@class='disabled']");

    ExcelWriting excelWriting;
    

    public CategoryPage(){
        super();
        excelWriting = new ExcelWriting();
    }

    public String getCategoryName(){
        return getText(categoryName);
    }

    public int jokeSizePerPage(){
        // according to the format of website every page has at most 9 jokes
        int lastPageIndex = driver.findElements(titles).size();
        return lastPageIndex;
    }

    public void goToNextPage() throws InterruptedException{
        for(int i = 0; i < 1000; i++){
            int jokePerPage = jokeSizePerPage();
            boolean enterCheckLoop = true;
            if(jokePerPage < 9){
                i = 1000;
            List<String> storeTitles = getTitles();
            List<String> storeText = getJokeText();
            for(int j = 0; j < jokePerPage; j++){
                excelWriting.writeExcel("Jokes", ExcelWriting.rowI , 0, getCategoryName());
                excelWriting.writeExcel("Jokes", ExcelWriting.rowI , 1, storeTitles.get(j));
                excelWriting.writeExcel("Jokes", ExcelWriting.rowI++ , 2, storeText.get(j));
            }
            break;
            }
                 
            // if it has less than 9 jokes that means that category has only 1 page
            
            String checkLoop = driver.findElement(atLastPage).getText();
            if(!checkLoop.contains("nceki")){
                break;
            }
                
            

            WebElement element = driver.findElement(nextButton);
            List<String> storeTitles = getTitles();
            List<String> storeText = getJokeText();
            for(int j = 0; j < jokePerPage; j++){
                excelWriting.writeExcel("Jokes", ExcelWriting.rowI , 0, getCategoryName());
                excelWriting.writeExcel("Jokes", ExcelWriting.rowI , 1, storeTitles.get(j));
                excelWriting.writeExcel("Jokes", ExcelWriting.rowI++ , 2, storeText.get(j));
            }
            waitForElement(nextButton, 10000);
            click(element);
        }
    }

    public List<String> getTitles(){
        List<WebElement> allTitles = driver.findElements(titles);
        List<String> storeTitles = new ArrayList<String>();
        for(int i = 0; i < allTitles.size(); i++){
            storeTitles.add(allTitles.get(i).getText());
        }
        return storeTitles;
    }

    public List<String> getJokeText(){
        List<WebElement> allText = driver.findElements(jokes);
        List<String> storeText = new ArrayList<String>();
        for(int i = 0; i < allText.size(); i++){
            storeText.add(allText.get(i).getText());
        }
        return storeText;
    }

}
