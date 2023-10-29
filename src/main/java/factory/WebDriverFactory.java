package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    private final String BROWSER_NAME = System.getProperty("browser.name", "chrome");

    public WebDriver create() {
        switch(BROWSER_NAME) {
            case "chrome": {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                return new ChromeDriver(chromeOptions);
            }
        }
        return null;
    }
}