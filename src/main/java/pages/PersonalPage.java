package pages;

import com.ibm.icu.text.Transliterator;
import data.ContactMethodsData;
import data.EnglishLvlsData;
import data.GendersData;
import data.cities.ICitiesData;
import data.cities.CitiesData;
import data.dev_experience.DevExpValuesData;
import data.dev_experience.TechStackData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.StandardWaiters;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;

public class PersonalPage extends AbsBasePage {

    private String pagePath = "/lk/biography/personal/";

    // ИМЯ, ФАМИЛИЯ, ДАТА РОЖДЕНИЯ.
    private static String firstName = "";
    private static String lastName = "";
    private static String firstNameLatin = "";
    private static String lastNameLatin = "";
    private static String blogName = "";
    private static String birthDate = "";
    @FindBy(id = "id_fname")
    private WebElement fNameField;
    @FindBy(id = "id_lname")
    private WebElement lNameField;
    @FindBy(id = "id_fname_latin")
    private WebElement fNameLatinField;
    @FindBy(id = "id_lname_latin")
    private WebElement lNameLatinField;
    @FindBy(id = "id_blog_name")
    private WebElement blogNameField;
    @FindBy(name = "date_of_birth")
    private WebElement dateOfBirthField;

    // ОСНОВНАЯ ИНФОРМАЦИЯ.
    private static ICitiesData cityData = null;
    private static EnglishLvlsData englishLvlsData = null;
    private static boolean readyToRelFlag;
    private static boolean scheduleFulldayFlag;
    private static boolean scheduleAgileFlag;
    private static boolean scheduleRemoteFlag;
    @FindBy (xpath = "//input[@name= 'country']/following::div[1]")
    private WebElement countryField;
    @FindBy (xpath = "//input[@data-title= 'Город']/following::div[1]")
    private WebElement cityField;
    @FindBy (css = "div[class='select lk-cv-block__input lk-cv-block__input_full js-lk-cv-custom-select']")
    private WebElement englishLvlField;
    @FindBy (css = "#id_ready_to_relocate_1")
    private WebElement readyToRelYesInput;
    @FindBy(xpath = "//input[@title = 'Полный день']")
    private WebElement scheduleFulldayInput;
    @FindBy(xpath =" //input[@title = 'Гибкий график']")
    private WebElement scheduleAgileInput;
    @FindBy(xpath = "//input[@title = 'Удаленно']")
    private WebElement scheduleRemoteInput;

    // КОНТАКТНАЯ ИНФОРМАЦИЯ.
    private static ContactMethodsData contactMethodsData1 = null;
    private static ContactMethodsData contactMethodsData2 = null;
    private static String contactMethodValue1 = "";
    private static String contactMethodValue2 = "";
    private static String contactMethodSettedLocator1 = "";
    private static String contactMethodSettedLocator2 = "";

    // ДРУГОЕ.
    private static GendersData gendersData = null;
    private static String choiceGenderLocator = "//option[@value='%s']";
    private static String company = "";
    private static String jobtitle = "";
    @FindBy (id = "id_company")
    private WebElement companyField;
    @FindBy(id = "id_work")
    private WebElement jobtitleField;

    // ОПЫТ РАЗРАБОТКИ.
    private static TechStackData techStackData = null;
    private static DevExpValuesData devExpValueData = null;
    private static String choiceTechStackLocator = "//option[normalize-space()='%s']";
    private static String choiceDevExpLocator = "//select[@id='id_experience-0-level']//option[contains(text(),'%s')]";
    @FindBy(id = "id_experience-0-experience")
    private WebElement techStackField;
    @FindBy(id = "id_experience-0-level")
    private WebElement devExpValueField;

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

        clearAndSend(fNameField, firstName);
        clearAndSend(lNameField, lastName);
        clearAndSend(fNameLatinField, firstNameLatin);
        clearAndSend(lNameLatinField, lastNameLatin);
        clearAndSend(blogNameField, blogName);
        clearAndSend(dateOfBirthField, birthDate);

        // ОСНОВНАЯ ИНФОРМАЦИЯ.

        // Выбор страны и города.
        this.cityData = CitiesData.values()[faker.random().nextInt(CitiesData.values().length)];
        this.selectCity(cityData);

        // Выбор уровня английского.
        this.englishLvlsData = EnglishLvlsData.values()[faker.random().nextInt(EnglishLvlsData.values().length)];
        this.selectEnglishLvl(englishLvlsData);

