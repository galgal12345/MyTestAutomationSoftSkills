package lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


public class Todos {

    WebDriver webDriver;

    String[] tripArr = {"Passport", "Phone_charger", "Euros", "EU_adapters"
                        , "Flip_flops", "Water_bottle", "Headphones", "Travel_Blanket"
                        ,"Travel_Pillow", "Eye_Mask", "Earplugs" , "Tissues", "Snacks", "Empty_Water_Bottle"
                        ,"Camera", "Passport/Visa/ID", "Gum",  "Credit/ATM_cards", "Insurance_Cards"};


    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://todomvc.com/examples/react/#/");
        webDriver.manage().window().maximize();

    }

    @Test(priority = 1)
    public void testCreatesNewToDoList(){


        WebElement textbox = webDriver.findElement(By.className("new-todo"));
        for (int i = 0; i < tripArr.length; i++) {
            webDriver.findElement(By.className("new-todo")).sendKeys(tripArr[i]);
            textbox.sendKeys(Keys.ENTER);
        }
    }

    @Test(priority = 2)
    public void testDeleteItemFromToDoList() {
        WebElement item ;
        WebElement destroyItem ;

        Actions action = new Actions(webDriver);
        List<WebElement> itemsList= webDriver.findElements(By.xpath("//section/ul/li"));
        for (int i = 0 ; i < itemsList.size() ; i+=2){

            item = itemsList.get(i);
            destroyItem = itemsList.get(i).findElement(By.className("destroy"));

            action.moveToElement(item).build().perform();
            action.click(destroyItem).build().perform();
        }
    }

    @Test(priority = 3)
    public void testEditItemFromToDoList(){

        WebElement item ;

        Actions action = new Actions(webDriver);
        List<WebElement> itemsList= webDriver.findElements(By.xpath("//section/ul/li"));
        for (int i = 0 ; i < itemsList.size() ; i+=2){

            item = itemsList.get(i);
            action.moveToElement(item).build().perform();
            action.doubleClick(item).build().perform();
            action.contextClick(item).sendKeys(Keys.BACK_SPACE)
                                    .sendKeys("------------")
                                    .sendKeys(Keys.RETURN).build().perform();

        }
    }

    @Test(priority = 4)
    public void testCheckToCompleted() {
        Actions action = new Actions(webDriver);
        List<WebElement> itemsList= webDriver.findElements(By.xpath("//section/ul/li"));
        for (int i = 0 ; i < itemsList.size() ; i+=2)
            action.moveToElement(itemsList.get(i)).click(itemsList.get(i).findElement(By.className("toggle"))).build().perform();
    }

    @Test(priority = 5)
    public void testFilterMissions() {

        WebElement dataWidget = webDriver.findElement(By.xpath("//section[@class='main']"));
        List<WebElement> itemsAll= dataWidget.findElements(By.xpath("//section/ul/li"));
        System.out.println(itemsAll.size());

        List<WebElement> itemsCompleted = dataWidget.findElements(By.xpath("//section/ul/li[@class='completed']"));
        System.out.println(itemsCompleted.size());

        System.out.println(itemsAll.size() - itemsCompleted.size());

    }

    @Test(priority = 6)
    public void testDeleteAllCompletedMissions() throws InterruptedException {

        WebElement item ;
        WebElement destroyItem ;
        Actions action = new Actions(webDriver);

        List<WebElement> itemsList= webDriver.findElements(By.xpath("//section/ul/li[@class='completed']"));
        for (int i = 0 ; i < itemsList.size() ; i++){

            item = itemsList.get(i);
            destroyItem = itemsList.get(i).findElement(By.className("destroy"));
            //Thread.sleep(1000);
            action.moveToElement(item).build().perform();
            //Thread.sleep(1000);
            action.click(destroyItem).build().perform();
        }


    }


    @AfterClass
    public void endSession() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.quit();
    }
}
