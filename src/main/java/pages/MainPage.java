package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import waiters.Waiter;

public class MainPage extends AbsBasePage {
    private final String LOGIN = System.getProperty("email");
    private final String PASSWORD = System.getProperty("password");

    Waiter waiter = new Waiter(driver);
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void login() {

        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Войти')]"));
        loginButton.click();

        driver.findElement(By.xpath("//div[@class='sc-rq8xzv-1 hGvqzc sc-11ptd2v-1 liHMCp']")).click();
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(this.LOGIN);
        driver.findElement(By.xpath("//div[@class='sc-rq8xzv-1 hGvqzc sc-11ptd2v-1-Component ciraFX']")).click();
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(this.PASSWORD);

        WebElement loginConfirmButton = driver.findElement(By.cssSelector(".sc-9a4spb-0.gYNtqF.sc-11ptd2v-2-Component.cElCrZ"));
        loginConfirmButton.click();
    }

    public void goToPersonalPage() {

    }
}