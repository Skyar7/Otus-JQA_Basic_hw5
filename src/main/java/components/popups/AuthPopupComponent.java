package components.popups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;

public class AuthPopupComponent extends AbsPageObject {
    private final String LOGIN = System.getProperty("email");
    private final String PASSWORD = System.getProperty("password");

    public AuthPopupComponent(WebDriver driver) {
        super(driver);
    }

    public void loginDataEnter() {
        String emailSelector = "input[name='email']";
        String passwordSelector = "input[type='password']";

        driver.findElement(By.xpath("//div[@class='sc-rq8xzv-1 hGvqzc sc-11ptd2v-1 liHMCp']")).click();
        waiters.waitForElementVisible(By.cssSelector(emailSelector));
        driver.findElement(By.cssSelector(emailSelector)).sendKeys(this.LOGIN);

        driver.findElement(By.xpath("//div[@class='sc-rq8xzv-1 hGvqzc sc-11ptd2v-1-Component ciraFX']")).click();
        waiters.waitForElementVisible(By.cssSelector(passwordSelector));
        driver.findElement(By.cssSelector(passwordSelector)).sendKeys(this.PASSWORD);

        driver.findElement(By.cssSelector(".sc-9a4spb-0.gYNtqF.sc-11ptd2v-2-Component.cElCrZ")).click();
    }
}