        // Выбор готовности к переезду.

        String workRelTemplLocator = "//span[contains(@class,'radio__label')][contains(text(),'%s')]";
        if (readyToRelYesInput.isSelected()) {
            $(By.xpath(String.format(workRelTemplLocator, "Нет"))).click();
            readyToRelFlag = false;
        } else {
            $(By.xpath(String.format(workRelTemplLocator, "Да"))).click();
            readyToRelFlag = true;
        }

        // Выбор формата работы.
        String workTypeTemplLocator = "//span[contains(text(), '%s')]";
        $(By.xpath(String.format(workTypeTemplLocator, "Полный день"))).click();
        scheduleFulldayFlag = scheduleFulldayInput.isSelected();

        $(By.xpath(String.format(workTypeTemplLocator, "Гибкий график"))).click();
        scheduleAgileFlag = scheduleAgileInput.isSelected();

        $(By.xpath(String.format(workTypeTemplLocator, "Удаленно"))).click();
        scheduleRemoteFlag = scheduleRemoteInput.isSelected();

        // КОНТАКТНАЯ ИНФОРМАЦИЯ.

        contactMethodsData1 = ContactMethodsData.values()[faker.random().nextInt(ContactMethodsData.values().length)];
        contactMethodValue1 = faker.internet().emailAddress();
        this.addContactMethod(contactMethodsData1, contactMethodValue1);
        contactMethodSettedLocator1 = String.format("//div[contains(text(),'%s')]", contactMethodsData1.getName());

        do {
            contactMethodsData2 = ContactMethodsData.values()[faker.random().nextInt(ContactMethodsData.values().length)];
        } while (contactMethodsData2 == contactMethodsData1);

        contactMethodValue2 = faker.phoneNumber().cellPhone();
        this.addContactMethod(contactMethodsData2, contactMethodValue2);
        contactMethodSettedLocator2 = String.format("//div[contains(text(),'%s')]", contactMethodsData2.getName());

        // ДРУГОЕ.

        // Выбор пола.
        gendersData = GendersData.values()[faker.random().nextInt(GendersData.values().length)];
        this.selectGender(gendersData);

        // Компания.
        company = faker.company().name();
        clearAndSend(companyField, company);

        // Должность.
        jobtitle = faker.company().profession();
        clearAndSend(jobtitleField, jobtitle);

        // ОПЫТ РАЗРАБОТКИ.

