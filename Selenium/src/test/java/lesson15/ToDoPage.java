package lesson15;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ToDoPage {

    private WebElement item, destroyItem ;

    @FindBy(how = How.CLASS_NAME, using = "new-todo")
    private WebElement textBox;
    @FindBy(how = How.XPATH, using = "//section/ul/li")
    private List<WebElement> itemsList;
    @FindBy(how = How.XPATH, using = "//section/ul/li[@class='completed']")
    private List<WebElement> itemsCompleted;

    public void setNew_Elements(String[] tripArr){

        for (int i = 0; i < tripArr.length; i++) {
           textBox.sendKeys(tripArr[i]);
           textBox.sendKeys(Keys.ENTER);
        }

    }

    public void deleteItem_fromToDoList(Actions action){


        for (int i = 0 ; i < itemsList.size() ; i+=2){
            item = itemsList.get(i);
            destroyItem = itemsList.get(i).findElement(By.className("destroy"));
            action.moveToElement(item).build().perform();
            action.click(destroyItem).build().perform();

        }
    }

    public void editItem_FromToDoList(Actions action){

        for (int i = 0 ; i < itemsList.size() ; i+=2){
            item = itemsList.get(i);
            action.moveToElement(item).build().perform();
            action.doubleClick(item).build().perform();
            action.contextClick(item).sendKeys(Keys.BACK_SPACE)
                    .sendKeys("------------")
                    .sendKeys(Keys.RETURN).build().perform();

        }
    }

    public void checkTo_Completed(Actions action){

        for (int i = 0 ; i < itemsList.size() ; i+=2)
            action.moveToElement(itemsList.get(i)).click(itemsList.get(i).findElement(By.className("toggle"))).build().perform();
    }

    public void filter_Missions(){

        System.out.println(itemsList.size());
        System.out.println(itemsCompleted.size());
        System.out.println(itemsList.size() - itemsCompleted.size());

    }

    public void deleteAll_CompletedMissions(Actions action){

        for (int i = 0; i < itemsList.size(); i++) {
            item = itemsList.get(i);
            destroyItem = itemsList.get(i).findElement(By.className("destroy"));
            action.moveToElement(item).build().perform();
            action.click(destroyItem).build().perform();
        }
    }


}
