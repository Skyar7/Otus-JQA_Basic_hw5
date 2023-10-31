package pageobject;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class AbsPageObject {

    protected WebDriver driver;
    protected Actions actions;
    protected Faker faker = new Faker();

    public AbsPageObject (WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void clickElement(WebElement element) {
        element.click();
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