        this.techStackData = TechStackData.values()[faker.random().nextInt(TechStackData.values().length)];
        this.devExpValueData = DevExpValuesData.values()[faker.random().nextInt(DevExpValuesData.values().length)];
        this.selectDevExp(techStackData, devExpValueData);
    }

    public void saveData () {
        $(By.cssSelector("button[title='Сохранить и заполнить позже']")).click();
    }

    public void checkData () {

        // ИМЯ, ФАМИЛИЯ, ДАТА РОЖДЕНИЯ.

        Assertions.assertEquals(firstName, fNameField.getAttribute("value"));
        Assertions.assertEquals(lastName, lNameField.getAttribute("value"));
        Assertions.assertEquals(firstNameLatin, fNameLatinField.getAttribute("value"));
        Assertions.assertEquals(lastNameLatin, lNameLatinField.getAttribute("value"));
        Assertions.assertEquals(blogName, blogNameField.getAttribute("value"));
        Assertions.assertEquals(birthDate, dateOfBirthField.getAttribute("value"));

        // ОСНОВНАЯ ИНФОРМАЦИЯ.

        Assertions.assertEquals(cityData.getCountryData().getName(), countryField.getText());
        Assertions.assertEquals(cityData.getName(), cityField.getText());
        Assertions.assertEquals(englishLvlsData.getName(), englishLvlField.getText());
        Assertions.assertEquals(readyToRelFlag, readyToRelYesInput.isSelected());
        Assertions.assertEquals(scheduleFulldayFlag, scheduleFulldayInput.isSelected());
        Assertions.assertEquals(scheduleAgileFlag, scheduleAgileInput.isSelected());
        Assertions.assertEquals(scheduleRemoteFlag, scheduleRemoteInput.isSelected());

        // КОНТАКТНАЯ ИНФОРМАЦИЯ.

        $(By.xpath(contactMethodSettedLocator1));
        $(By.xpath(contactMethodSettedLocator2));

        // Используется сортировка, т.к. после сохранения и открытия страницы, контакты сортируются по типу в алфавитном порядке.
        TreeMap<String, String> sortedContacts = new TreeMap<>();
        sortedContacts.put(String.valueOf(contactMethodsData1), contactMethodValue1);
        sortedContacts.put(String.valueOf(contactMethodsData2), contactMethodValue2);

        Assertions.assertEquals(sortedContacts.get(sortedContacts.keySet().toArray()[0]), $(By.id("id_contact-0-value")).getAttribute("value"));
        Assertions.assertEquals(sortedContacts.get(sortedContacts.keySet().toArray()[1]), $(By.id("id_contact-1-value")).getAttribute("value"));

        // ДРУГОЕ.

        Assertions.assertTrue($(By.xpath(String.format(choiceGenderLocator, gendersData.getName()))).isSelected());
        Assertions.assertEquals(company, companyField.getAttribute("value"));
        Assertions.assertEquals(jobtitle, jobtitleField.getAttribute("value"));

        // ОПЫТ РАЗРАБОТКИ.

        Assertions.assertTrue($(By.xpath(String.format(choiceTechStackLocator, techStackData.getName()))).isSelected());
        Assertions.assertTrue($(By.xpath(String.format(choiceDevExpLocator, devExpValueData.getName()))).isSelected());

        // Сброс полей контактов и опыта разработки

        $(By.xpath("(//button[contains(@type,'button')][contains(text(),'Удалить')])[2]")).click();
        $(By.xpath("(//button[contains(@type,'button')][contains(text(),'Удалить')])[4]")).click();
        $(By.cssSelector(".experience-row__remove.ic-close.js-formset-delete")).click();
        this.saveData();
    }

    private void selectCity(ICitiesData cityData) {
        countryField.click();
        String countryDropdownSelector = String.format("button[title='%s']", cityData.getCountryData().getName());
        $(By.cssSelector(countryDropdownSelector)).click();

        StandardWaiters cityWaiter = new StandardWaiters(driver, 1);
        cityWaiter.waitForCondition(ExpectedConditions.attributeToBe(cityField, "disabled", "disabled"));
        cityWaiter.waitForCondition(ExpectedConditions.not(ExpectedConditions.attributeToBe(cityField, "disabled", "disabled")));
        cityField.click();
        String cityDropdownSelector = String.format("button[title='%s']", cityData.getName());
        standardWaiters.waitForElementVisible(By.cssSelector(cityDropdownSelector));
        $(By.cssSelector(cityDropdownSelector)).click();
    }

    private void selectEnglishLvl(EnglishLvlsData englishLvlsData) {
        englishLvlField.click();
        String englishLvlDropdownLocator = String.format("//button[contains(@title,'%s')]", englishLvlsData.getName());
        $(By.xpath(englishLvlDropdownLocator)).click();
    }

    private void addContactMethod(ContactMethodsData contactMethodsData, String contactFieldValue) {

        try{
            $(By.xpath("//span[@class='placeholder']")).click();
        } catch (NoSuchElementException e) {
            $(By.xpath("//button[contains(text(),'Добавить')]")).click();
            $(By.xpath("//span[@class='placeholder']")).click();
        }

        String contactMethodDropdownLocator = String.format("(//button[@data-value='%s'])[last()]", contactMethodsData.getName().toLowerCase());
        standardWaiters.waitForElementVisible(By.xpath(contactMethodDropdownLocator));

        $(By.xpath(contactMethodDropdownLocator)).click();

        WebElement contactField = $(By.xpath("(//input[@class ='input input_straight-top-left input_straight-bottom-left lk-cv-block__input lk-cv-block__input_9 lk-cv-block__input_md-8'])[last()]"));
        clearAndSend(contactField, contactFieldValue);
    }

    private void selectGender(GendersData gendersData) {
        $(By.id("id_gender")).click();
        $(By.xpath(String.format(choiceGenderLocator, gendersData.getName()))).click();
    }

    private void selectDevExp(TechStackData techStackData, DevExpValuesData devExpValuesData) {
        $(By.cssSelector("a[title='Добавить']")).click();

        //Выбор технологии.
        techStackField.click();
        $(By.xpath(String.format(choiceTechStackLocator, techStackData.getName()))).click();

        // Выбор опыта.
        devExpValueField.click();
        $(By.xpath(String.format(choiceDevExpLocator, devExpValuesData.getName()))).click();
    }
}