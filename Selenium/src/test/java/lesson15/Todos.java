package lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import lesson16.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners(tools.Listeners.class)
public class Todos {

    WebDriver webDriver;
    ToDoPage toDoPage;

    String[] tripArr = {"Passport", "Phone_charger", "Euros", "EU_adapters"
            , "Flip_flops", "Water_bottle", "Headphones", "Travel_Blanket"
            , "Travel_Pillow", "Eye_Mask", "Earplugs", "Tissues", "Snacks", "Empty_Water_Bottle"
            , "Camera", "Passport/Visa/ID", "Gum", "Credit/ATM_cards", "Insurance_Cards"};


    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://todomvc.com/examples/react/#/");
        webDriver.manage().window().maximize();
        toDoPage = PageFactory.initElements(webDriver, ToDoPage.class);
    }

    @Test(priority = 1)
    @Description("adding some new sentences into the to do list web")
    public void testCreatesNewToDoList() {
        toDoPage.setNew_Elements(tripArr);
    }

    @Test(priority = 2)
    @Description("deleting some items from todo list web")
    public void testDeleteItemFromToDoList() {
        Actions action = new Actions(webDriver);
        toDoPage.deleteItem_fromToDoList(action);
    }

    @Test(priority = 3)
    @Description("edit the items from the todolist web and write something else")
    public void testEditItemFromToDoList() {
        Actions action = new Actions(webDriver);
        toDoPage.editItem_FromToDoList(action);
    }

    @Test(priority = 4)
    @Description("checking all missions")
    public void testCheckToCompleted() {
        Actions action = new Actions(webDriver);
        toDoPage.checkTo_Completed(action);
    }

    @Test(priority = 5)
    @Description("counts how much we have from all other mission like active completed and all missions")
    public void testFilterMissions() {
        toDoPage.filter_Missions();
    }

    @Test(priority = 6)
    @Description("if there are a completed missions delete them")
    public void testDeleteAllCompletedMissions() {
        Actions action = new Actions(webDriver);
        toDoPage.deleteAll_CompletedMissions(action);
    }

    @AfterClass
    public void endSession() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.quit();
    }
}
