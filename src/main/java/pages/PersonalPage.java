package pages;

import com.ibm.icu.text.Transliterator;
import data.ContactMethodData;
import data.EnglishLvlData;
import data.GenderData;
import data.cities.ICityData;
import data.cities.CitiesData;
import data.dev_experience.DevExpValuesData;
import data.dev_experience.TechStackData;
import org.junit.jupiter.api.Assertions;
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

    // ИМЯ, ФАМИЛИЯ, ДАТА РОЖДЕНИЯ.
    private static String firstName = "";
    private static String lastName = "";
    private static String firstNameLatin = "";
    private static String lastNameLatin = "";
    private static String blogName = "";
    private static String birthDate = "";
    private String fNameFieldId = "id_fname";
    private String lNameFieldId = "id_lname";
    private String fNameLatinFieldId = "id_fname_latin";
    private String lNameLatinFieldId = "id_lname_latin";
    private String blogNameFieldId = "id_blog_name";
    private String dateOfBirthFieldName = "date_of_birth";

    // ОСНОВНАЯ ИНФОРМАЦИЯ.
    private ICityData cityData = null;
    private WebElement countryField = null;
    private WebElement cityField = null;
    private EnglishLvlData englishLvlData = null;
    private WebElement englishLvlField = null;
    private boolean readyToMoveFlag;
    private boolean scheduleFulldayFlag;
    private boolean scheduleAgileFlag;
    private boolean scheduleRemoteFlag;
    private WebElement readyToRelYesInput = null;
    private WebElement scheduleFulldayInput = null;
    private WebElement scheduleAgileInput = null;
    private WebElement scheduleRemoteInput = null;

    // КОНТАКТНАЯ ИНФОРМАЦИЯ.
    private ContactMethodData contactMethodData1 = null;
    private ContactMethodData contactMethodData2 = null;
    private String contactMethodValue1 = "";
    private String contactMethodValue2 = "";

    // ДРУГОЕ.
    private GenderData genderData = null;
    private String company = "";
    private String jobtitle = "";
    private WebElement companyField = null;
    private WebElement jobtitleField = null;

    // ОПЫТ РАЗРАБОТКИ.
    private TechStackData techStackData = null;
    private WebElement techStackField = null;
    private DevExpValuesData devExpValueData = null;
    private WebElement devExpValueField = null;

    public PersonalPage(WebDriver driver) {
        super(driver);
    }

    public String getPagePath() {
        return pagePath;
    }

    public void fillData() {

        // ИМЯ, ФАМИЛИЯ, ДАТА РОЖДЕНИЯ.

        Transliterator toCyrillicTrans = Transliterator.getInstance("Latin-Russian/BGN");
        this.firstNameLatin = faker.name().firstName();
        this.lastNameLatin = faker.name().lastName();
        this.firstName = toCyrillicTrans.transliterate(firstNameLatin);
        this.lastName = toCyrillicTrans.transliterate(lastNameLatin);
        this.blogName = firstNameLatin + " " + faker.harryPotter().spell();
        this.birthDate = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        clearAndSend(driver.findElement(By.id(fNameFieldId)), firstName);
        clearAndSend(driver.findElement(By.id(lNameFieldId)), lastName);
        clearAndSend(driver.findElement(By.id(fNameLatinFieldId)), firstNameLatin);
        clearAndSend(driver.findElement(By.id(lNameLatinFieldId)), lastNameLatin);
        clearAndSend(driver.findElement(By.id(blogNameFieldId)), blogName);
        clearAndSend(driver.findElement(By.name(dateOfBirthFieldName)), birthDate);

        // ОСНОВНАЯ ИНФОРМАЦИЯ.

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

        // КОНТАКТНАЯ ИНФОРМАЦИЯ.

        this.contactMethodData1 = ContactMethodData.values()[faker.random().nextInt(ContactMethodData.values().length)];
        this.contactMethodValue1 = faker.internet().emailAddress();
        this.addContactMethod(contactMethodData1, contactMethodValue1);

        this.contactMethodData2 = ContactMethodData.values()[faker.random().nextInt(ContactMethodData.values().length)];
        this.contactMethodValue2 = faker.phoneNumber().cellPhone();
        this.addContactMethod(contactMethodData2, contactMethodValue2);

        // ДРУГОЕ.

        // Выбор пола.
        this.genderData = GenderData.values()[faker.random().nextInt(GenderData.values().length)];
        this.selectGender(genderData);

        // Компания.
        this.companyField = driver.findElement(By.id("id_company"));
        company = faker.company().name();
        clearAndSend(companyField, company);

        // Должность.
        this.jobtitleField = driver.findElement(By.id("id_work"));
        jobtitle = faker.company().profession();
        clearAndSend(jobtitleField, jobtitle);

        // ОПЫТ РАЗРАБОТКИ.

        this.techStackData = TechStackData.values()[faker.random().nextInt(TechStackData.values().length)];
        this.devExpValueData = DevExpValuesData.values()[faker.random().nextInt(DevExpValuesData.values().length)];
        this.selectDevExp(techStackData, devExpValueData);
    }

    public void saveData () {
        driver.findElement(By.cssSelector("button[title='Сохранить и заполнить позже']")).click();
    }

    public void checkData () {

        // ИМЯ, ФАМИЛИЯ, ДАТА РОЖДЕНИЯ.

        Assertions.assertEquals(firstName, driver.findElement(By.id(fNameFieldId)).getAttribute("value"));
        Assertions.assertEquals(lastName, driver.findElement(By.id(lNameFieldId)).getAttribute("value"));
        Assertions.assertEquals(firstNameLatin, driver.findElement(By.id(fNameLatinFieldId)).getAttribute("value"));
        Assertions.assertEquals(lastNameLatin, driver.findElement(By.id(lNameLatinFieldId)).getAttribute("value"));
        Assertions.assertEquals(blogName, driver.findElement(By.id(blogNameFieldId)).getAttribute("value"));
        Assertions.assertEquals(birthDate, driver.findElement(By.name(dateOfBirthFieldName)).getAttribute("value"));

        // ОСНОВНАЯ ИНФОРМАЦИЯ.

        // КОНТАКТНАЯ ИНФОРМАЦИЯ.

        // ДРУГОЕ.

        // ОПЫТ РАЗРАБОТКИ.
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

    private void selectGender(GenderData genderData) {
        driver.findElement(By.id("id_gender")).click();
        String genderDropdownLocator = String.format("//option[contains(@value, '%s')]", genderData.getValue());
        driver.findElement(By.xpath(genderDropdownLocator)).click();
    }

    private void selectDevExp(TechStackData techStackData, DevExpValuesData devExpValuesData) {
        driver.findElement(By.cssSelector("a[title='Добавить']")).click();

        //Выбор технологии.
        this.techStackField = driver.findElement(By.id("id_experience-0-experience"));
        techStackField.click();
        String techStackDropdownLocator = String.format("//option[contains(@value, '%s')]", techStackData.getName());
        driver.findElement(By.xpath(techStackDropdownLocator)).click();

        // Выбор опыта.
        this.devExpValueField = driver.findElement(By.id("id_experience-0-level"));
        devExpValueField.click();
        String devExpDropdownLocator = String.format("//option[contains(text(), '%s')]", devExpValuesData.getName());
        driver.findElement(By.xpath(devExpDropdownLocator)).click();
    }
}