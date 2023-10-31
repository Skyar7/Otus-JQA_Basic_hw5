package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobject.AbsPageObject;

public class HeaderContainerComponent extends AbsPageObject {

    public HeaderContainerComponent(WebDriver driver) {
        super(driver);
    }

    public void useLoginButton () {
        driver.findElement(By.xpath("//button[contains(text(),'Войти')]")).click();
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
