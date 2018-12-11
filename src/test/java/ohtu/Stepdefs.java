package ohtu;

import com.gargoylesoftware.htmlunit.WebClient;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

        this.driver = new HtmlUnitDriver() {
            @Override
            protected WebClient getWebClient() {
                WebClient webclient = super.getWebClient();
                webclient.getOptions().setCssEnabled(false);    // disable all bootstrap related css errors
                return webclient;
            }
        };

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
        driver.get("http://localhost:" + 8080 + "/books/new");
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.id("title"));
        element.sendKeys(bookname);
        element = driver.findElement(By.id("author"));
        element.sendKeys("Anonymous");
        element = driver.findElement(By.id("isbn"));
        element.sendKeys("123456789");
        element = driver.findElement(By.id("submit"));
        element.click();
        Thread.sleep(1000);
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
        element = driver.findElement(By.xpath("//*[@id='submit']"));
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
        Thread.sleep(1000);
    }

    @Then("^the user should be at page \"([^\"]*)\"$")
    public void the_user_should_be_at_page(String page) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals("http://localhost:8080/" + page, driver.getCurrentUrl());
    }

    @Given("^book \"([^\"]*)\" exist in database$")
    public void book_exist_in_database(String bookname) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get("http://localhost:" + 8080 + "/" + "books/new");
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.id("title"));
        element.sendKeys(bookname);
        element = driver.findElement(By.id("author"));
        element.sendKeys("Anonymous");
        element = driver.findElement(By.id("isbn"));
        element.sendKeys("123456789");
        element = driver.findElement(By.id("submit"));
        element.click();
        Thread.sleep(1000);
    }

    @When("^changing book title to \"([^\"]*)\"$")
    public void changing_book_title_to(String newBookTitle) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WebElement element = driver.findElement(By.id("title"));
        element.clear();
        element.sendKeys(newBookTitle);
        element = driver.findElement(By.id("submit"));
        element.click();
        Thread.sleep(1000);
    }

    @When("^button \"([^\"]*)\" is pressed")
    public void pressing_button_on_page_by_name(String buttonName) throws Throwable {
        WebElement element = driver.findElement(By.xpath("//*[@id='submit'][@value='" + buttonName + "']"));
        element.click();
        Thread.sleep(1000);
    }

    @Then("^the page should not list book \"([^\"]*)\"")
    public void page_should_not_list_book(String bookName) throws Throwable {
        //System.out.println(driver.getCurrentUrl());
        assertFalse(driver.getPageSource().contains(bookName));
    }

    @When("^user searches books by keyword \"([^\"]*)\"$")
    public void user_searches_books_by_keyword(String keyword) throws Throwable {
        WebElement element = driver.findElement(By.id("searchfield"));
        element.clear();
        element.sendKeys(keyword);
        element = driver.findElement(By.id("search"));
        element.click();
        Thread.sleep(500);
    }

    @Given("^the user is at the new blog page$")
    public void the_user_is_at_the_new_blog_page() throws Throwable {
        driver.get("http://localhost:" + 8080 + "/" + "blogs/new");
        Thread.sleep(500);
    }

    @Given("^the user is at the blog listing page$")
    public void the_user_is_at_the_blog_listing_page() throws Throwable {
        driver.get("http://localhost:" + 8080 + "/blogs");
        Thread.sleep(500);
    }

    @Given("^blog with title \"([^\"]*)\", Writer \"([^\"]*)\" and description \"([^\"]*)\" exists in database$")
    public void blog_exisists(String title, String writer, String description) throws Throwable {
        driver.get("http://localhost:" + 8080 + "/" + "blogs/new");
        Thread.sleep(500);
        WebElement element = driver.findElement(By.id("title"));
        element.sendKeys(title);
        element = driver.findElement(By.id("writer"));
        element.sendKeys(writer);
        element = driver.findElement(By.id("description"));
        element.sendKeys(description);
        element = driver.findElement(By.xpath("//*[@id='submit']"));
        element.click();
        Thread.sleep(500);
    }

    @When("^blog with title \"([^\"]*)\", Writer \"([^\"]*)\" and description \"([^\"]*)\" is created$")
    public void blog_is_created(String title, String writer, String description) throws Throwable {
        WebElement element = driver.findElement(By.id("title"));
        element.sendKeys(title);
        element = driver.findElement(By.id("writer"));
        element.sendKeys(writer);
        element = driver.findElement(By.id("description"));
        element.sendKeys(description);
        element = driver.findElement(By.xpath("//*[@id='submit']"));
        element.click();
        Thread.sleep(500);
    }

    @When("^requesting individual blog page with id \"([^\"]*)\"$")
    public void requesting_individual_blog_page_with_id(String id) throws Throwable {
        driver.get("http://localhost:" + 8080 + "/blogs/" + id);
        Thread.sleep(500);
    }

    @When("^changing blog title to \"([^\"]*)\"$")
    public void changing_blog_title_to(String newTitle) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WebElement element = driver.findElement(By.id("title"));
        element.clear();
        element.sendKeys(newTitle);
        element = driver.findElement(By.id("submit"));
        element.click();
        Thread.sleep(500);
    }

    @Then("^the page should list blog \"([^\"]*)\"$")
    public void the_page_should_list_blog(String blogName) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(driver.getPageSource().contains(blogName));
    }

    @When("^button with id \"([^\"]*)\" is pressed$")
    public void button_with_id_is_pressed(String id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WebElement element = driver.findElement(By.id(id));
        element.click();
        Thread.sleep(500);
    }

    @When("^user searches blogs by keyword \"([^\"]*)\"$")
    public void user_searches_blogs_by_keyword(String keyword) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WebElement element = driver.findElement(By.id("searchfield"));
        element.clear();
        element.sendKeys(keyword);
        element = driver.findElement(By.id("search"));
        element.click();
        Thread.sleep(500);
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
