import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.BookingPage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Juan on 23/10/2017.
 */
public class BookingFormSteps {

    private String URL = "http://hotel-test.equalexperts.io/";
    Random rand = new Random();
    //Since the form lets us use numbers as names, we will use these three to clearly identify the user we are creating
    int nFirst = rand.nextInt(50000) + 1;
    private String firstName = Integer.toString(nFirst);
    int nLast = rand.nextInt(50000) + 1;
    private String lastName = Integer.toString(nLast);
    int nprice = rand.nextInt(50000) + 1;
    private String price = Integer.toString(nprice);
    Date date = new Date();
    String checkInDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
    String checkOutDate = "2017-11-12";
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }

    @Given("^the user is in the booking page$")
    public void isInBookingPage() throws Throwable {
        driver.get(URL);
    }

    @When("^he enters his personal data$")
    public void fillsTheForm() throws Throwable {
        BookingPage bp = new BookingPage(driver);

        bp.setFirstNameText(firstName)
                .setLastNameText(lastName)
                .setPriceText(price)
                .setCheckInCalendar()
                .setCheckOutCalendar(checkOutDate)
                .clickSave();
    }

    @Then("^the data should be visible$")
    public void theDataShouldBeVisible() throws Throwable {
        BookingPage bp = new BookingPage(driver);
        Assert.assertThat("first name is correct", bp.getLastFirstName(), is(firstName));
        Assert.assertThat("last name is correct", bp.getLastLastName(), is(lastName));
        Assert.assertThat("price is correct", bp.getLastPrice(), is(price));
        Assert.assertThat("deposit is true", bp.getLastDeposit(), is(true));
        Assert.assertThat("check in date is correct", bp.getLastCheckInDate(), is(checkInDate));
        Assert.assertThat("check out date is correct", bp.getLastCheckOutDate(), is(checkOutDate));
    }

    @Given("^the user has made a booking$")
    public void theUserHasMadeABooking() throws Throwable {
        isInBookingPage();
        fillsTheForm();
    }

    @When("^he clicks the delete button$")
    public void heClicksTheDeleteButton() throws Throwable {
        BookingPage bp = new BookingPage(driver);
        bp.clickLastDeleteButton();
    }

    @Then("^the booking should be removed$")
    public void theBookingShouldBeRemoved() throws Throwable {
        BookingPage bp = new BookingPage(driver);
        Assert.assertTrue(!bp.getPageSource().contains(firstName));
    }
}
