package ui;

import components.popups.AuthPopupComponent;
import components.HeaderContainerComponent;
import driver.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.PersonalPage;

public class Biography_Test {
    private WebDriver driver;
    private Logger log = LogManager.getLogger(Biography_Test.class);

    @BeforeEach
    public void startDriver() {
        this.driver = new WebDriverFactory().newDriver();
    }

//    @AfterEach
//    public void shutdownDriver() {
//        if (this.driver != null) {
//            this.driver.close();
//            this.driver.quit();
//        }
//    }
//

    @Test
    public void personalTest() {
        MainPage mainPage = new MainPage(driver);
        PersonalPage personalPage = new PersonalPage(driver);
        HeaderContainerComponent headerContainerComponent = new HeaderContainerComponent(driver);

        log.info("Открытие сайта");
        mainPage.open(mainPage.getPagePath());

        log.info("Авторизация на сайте");
        this.login();

        log.info("Вход в личный кабинет");
        headerContainerComponent.goToPersonalPage();

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
    }
    public void login() {
        AuthPopupComponent authPopupComponent = new AuthPopupComponent(driver);
        HeaderContainerComponent headerContainerComponent = new HeaderContainerComponent(driver);

        headerContainerComponent.useLoginButton();
        authPopupComponent.loginDataEnter();
    }
}