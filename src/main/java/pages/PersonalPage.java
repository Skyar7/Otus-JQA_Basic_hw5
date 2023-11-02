package pages;

import data.EnglishLvlData;
import data.cities.ICityData;
import data.cities.CitiesData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.Waiters;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PersonalPage extends AbsBasePage {

    // Имя, фамилия, дата рождения.
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

    // Основная информация.
    private WebElement countryField = null;
    private WebElement cityField = null;
    private WebElement englishLvlField = null;
    private ICityData cityData = null;
    private EnglishLvlData englishLvlData = null;
    private boolean readyToMoveFlag;
    private boolean scheduleFulldayFlag;
    private boolean scheduleAgileFlag;
    private boolean scheduleRemoteFlag;
    WebElement readyToRelYesInput = null;
    WebElement scheduleFulldayInput = null;
    WebElement scheduleAgileInput = null;
    WebElement scheduleRemoteInput = null;

    // Контактная информация.

    // Другое.

    // Опыт разработки.


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
        this.birthDate = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        clearAndSend(this.fNameField = driver.findElement(By.id("id_fname")), firstName);
        clearAndSend(this.lNameField = driver.findElement(By.id("id_lname")), lastName);
        clearAndSend(this.fNameLatinField = driver.findElement(By.id("id_fname_latin")), firstNameLatin);
        clearAndSend(this.lNameLatinField = driver.findElement(By.id("id_lname_latin")), lastNameLatin);
        clearAndSend(this.blogNameField = driver.findElement(By.id("id_blog_name")), blogName);
        clearAndSend(this.dateOfBirthField = driver.findElement(By.name("date_of_birth")), birthDate);

        // Основная информация.
        cityData = CitiesData.values()[faker.random().nextInt(CitiesData.values().length)];
        this.selectCity(cityData);

        englishLvlData = EnglishLvlData.values()[faker.random().nextInt(EnglishLvlData.values().length)];
        this.selectEnglishLvl(englishLvlData);


        this.readyToRelYesInput = driver.findElement(By.cssSelector("#id_ready_to_relocate_1"));

        if (readyToRelYesInput.isSelected()) {
            driver.findElement(By.xpath("//span[contains(text(), 'Нет')]")).click();
            this.readyToMoveFlag = false;
        } else {
            driver.findElement(By.xpath("//span[contains(text(), 'Да')]")).click();
            this.readyToMoveFlag = true;
        }

        this.scheduleFulldayInput = driver.findElement(By.xpath("//input[@title = 'Полный день']"));
        this.scheduleAgileInput = driver.findElement(By.xpath("//input[@title = 'Гибкий график']"));
        this.scheduleRemoteInput = driver.findElement(By.xpath("//input[@title = 'Удаленно']"));

        scheduleFulldayFlag = scheduleFulldayInput.isSelected();
        scheduleAgileFlag = scheduleAgileInput.isSelected();
        scheduleRemoteFlag = scheduleRemoteInput.isSelected();

        driver.findElement(By.xpath("//span[contains(text(), 'Полный день')]")).click();
        scheduleFulldayFlag = !scheduleFulldayFlag;

        driver.findElement(By.xpath("//span[contains(text(), 'Гибкий график')]")).click();
        scheduleAgileFlag = !scheduleAgileFlag;

        driver.findElement(By.xpath("//span[contains(text(), 'Удаленно')]")).click();
        scheduleRemoteFlag = !scheduleRemoteFlag;

//    //LocalDateAssertion
//    private void checkDate(LocalDate expectedDate){
//        Assertions.assertEquals(expectedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.of("ru"))), element.getText());
//    }
    }
    private void selectCity(ICityData cityData) {
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

    private void selectEnglishLvl(EnglishLvlData englishLvlData) {
        this.englishLvlField = driver.findElement(By.cssSelector("div[class='select lk-cv-block__input lk-cv-block__input_full js-lk-cv-custom-select']"));
        englishLvlField.click();
        String englishLvlDropdownLocator = String.format("//button[contains(@title,'%s')]", englishLvlData.getName());
        driver.findElement(By.xpath(englishLvlDropdownLocator)).click();
    }
}