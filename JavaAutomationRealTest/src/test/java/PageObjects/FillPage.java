package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;
//todo:change name of the class
public class FillPage {

    //todo:change names
    private WebElement name2, name3;


    //todo:put path / type / and name for the value type
    @FindBy(how = How.CLASS_NAME, using = "")
    private WebElement name1;

    @FindBy(how = How.XPATH, using = "")
    private List<WebElement> list1;

    @FindBy(how = How.XPATH, using = "")
    private List<WebElement> list2;



    //todo:give real name and params for this methods
    public void some_methodThatDoThingsWithFindBy1(String[] tripArr){

    }
    public void some_methodThatDoThingsWithFindBy2(Actions action){

    }
    public void some_methodThatDoThingsWithFindBy3(Actions action){

    }
    public void some_methodThatDoThingsWithFindBy4(Actions action){

    }
    public void some_methodThatDoThingsWithFindBy5(){

    }
    public void some_methodThatDoThingsWithFindBy6(Actions action){

    }

}
