package lesson20.reqres.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ReqResPage {

    @FindBy(how = How.XPATH, using = "//*[@id=\"console\"]/div[2]/div[1]/p/strong/a/span")
    private WebElement allUsersListButton;

    public WebElement getButton(){
        return allUsersListButton;
    }
}
