package lesson16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(tools.Listeners.class)
public class PageObjects {

    WebDriver webDriver;
    LoginPage loginPage;
    FormPage formPage;
    ClickPage clickPage;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://atidcollege.co.il/Xamples/webdriveradvance.html");

        loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        formPage = PageFactory.initElements(webDriver, FormPage.class);
        clickPage = PageFactory.initElements(webDriver, ClickPage.class);

    }

    @Test(priority = 1)
    public void testLogin(){

        loginPage.setTxt_fields("selenium", "webdriver");
        loginPage.btn_submit.click();

    }

    @Test(priority = 2)
    public void testForm(){

        formPage.setTxt_fields("Test Automation Engineer", "27", "Acre");
        formPage.btn_clickMe.click();

    }

    @Test(priority = 3)
    public void testClick(){
        clickPage.onScreen();
    }

    @AfterClass
    public void endSession() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.quit();
    }
}
