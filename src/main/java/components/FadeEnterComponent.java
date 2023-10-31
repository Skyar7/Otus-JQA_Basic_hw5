package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;

public class FadeEnterComponent extends AbsPageObject {
    private final String LOGIN = System.getProperty("email");
    private final String PASSWORD = System.getProperty("password");

    public FadeEnterComponent(WebDriver driver) {
        super(driver);
    }

    public void loginDataEnter() {
        driver.findElement(By.xpath("//div[@class='sc-rq8xzv-1 hGvqzc sc-11ptd2v-1 liHMCp']")).click();
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(this.LOGIN);
        driver.findElement(By.xpath("//div[@class='sc-rq8xzv-1 hGvqzc sc-11ptd2v-1-Component ciraFX']")).click();
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(this.PASSWORD);
        driver.findElement(By.cssSelector(".sc-9a4spb-0.gYNtqF.sc-11ptd2v-2-Component.cElCrZ")).click();
    }
}
