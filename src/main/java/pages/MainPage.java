package pages;

import org.openqa.selenium.WebDriver;

public class MainPage extends AbsBasePage {
    private String path = "/";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + this.path);
    }
}