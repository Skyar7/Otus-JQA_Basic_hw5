package ui;

import com.github.javafaker.Faker;
import data.ICityData;
import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.AbsBasePage;
import pages.MainPage;
import pages.PersonalPage;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Biography_Test {
    private WebDriver driver;
    private Logger log = LogManager.getLogger(Biography_Test.class);

    @BeforeAll
public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void startDriver() {
        this.driver = new WebDriverFactory().create();
    }

//    @AfterEach
//    public void shutdownDriver() {
//        if (this.driver != null) {
//            this.driver.close();
//            this.driver.quit();
//        }
//    }
//
//    //ENUMS city-country
//    public void selectCity(ICityData cityData) {
//    //WebElement blablabl
//        String locatorCountry = String.format("//div[@title='$s']", cityData.getCountryData().getName());
//        //select
//
//        String locatorCity = String.format("//div[@title='$s']", cityData.getName());
//        //select
//
//        //Assertions
//
//    }
//
//
//    //LocalDate
//    private void checkDate(LocalDate expectedDate){
//        Assertions.assertEquals(expectedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.of("ru"))), element.getText());
//    }

    @Test
    public void personalTest() {
        MainPage mainPage = new MainPage(driver);
        PersonalPage personalPage = new PersonalPage(driver);

        log.info("Открытие сайта");
        mainPage.open("/");

        log.info("Авторизация на сайте");
        mainPage.login();

        log.info("Вход в личный кабинет");
        mainPage.goToPersonalPage();

        log.info("Заполнение данных");
        personalPage.fillData();
//
//        log.info("Сохранение");
//
//        log.info("Открытие чистого браузера");
//
//        log.info("Авторизация на сайте");
//
//        log.info("Вход в личный кабинет");
//
//        log.info("Проверка данных");
//
//
//
//
//
//        //Faker
//        List<String> elements = new ArrayList<>();
//        elements.add ("1");
//        elements.add ("2");
//        elements.add ("3");
//
//        Faker faker = new Faker();
//        String workFormats = faker.options().option("Полный день", "Частичный день", "Удалёнка");
//        String fakerList = faker.options().nextElement(elements);
//
//        //LocalDate
//        String birthDate = LocalDate.of(1990, Month.OCTOBER, 12).format(DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.of("ru")));
//
//
//
////        WebElement userName = driver.findElement(By.cssSelector("input[name='email']"));
////        userName.sendKeys(this.LOGIN);
////
////        WebElement userPassword = driver.findElement(By.cssSelector("input[type='password']"));
////        userPassword.sendKeys(this.PASSWORD);
//
////        log.info(driver.manage().getCookies());
    }
}