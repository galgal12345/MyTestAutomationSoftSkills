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

    String[] tripArr = {"Passport", "Phone_charger", "Euros", "EU_adapters"
                        , "Flip_flops", "Water_bottle", "Headphones", "Travel_Blanket"
                        ,"Travel_Pillow", "Eye_Mask", "Earplugs" , "Tissues", "Snacks", "Empty_Water_Bottle"
                        ,"Camera", "Passport/Visa/ID", "Gum", "Cash", "Credit/ATM_cards", "Insurance_Cards"};


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

        int countAll = 0;
        int countActive = 0;
        int countCompleted = 0;

        List<WebElement> itemsList= webDriver.findElements(By.xpath("//section/ul/li"));

        for (int i = 0; i < itemsList.size() ; i+=2)
            if (itemsList.get(i).findElement(By.xpath("//section/ul/li")).isDisplayed())
                countAll++;
        System.out.println(countAll);

        webDriver.findElement(By.xpath("//footer/ul/li[2]/a")).click();
        for (int i = 0; i < itemsList.size() ; i+=2)
            if (itemsList.get(i).findElement(By.xpath("//section/ul/li")).isDisplayed())
                countActive++;
        System.out.println(countActive);

        webDriver.findElement(By.xpath("/html/body/section/div/footer/ul/li[3]/a")).click();
        for (int i = 0; i < itemsList.size() ; i+=2)
            if (itemsList.get(i).findElement(By.xpath("//section/ul/li")).isDisplayed())
                countCompleted++;
        System.out.println(countCompleted);

    }


    @AfterClass
    public void endSession() throws InterruptedException {
        Thread.sleep(3000);
        //webDriver.quit();
    }
}
