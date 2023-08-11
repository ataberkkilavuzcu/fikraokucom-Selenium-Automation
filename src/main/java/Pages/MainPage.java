package Pages;


public class MainPage extends BasePage{

    public MainPage() {
        super();
    }
    public static void main(String[] args) {
        MainPage mainPage = new MainPage();
        mainPage.launch("https://www.xn--fkraoku-rfb.com/");
    }
}
