package waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {

    private WebDriver driver;
    public Waiter(WebDriver driver) {
        this.driver = driver;
    }

    public boolean waitForCondition(ExpectedCondition condition) {
        WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        try {
            webDriverWait.until(condition);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean waitForElementVisible(WebElement element) {
        return waitForCondition(ExpectedConditions.visibilityOf(element));
    }
    public boolean waitForElementVisible(By locator) {
        return waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean waitForElementToBeClickable(WebElement element) {
        return waitForCondition(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitForElementToBeClickable(By locator) {
        return waitForCondition(ExpectedConditions.elementToBeClickable(locator));
    }
//    public boolean waitForElementNotVisible(WebElement element) {
//        return waitForCondition(ExpectedConditions.invisibilityOf(element));
//    }
}