package ui;

import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Kiosk_Test {
    private WebDriver driver;

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void startDriver() {
        this.driver = new WebDriverFactory().createKiosk();
    }

    @AfterEach
    public void shutdownDriver() {
        if (this.driver != null) {
            this.driver.close();
            this.driver.quit();
        }
    }

    @Test
    public void picOnModalTest() {
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");

        WebElement picture = driver.findElement(By.cssSelector(".portfolio-item2.content[data-id='id-3']"));
        picture.click();

        WebElement pictureOnModal = driver.findElement(By.cssSelector(".pp_pic_holder.light_rounded"));
        pictureOnModal.isDisplayed();
    }
}