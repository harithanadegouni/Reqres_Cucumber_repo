package Stepdefinitions;
import Pages.ReqresApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;
import java.util.List;

public class HomePageStepDefinitions {
    WebDriver driver;
    List<WebElement> numberOfRequests;
    WebElement upgradeButton;
    ReqresApp pom;
    private static final Logger logger = LogManager.getLogger(HomePageStepDefinitions.class);

    @Given("I open the application")
    public void i_open_the_application() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\haritha.nadegouni\\Downloads\\Learning\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://reqres.in/");
        driver.manage().window().maximize();
        pom = new ReqresApp(driver);
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",pom.getFirstGetMethod() );
        logger.info("I opened the application");
    }
    @When("verify different request types on homepage")
    public void verify_different_request_types_on_homepage() {
        numberOfRequests=driver.findElements(By.xpath(".//div[@class='endpoints']/ul/li"));

        for (WebElement ele:numberOfRequests)
        {
            String reqType=ele.getAttribute("data-http");
            if(reqType.equalsIgnoreCase("get") || reqType.equalsIgnoreCase("post") || reqType.equalsIgnoreCase("put"))
            {
                logger.info("Different types of request methods exist in the application");
            }
        }
    }

    @Then("request types counts is {string}")
    public void request_types_counts_is(String count) {
        numberOfRequests=driver.findElements(By.xpath(".//div[@class='endpoints']/ul/li"));
        Assert.assertEquals(String.valueOf(numberOfRequests.size()),count);
        driver.close();
    }

    @When("I click on single user request method")
    public void click_on_single_user_method() {
        pom.getSingleUserMethod().click();
    }

    @Then("verify response after clicking on sigle user method")
    public void verify_response() {
        String responseCode =driver.findElement(By.cssSelector("span.response-code")).getText();
        Assert.assertEquals(responseCode,"200");
        String actualResponse="{\n" +
                "    \"data\": {\n" +
                "        \"id\": 2,\n" +
                "        \"email\": \"janet.weaver@reqres.in\",\n" +
                "        \"first_name\": \"Janet\",\n" +
                "        \"last_name\": \"Weaver\",\n" +
                "        \"avatar\": \"https://reqres.in/img/faces/2-image.jpg\"\n" +
                "    },\n" +
                "    \"support\": {\n" +
                "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                "    }\n" +
                "}";

        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(pom.getResponseBody()));

        String responseBody=pom.getResponseBody().getText();
        Assert.assertEquals(actualResponse,responseBody);
        driver.close();
    }
    @When("I verify Support Reqres button is displayed")
    public void verify_support_reqres_button_is_displayed() {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath(".//button/a")) );
        boolean isButtonExist=driver.findElement(By.xpath(".//button/a")).isDisplayed();
        Assert.assertTrue(isButtonExist);
    }

    @Then("Click on Support Reqres button")
    public void click_on_support_button() {
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//button/a"))));
        driver.findElement(By.xpath(".//button/a")).click();
    }

    @Then("Verify Support page displayed")
    public void verify_support_page_displayed() {
        boolean isHeaderExist=driver.findElement(By.id("support-heading")).isDisplayed();
        Assert.assertTrue(isHeaderExist);
        driver.close();
    }

    @When("I verify Upgrade button is displayed")
    public void i_verify_upgrade_button_is_displayed() {
        upgradeButton=driver.findElement(By.cssSelector("button#trigger-pro.large"));
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",upgradeButton );
        boolean isButtonExist=upgradeButton.isDisplayed();
        Assert.assertTrue(isButtonExist);
    }
    @Then("Click on Upgrade button")
    public void click_on_upgrade_button() {
        upgradeButton.click();
    }

    @Then("Verify Upgrade details page displayed")
    public void verify_upgrade_details_page_displayed() {
        boolean emailAddressTextField = driver.findElement(By.id("mce-EMAIL")).isDisplayed();
        Assert.assertTrue(emailAddressTextField);
        boolean subscribe = driver.findElement(By.id("mc-embedded-subscribe")).isDisplayed();
        Assert.assertTrue(subscribe);
        driver.close();
    }
}
