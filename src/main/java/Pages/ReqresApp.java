package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReqresApp {
    WebDriver driver;

    public ReqresApp(WebDriver driver)
    {
        this.driver = driver
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="elementId")
    WebElement element;
}
