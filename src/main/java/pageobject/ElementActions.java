package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class ElementActions {
    protected WebDriver driver;
    protected Actions actions;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public WebElement $(By by) {
        return driver.findElement(by);
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
