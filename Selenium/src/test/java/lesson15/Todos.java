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

/**
 * //1
 * דבר ראשון למצוא שדה טקסט של todo ולהכניס לתוכו keys
 * <p>
 * ללחוץ enter בעזרת
 * WebElement textbox = driver.findElement(By.id("idOfElement"));
 * textbox.sendKeys(Keys.ENTER);
 * //2
 * mouse hover
 * ואז click בשביל ללחוץ על האיקס למחיקה
 * <p>
 * //3
 * mouse hover לתיבת הטקסט doubleClick
 * ואז clear
 * וכתיבת משימה חדשה
 * או
 * רק clear לתיבת הטקסט
 * וכתיבת משימה חדשה
 * <p>
 * //4
 * נוסיף mouse hover  נלחץ על העיגול בשביל לסמן ✔ כ Completed
 * <p>
 * //5
 * בדיקת פילטור עם אסרט
 * <p>
 * //6
 * אם completed מחק
 **/

public class Todos {

    WebDriver webDriver;

    String[] tripArr = {"Passport", "Phone charger / portable phone charger", "Euros", "EU adapters"
                        , "Flip flops (for the hostel bathrooms)", "Water bottle", "Headphones", "Travel Blanket"
                        ,"Travel Pillow", "Eye Mask", "Earplugs" , "Tissues", "Snacks", "Empty Water Bottle"
                        ,"Camera", "Passport/Visa/ID", "Gum", "Cash", "Credit/ATM cards", "Insurance Cards"};


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
        WebElement dClickItem;

        Actions action = new Actions(webDriver);
        List<WebElement> itemsList= webDriver.findElements(By.xpath("//section/ul/li"));
        for (int i = 0 ; i < itemsList.size() ; i+=2){

            item = itemsList.get(i);
            dClickItem = itemsList.get(i).findElement(By.tagName("label"));

            action.moveToElement(item).build().perform();
            action.doubleClick(dClickItem).build().perform();
            item.clear();
        }

    }

    @AfterClass
    public void endSession() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.quit();
    }
}
