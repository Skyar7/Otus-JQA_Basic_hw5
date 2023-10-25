package ui;

import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Maximized_Test {
    private final String LOGIN = System.getProperty("email");
    private final String PASSWORD = System.getProperty("password");
    private WebDriver driver;
    private Logger log = LogManager.getLogger(Maximized_Test.class);

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void startDriver() {
        this.driver = new WebDriverFactory().createMaximized();
    }

    @AfterEach
    public void shutdownDriver() {
        if (this.driver != null) {
            this.driver.close();
            this.driver.quit();
        }
    }

    @Test
    public void loginAndGetCookiesTest() {
        driver.get("https://otus.ru");

        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Войти')]"));
        loginButton.click();

        WebElement userName = driver.findElement(By.cssSelector("input[name='email']"));
        userName.sendKeys(this.LOGIN);

        WebElement userPassword = driver.findElement(By.cssSelector("input[type='password']"));
        userPassword.sendKeys(this.PASSWORD);

        WebElement loginConfirmButton = driver.findElement(By.cssSelector(".sc-9a4spb-0.gYNtqF.sc-11ptd2v-2-Component.cElCrZ"));
        loginConfirmButton.click();

        log.info(driver.manage().getCookies());
    }
}