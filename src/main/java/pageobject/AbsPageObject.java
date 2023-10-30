package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public abstract class AbsPageObject {

    protected WebDriver driver;
    protected Actions actions;

    public AbsPageObject (WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }
}
