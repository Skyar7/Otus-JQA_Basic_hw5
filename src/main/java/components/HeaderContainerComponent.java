package components;

import components.popups.AuthPopupComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobject.AbsPageObject;

public class HeaderContainerComponent extends AbsPageObject {

    public HeaderContainerComponent(WebDriver driver) {
        super(driver);
    }

    public void useLoginButton () {

        String loginButtonLocator = "//button[contains(text(),'Войти')]";
        waiters.waitForElementVisible(By.cssSelector(loginButtonLocator));
        driver.findElement(By.xpath(loginButtonLocator)).click();
    }

    public void goToPersonalPage() {

        String headNameSelector = ".sc-199a3eq-0.fJMWHf";
        waiters.waitForElementVisible(By.cssSelector(headNameSelector));
        WebElement headName = driver.findElement(By.cssSelector(headNameSelector));
        moveToElement(headName);

        WebElement popupMyProfile = driver.findElement(By.xpath("//a[contains(text(), 'Мой профиль')]"));
        moveAndClick(popupMyProfile);
    }
}
