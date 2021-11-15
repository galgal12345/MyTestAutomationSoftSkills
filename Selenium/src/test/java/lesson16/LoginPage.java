package lesson16;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    @FindBy(how = How.XPATH, using = "//*[@id='contact_info_left']/form/input[1]")
    private WebElement txt_userName;

    @FindBy(how = How.XPATH, using = "//*[@id='contact_info_left']/form/input[2]")
    private WebElement txt_passWord;

    public void setTxt_fields(String userName,String passWord){

        txt_userName.sendKeys(userName);
        txt_passWord.sendKeys(passWord);
    }

    @FindBy(how = How.ID, using = "submit")
    public WebElement btn_submit;
}
