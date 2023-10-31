package pageobject;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import waiters.Waiters;

public abstract class AbsPageObject {

    protected WebDriver driver;
    protected Actions actions;
    protected Waiters waiters;
    protected Faker faker;

    public AbsPageObject (WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waiters = new Waiters(driver);
        this.faker = new Faker();
    }

    public void clearAndSend(WebElement element, String field) {
        element.clear();
        element.sendKeys(field);
    }

    public void moveToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void moveAndClick(WebElement element) {
        actions.moveToElement(element);
        element.click();
    }
}