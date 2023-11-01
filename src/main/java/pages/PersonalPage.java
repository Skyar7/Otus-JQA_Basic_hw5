package pages;

import data.ICityData;
import data.RussianCityData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.Waiters;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class PersonalPage extends AbsBasePage {

    private String firstName = "";
    private String lastName = "";
    private String firstNameLatin = "";
    private String lastNameLatin = "";
    private String blogName = "";
    private String birthDate = "";
    private WebElement fNameField = null;
    private WebElement lNameField = null;
    private WebElement fNameLatinField = null;
    private WebElement lNameLatinField = null;
    private WebElement blogNameField = null;
    private WebElement dateOfBirthField = null;
    private WebElement countryField = null;
    private WebElement cityField = null;

    ICityData cityData = RussianCityData.MOSCOW;

    public PersonalPage(WebDriver driver) {
        super(driver);
    }

    public void fillData() {

        // Имя, фамилия, дата рождения.
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.firstNameLatin = firstName;
        this.lastNameLatin = lastName;
        this.blogName = firstName + " " + lastName;
        this.birthDate = LocalDate.of(1990, Month.OCTOBER, 12).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        clearAndSend(this.fNameField = driver.findElement(By.id("id_fname")), firstName); ;
        clearAndSend(this.lNameField = driver.findElement(By.id("id_lname")), lastName);
        clearAndSend(this.fNameLatinField = driver.findElement(By.id("id_fname_latin")), firstNameLatin);
        clearAndSend(this.lNameLatinField = driver.findElement(By.id("id_lname_latin")), lastNameLatin);
        clearAndSend(this.blogNameField = driver.findElement(By.id("id_blog_name")), blogName);
        clearAndSend(this.dateOfBirthField = driver.findElement(By.name("date_of_birth")), birthDate);

        // Основная информация.

        this.selectCity(cityData);





//
//
//    //LocalDateAssertion
//    private void checkDate(LocalDate expectedDate){
//        Assertions.assertEquals(expectedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.of("ru"))), element.getText());
//    }
    }
    public void selectCity(ICityData cityData) {

        this.countryField = driver.findElement(By.xpath("//input[@name= 'country']/following::div[1]"));
        countryField.click();
        String countryDropdownSelector = String.format("button[title='%s']", cityData.getCountryData().getName());
        driver.findElement(By.cssSelector(countryDropdownSelector)).click();

        String cityFieldLocator = "//input[@data-title= 'Город']/following::div[1]";
        Waiters cityWaiters = new Waiters(driver, 1);
        cityWaiters.waitForCondition(ExpectedConditions.attributeToBe(By.xpath(cityFieldLocator), "disabled", "disabled"));
        cityWaiters.waitForCondition(ExpectedConditions.not(ExpectedConditions.attributeToBe(By.xpath(cityFieldLocator), "disabled", "disabled")));
        this.cityField = driver.findElement(By.xpath(cityFieldLocator));
        cityField.click();
        String cityDropdownSelector = String.format("button[title='%s']", cityData.getName());
        waiters.waitForElementVisible(By.cssSelector(cityDropdownSelector));
        driver.findElement(By.cssSelector(cityDropdownSelector)).click();
    }
}