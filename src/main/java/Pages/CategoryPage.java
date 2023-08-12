package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;

import java.util.*;

public class CategoryPage extends BasePage{

    // next button
    private By nextButton = By.xpath("//div[@class='pagination']//a[last()]");

    // to find index of last page to stop loop
    private By lastPage = By.xpath("//div[@class='pagination']//a[last()-1]");

    
    public CategoryPage(){
        super();
    }

    public int pageSize(){
        String lastIndex = getText(lastPage);
        int lastPageIndex = Integer.parseInt(lastIndex);
        return lastPageIndex;
    }

    public void goToNextPage(){
        for(int i = 0; i < pageSize(); i++){
            WebElement element = driver.findElement(nextButton);
            scrollIntoView(element);
            waitForElement(nextButton, 10);
            click(nextButton);
        }

    }

}
