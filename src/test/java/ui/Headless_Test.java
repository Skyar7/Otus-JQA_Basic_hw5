package ui;

import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.junit.jupiter.api.*;

public class Headless_Test {
    private WebDriver driver;

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void startDriver() {
        this.driver = new WebDriverFactory().createHeadless();
    }

    @AfterEach
    public void shutdownDriver() {
        if (this.driver != null) {
            this.driver.close();
            this.driver.quit();
        }
    }

    @Test
    public void searchTest() {
        driver.get("https://duckduckgo.com");

        WebElement searchBox = driver.findElement(By.cssSelector("#searchbox_input"));
        searchBox.clear();
        searchBox.sendKeys("ОТУС");

        WebElement searchButton = driver.findElement(By.cssSelector("button[aria-label='Search']"));
        searchButton.click();

        WebElement firstResult = driver.findElement(By.cssSelector("article[id='r1-0'] div[class='ikg2IXiCD14iVX7AdZo1']"));
        String actualTitle = firstResult.getText();
        String expectedTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным ...";

        Assertions.assertEquals(expectedTitle,actualTitle);
    }
}