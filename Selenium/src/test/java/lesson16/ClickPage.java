package lesson16;

import static org.testng.Assert.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ClickPage {

    @FindBy(how = How.TAG_NAME, using = "button")
    private WebElement btn_clickMe;

    public void onScreen(){
        assertTrue(btn_clickMe.isDisplayed(), "Test Failed: btn_clickMe is NOT displayed");
    }
}
