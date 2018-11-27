package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {

    WebDriver driver;

    public Stepdefs() {
        File file;
        if (System.getProperty("os.name").matches("Mac OS X")) {
            file = new File("lib/macgeckodriver");
        } else {
            file = new File("lib/geckodriver");
        }
        String absolutePath = file.getAbsolutePath();
        System.setProperty("webdriver.gecko.driver", absolutePath);

        this.driver = new HtmlUnitDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("^the user is at the main page$")
    public void user_is_at_the_main_page() throws Throwable {
        driver.get("http://localhost:" + 8080 + "/");
        Thread.sleep(1000);
    }

    @Given("^book \"([^\"]*)\" exists in database$")
    public void book_exists_in_database(String bookname) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^\"([^\"]*)\" is shown$")
    public void is_shown(String arg1) throws Throwable {
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains(arg1));
    }

    @When("^the \"([^\"]*)\" link is clicked$")
    public void a_link_is_clicked(String linktext) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        clickLinkWithText(linktext);
    }

    @Then("^the page should have a title \"([^\"]*)\"$")
    public void page_should_should_contain(String title) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(title, driver.getTitle());
    }

    @Given("^the user is at the new book page$")
    public void the_user_is_at_the_new_book_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get("http://localhost:" + 8080 + "/" + "books/new");
        Thread.sleep(1000);
    }

    @When("^book \"([^\"]*)\" is created$")
    public void book_is_created(String bookname) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WebElement element = driver.findElement(By.id("title"));
        element.sendKeys(bookname);
        element = driver.findElement(By.id("author"));
        element.sendKeys("Anonymous");
        element = driver.findElement(By.id("isbn"));
        element.sendKeys("123456789");
        element = driver.findElement(By.id("submit"));
        element.click();
    }

    @Then("^the page should list book \"([^\"]*)\"$")
    public void the_page_should_list(String bookname) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(driver.getPageSource().contains(bookname));
    }

    @When("^requesting individual book page with id \"([^\"]*)\"$")
    public void requesting_individual_book_page_with_id(String id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get("http://localhost:" + 8080 + "/books/" + id);
    }

    private void clickLinkWithText(String text) {
        int trials = 0;
        while (trials++ < 5) {
            try {
                WebElement element = driver.findElement(By.linkText(text));
                element.click();
                break;
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }

}
