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
    private MainPage mainPage;
    private PersonalPage personalPage;
    private HeaderContainerComponent headerContainerComponent;

    @BeforeEach
    public void startDriver() {
        this.driver = new WebDriverFactory().newDriver();
        mainPage = new MainPage(driver);
        personalPage = new PersonalPage(driver);
    }

    @AfterEach
    public void shutdownDriver() {
        if (this.driver != null) {
            this.driver.close();
            this.driver.quit();
        }
    }

    @Test
    public void personalTest() {

        log.info("Открытие сайта");
        mainPage.open(mainPage.getPagePath());

        log.info("Авторизация на сайте");
        this.login();

        log.info("Вход в личный кабинет");
        headerContainerComponent.goToPersonalPage();

        log.info("Заполнение данных");
        personalPage.fillData();

        log.info("Сохранение");
        personalPage.saveData();

        log.info("Открытие https://otus.ru в \"чистом браузере\".");
        shutdownDriver();
        startDriver();
        mainPage.open(mainPage.getPagePath());

        log.info("Авторизация на сайте");
        this.login();

        log.info("Вход в личный кабинет");
        headerContainerComponent.goToPersonalPage();

        log.info("Проверка данных");
        personalPage.checkData();
    }

    public void login() {
        AuthPopupComponent authPopupComponent = new AuthPopupComponent(driver);
        authPopupComponent.popupShouldNotBeVisible();

        headerContainerComponent = new HeaderContainerComponent(driver);
        headerContainerComponent.useLoginButton();

        authPopupComponent.popupShouldBeVisible();
        authPopupComponent.loginDataEnter();
    }
}