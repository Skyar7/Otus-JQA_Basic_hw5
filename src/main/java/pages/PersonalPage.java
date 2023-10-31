package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public PersonalPage(WebDriver driver) {
        super(driver);
    }

    public void fillData() {

        // Имя, фамилия, дата рождения.
        WebElement fNameField = driver.findElement(By.id("id_fname"));
        WebElement lNameField = driver.findElement(By.id("id_lname"));
        WebElement fNameLatinField = driver.findElement(By.id("id_fname_latin"));
        WebElement lNameLatinField = driver.findElement(By.id("id_lname_latin"));
        WebElement blogNameField = driver.findElement(By.id("id_blog_name"));
        WebElement dateOfBirthField = driver.findElement(By.name("date_of_birth"));

        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.firstNameLatin = firstName;
        this.lastNameLatin = lastName;
        this.blogName = firstName + " " + lastName;
        this.birthDate = LocalDate.of(1990, Month.OCTOBER, 12).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        clearAndSend(fNameField, firstName);
        clearAndSend(lNameField, lastName);
        clearAndSend(fNameLatinField, firstNameLatin);
        clearAndSend(lNameLatinField, lastNameLatin);
        clearAndSend(blogNameField, blogName);
        clearAndSend(dateOfBirthField, birthDate);

        // Основная информация.
        WebElement countryField = driver.findElement(By.name("country"));
        countryField.click();

    }
}