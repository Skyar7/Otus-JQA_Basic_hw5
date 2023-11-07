package components.popups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class AuthPopupComponent extends AbsPageObject implements IPopups {
    private final String LOGIN = System.getProperty("email");
    private final String PASSWORD = System.getProperty("password");
    private String authPopupSelector = "#__PORTAL__ > div";

    public AuthPopupComponent(WebDriver driver) {
        super(driver);
    }

    @Override
    public void popupShouldBeVisible() {
        assertThat(standardWaiters.waitForElementVisible(By.cssSelector(authPopupSelector)))
            .as("Auth popup should be visible on page")
            .isTrue();

    }

    @Override
    public void popupShouldNotBeVisible() {
        assertThat(standardWaiters.waitForElementNotvisible(By.cssSelector(authPopupSelector)))
            .as("Auth popup should not be visible on page")
            .isTrue();
    }

    public void loginDataEnter() {
        String emailSelector = "input[name='email']";
        String passwordSelector = "input[type='password']";

        $(By.xpath("//div[@class='sc-rq8xzv-1 hGvqzc sc-11ptd2v-1 liHMCp']")).click();
        standardWaiters.waitForElementVisible(By.cssSelector(emailSelector));
        $(By.cssSelector(emailSelector)).sendKeys(this.LOGIN);

        $(By.xpath("//div[@class='sc-rq8xzv-1 hGvqzc sc-11ptd2v-1-Component ciraFX']")).click();
        standardWaiters.waitForElementVisible(By.cssSelector(passwordSelector));
        $(By.cssSelector(passwordSelector)).sendKeys(this.PASSWORD);

        $(By.cssSelector(".sc-9a4spb-0.gYNtqF.sc-11ptd2v-2-Component.cElCrZ")).click();
    }
}