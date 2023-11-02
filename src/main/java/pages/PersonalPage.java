package pages;

import com.ibm.icu.text.Transliterator;
import data.ContactMethodData;
import data.EnglishLvlData;
import data.cities.ICityData;
import data.cities.CitiesData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.Waiters;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PersonalPage extends AbsBasePage {

    private String pagePath = "/lk/biography/personal/";

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
    private WebElement readyToRelYesInput = null;
    private WebElement scheduleFulldayInput = null;
    private WebElement scheduleAgileInput = null;
    private WebElement scheduleRemoteInput = null;

    // Контактная информация.
    private ContactMethodData contactMethodData1 = null;
    private ContactMethodData contactMethodData2 = null;
    private String contactMethodValue1 = "";
    private String contactMethodValue2 = "";

    // Другое.

    // Опыт разработки.


    public PersonalPage(WebDriver driver) {
        super(driver);
    }

    public String getPagePath() {
        return pagePath;
    }

    public void fillData() {

        // Имя, фамилия, дата рождения.
        Transliterator toCyrillicTrans = Transliterator.getInstance("Latin-Russian/BGN");
        this.firstNameLatin = faker.name().firstName();
        this.lastNameLatin = faker.name().lastName();
        this.firstName = toCyrillicTrans.transliterate(firstNameLatin);
        this.lastName = toCyrillicTrans.transliterate(lastNameLatin);
        this.blogName = faker.harryPotter().spell();
        this.birthDate = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        clearAndSend(this.fNameField = driver.findElement(By.id("id_fname")), firstName);
        clearAndSend(this.lNameField = driver.findElement(By.id("id_lname")), lastName);
        clearAndSend(this.fNameLatinField = driver.findElement(By.id("id_fname_latin")), firstNameLatin);
        clearAndSend(this.lNameLatinField = driver.findElement(By.id("id_lname_latin")), lastNameLatin);
        clearAndSend(this.blogNameField = driver.findElement(By.id("id_blog_name")), blogName);
        clearAndSend(this.dateOfBirthField = driver.findElement(By.name("date_of_birth")), birthDate);

        // Основная информация.

        // Выбор страны и города.
        this.cityData = CitiesData.values()[faker.random().nextInt(CitiesData.values().length)];
        this.selectCity(cityData);

        // Выбор уровня английского.
        this.englishLvlData = EnglishLvlData.values()[faker.random().nextInt(EnglishLvlData.values().length)];
        this.selectEnglishLvl(englishLvlData);

        // Выбор готовности к переезду.
        this.readyToRelYesInput = driver.findElement(By.cssSelector("#id_ready_to_relocate_1"));

        if (readyToRelYesInput.isSelected()) {
            driver.findElement(By.xpath("//span[contains(text(), 'Нет')]")).click();
            this.readyToMoveFlag = false;
        } else {
            driver.findElement(By.xpath("//span[contains(text(), 'Да')]")).click();
            this.readyToMoveFlag = true;
        }

        // Выбор формата работы.
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

        // Контактная информация.

        // Выбор способа связи.
        this.contactMethodData1 = ContactMethodData.values()[faker.random().nextInt(EnglishLvlData.values().length)];
        this.contactMethodValue1 = faker.internet().emailAddress();
        this.addContactMethod(contactMethodData1, contactMethodValue1);

        this.contactMethodData2 = ContactMethodData.values()[faker.random().nextInt(EnglishLvlData.values().length)];
        this.contactMethodValue2 = faker.phoneNumber().cellPhone();
        this.addContactMethod(contactMethodData2, contactMethodValue2);

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

    private void addContactMethod(ContactMethodData contactMethodData, String contactFieldValue) {
        try{
            driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        } catch (NoSuchElementException e) {
            driver.findElement(By.xpath("//button[contains(text(),'Добавить')]")).click();
            driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        }

        String contactMethodDropdownLocator = String.format("(//button[@data-value='%s'])[last()]", contactMethodData.getName());
        driver.findElement(By.xpath(contactMethodDropdownLocator)).click();

        WebElement contactField = driver.findElement(By.xpath("(//input[@class ='input input_straight-top-left input_straight-bottom-left lk-cv-block__input lk-cv-block__input_9 lk-cv-block__input_md-8'])[last()]"));
        clearAndSend(contactField, contactFieldValue);
    }
}