package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReqresApp {
    WebDriver driver;

    public ReqresApp(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath=".//div[@class='endpoints']/ul/li[1]")
    WebElement FirstGetElement;

    @FindBy(xpath=".//div[@class='endpoints']/ul/li[2]")
    WebElement SingleUserMethod;

    @FindBy(xpath="//pre[@data-key='output-response']")
    WebElement ResponseBody;

    public WebElement getFirstGetMethod(){
        return FirstGetElement;
    }
    public WebElement getSingleUserMethod(){
        return SingleUserMethod;
    }
    public WebElement getResponseBody(){
        return ResponseBody;
    }
}
