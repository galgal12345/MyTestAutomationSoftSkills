package lesson16;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FormPage {


    @FindBy(how = How.ID, using = "occupation")
    private WebElement txt_occupation;

    @FindBy(how = How.ID, using = "age")
    private WebElement txt_age;

    @FindBy(how = How.ID, using = "location")
    private WebElement txt_location;

    public void setTxt_fields(String occupation,String age, String location){

        txt_occupation.sendKeys(occupation);
        txt_age.sendKeys(age);
        txt_location.sendKeys(location);
    }

    @FindBy(how = How.XPATH, using = "//input[@type='button']")
    public WebElement btn_clickMe;
}
