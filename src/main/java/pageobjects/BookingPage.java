package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Juan on 23/10/2017.
 */
public class BookingPage {

    @FindBy(id = "firstname")
    private WebElement firstNameText;

    @FindBy(id = "lastname")
    private WebElement lastNameText;

    @FindBy(id = "totalprice")
    private WebElement priceText;

    @FindBy(id = "checkin")
    private WebElement checkInCalendar;

    @FindBy(id = "checkout")
    private WebElement checkOutCalendar;

    @FindBy(css = "input[value=\" Save \"]")
    private WebElement saveButton;

    @FindBy(css = "#bookings>.row:last-child>.col-md-2:nth-child(1)")
    private WebElement lastFirstName;

    @FindBy(css = "#bookings>.row:last-child>.col-md-2:nth-child(2)")
    private WebElement lastLastName;

    @FindBy(css = "#bookings>.row:last-child>.col-md-1>p")
    private WebElement lastPrice;

    @FindBy(css = "#bookings>.row:last-child>.col-md-2:nth-child(4)")
    private WebElement lastDeposit;

    @FindBy(css = "#bookings>.row:last-child>.col-md-2:nth-child(5)")
    private WebElement lastCheckin;

    @FindBy(css = "#bookings>.row:last-child>.col-md-2:nth-child(6)")
    private WebElement lastCheckout;

    @FindBy(css = "#bookings>.row:last-child>.col-md-1:nth-child(7)>input")
    private WebElement lastDeleteButton;

    //this should be changed if this consisted of more than 1 page.
    protected WebDriver driver;

    public BookingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public BookingPage setFirstNameText(String name) {
        firstNameText.sendKeys(name);
        return this;
    }

    public BookingPage setLastNameText(String name) {
        lastNameText.sendKeys(name);
        return this;
    }

    public BookingPage setPriceText(String price) {
        priceText.sendKeys(price);
        return this;
    }

    public BookingPage setCheckInCalendar() {
        checkInCalendar.click();
        checkInCalendar.sendKeys(Keys.RETURN);
        return this;
    }

    public BookingPage setCheckOutCalendar(String date) {
        checkOutCalendar.sendKeys(date);
        return this;
    }

    public BookingPage clickSave() {
        int tableSize = driver.findElements(By.cssSelector("#bookings>.row")).size();
        saveButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("#bookings>.row"), tableSize));
        return new BookingPage(driver);
    }

    public String getLastFirstName() {
        return lastFirstName.getText();
    }

    public String getLastLastName() {
        return lastLastName.getText();
    }

    public String getLastPrice() {
        return lastPrice.getText();
    }

    public boolean getLastDeposit() {
        return lastDeposit.getText().equals("true");
    }

    public String getLastCheckInDate() {
        return lastCheckin.getText();
    }

    public String getLastCheckOutDate() {
        return lastCheckout.getText();
    }

    public BookingPage clickLastDeleteButton() {
        int tableSize = driver.findElements(By.cssSelector("#bookings>.row")).size();
        lastDeleteButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("#bookings>.row"), tableSize));
        return new BookingPage(driver);
    }

    public String getPageSource() {
        return driver.getPageSource();
    }
}
