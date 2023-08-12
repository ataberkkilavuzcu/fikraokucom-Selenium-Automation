package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage{

    private int indexCategories = 1;
    private By categories = By.xpath("//body//aside//ul//following-sibling::li["+indexCategories+"]");

    public MainPage() {
        super();
    }

    public List<String> categoriesList(){
        List<WebElement> category = new ArrayList<WebElement>();
        List<String> categoryTitles = new ArrayList<String>();
        int i = 0;
        for(;indexCategories < 34; indexCategories++){
            category = driver.findElements(categories);
            categoryTitles.add(category.get(i).getText());
            i++;
            waitForElement(categories, 5);
            click(categories);
            break;
        }
        for (String string : categoryTitles) {
            System.out.println(string);
        }
        return categoryTitles;
    }

    public static void main(String[] args) {
        MainPage mainPage = new MainPage();
        mainPage.launch("https://www.xn--fkraoku-rfb.com/");
        CategoryPage categoryPage = new CategoryPage();
        mainPage.categoriesList();
        System.out.println(categoryPage.pageSize());
        categoryPage.goToNextPage();
    }
}
