package waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiters {

    private WebDriver driver;
    private int timeoutSec = 5;

    public Waiters(WebDriver driver) {
        this.driver = driver;
    }
    public Waiters(WebDriver driver, int timeoutSec) {
        this.driver = driver;
        this.timeoutSec = timeoutSec;
    }

    public boolean waitForCondition(ExpectedCondition condition) {
        WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(timeoutSec));
        try {
            webDriverWait.until(condition);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean waitForElementVisible(By locator) {
        return waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}