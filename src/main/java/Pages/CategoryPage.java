package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import DataStore.ExcelWriting;

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

    public void goToNextPage() throws InterruptedException {
        final int MAX_PAGES = 1000;
        final int MIN_JOKES_PER_PAGE = 9;
        
        for (int page = 0; page < MAX_PAGES; page++) {
            int jokesPerPage = jokeSizePerPage();
            
            if (jokesPerPage < MIN_JOKES_PER_PAGE) {
                break;
            }
            
            List<String> storeTitles = getTitles();
            List<String> storeText = getJokeText();
            
            writeToExcel(storeTitles, storeText);
            Thread.sleep(1000);
            
            if (!isNextPageAvailable()) {
                break;
            }
                    
            try {
                WebElement nextButtonElement = driver.findElement(nextButton);
                click(nextButtonElement);
                Thread.sleep(5000);
            } catch (NoSuchElementException e) {
                break;
            }      
        }
}

    private void writeToExcel(List<String> titles, List<String> texts) {
        for (int j = 0; j < titles.size(); j++) {
            excelWriting.writeExcel("Jokes", ExcelWriting.rowI, 0, getCategoryName());
            excelWriting.writeExcel("Jokes", ExcelWriting.rowI, 1, titles.get(j));
            excelWriting.writeExcel("Jokes", ExcelWriting.rowI++, 2, texts.get(j));
        }
    }

    private boolean isNextPageAvailable() {
        try {
            String nextPageText = driver.findElement(atLastPage).getText();
            return !nextPageText.contains("Sonraki");
        } catch (NoSuchElementException e) {
            return true;
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
