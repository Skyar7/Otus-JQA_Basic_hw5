package components;

import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;

public class AbsBaseComponent extends AbsPageObject {

    public AbsBaseComponent(WebDriver driver) {
        super(driver);
    }
}
