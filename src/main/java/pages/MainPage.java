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
        driver.findElement(By.xpath("//button[contains(text(),'Войти')]")).click();

        driver.findElement(By.xpath("//div[@class='sc-rq8xzv-1 hGvqzc sc-11ptd2v-1 liHMCp']")).click();
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(this.LOGIN);
        driver.findElement(By.xpath("//div[@class='sc-rq8xzv-1 hGvqzc sc-11ptd2v-1-Component ciraFX']")).click();
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(this.PASSWORD);

        driver.findElement(By.cssSelector(".sc-9a4spb-0.gYNtqF.sc-11ptd2v-2-Component.cElCrZ")).click();
    }

    public void goToPersonalPage() {

        String headNameSelector = ".sc-199a3eq-0.fJMWHf";
        waiter.waitForElementVisible(By.cssSelector(headNameSelector));
        WebElement headName = driver.findElement(By.cssSelector(headNameSelector));
        moveToElement(headName);

        WebElement popupMyProfile = driver.findElement(By.xpath("//a[contains(text(), 'Мой профиль')]"));
        moveAndClick(popupMyProfile);
    }
}