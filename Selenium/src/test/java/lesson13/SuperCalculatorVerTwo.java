package lesson13;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**מקס -> לחצן עם כיוון למטה לחץ עליו 90 פעם עד שתגיע ל 10
 ואז לוחצים על לחצן ה GO

 לוקחים את המספר הרנדומאלי מהמסך ושומרים אותו במשתנה

 בסעיף מספר 3 תשים את הGET עם הכתובת URL בכל טסט ספציפי

 את הטסט השני נהפוך לSTEP שיקבל את המספר הרנדומלי

 בשביל לסכם את המספר במכפלת המספרים הקטנים ממנו נצטרך
 להשתמש באותה הלולאה שהשתמשנו בשאלה הקודמת בסעיף הראשון רק שכאן הלולאה הולכת הפוך ובלי הסימן = בלולאה

 ונכניס את כל התוצאות לתוך מערך סכומים

 ואז נעבור שוב פעם על לולאה של הסכומים
 נשנה סימן ל +
 נכניס ערכים מהמערך SUM לתוך השדות
 ונסכם

 ונגדיר משתנה SUM שיסכם את התוצאה של המכפלות

 סעיף 5 נדפיס למסך את המספר הרנדומאלי ואת ואת הסכום
 **/
public class SuperCalculatorVerTwo {

    WebDriver webDriver;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://www.random.org/");

    }

    @Test(priority = 1)
    public void testRndOneToTen() throws InterruptedException {


        WebElement ifrm = webDriver.findElement(By.cssSelector("iframe[src='https://www.random.org/widgets/integers/iframe.php?title=True+Random+Number+Generator&buttontxt=Generate&width=160&height=230&border=on&bgcolor=%23FFFFFF&txtcolor=%23777777&altbgcolor=%23CCCCFFA&alttxtcolor=%23000000&defaultmin=1&defaultmax=100&fixed=off']"));
        webDriver.switchTo().frame(ifrm);
        webDriver.findElement(By.xpath("//input[contains(@id,'max')]")).clear();
        webDriver.findElement(By.xpath("//input[contains(@id,'max')]")).sendKeys("10");
        webDriver.findElement(By.xpath("//input[contains(@id,'button')]")).click();
        Thread.sleep(3000);
        String s = webDriver.findElement(By.xpath("//span[contains(@id,'result')]/center/span[1]")).getText();
        System.out.println("My Rnd: " + s);
        testUsingNum(s);

    }

    @Step
    public void testUsingNum(String s) throws InterruptedException {

        int num = Integer.parseInt(s);
        List<String> sumOfMutRnd = new ArrayList<>();
        webDriver.get("http://juliemr.github.io/protractor-demo/");

        webDriver.findElement(By.tagName("select")).click();
        webDriver.findElement(By.xpath("//option[4]")).click();

        String rightFieldsNumToStr;
        String leftFieldsNumToStr;


        for (int j = num-1; j > 0; j--) {
            rightFieldsNumToStr = "" + num;
            leftFieldsNumToStr = "" + j;
            webDriver.findElement(By.xpath("//input[1]")).sendKeys(rightFieldsNumToStr);
            webDriver.findElement(By.xpath("//input[2]")).sendKeys(leftFieldsNumToStr);
            webDriver.findElement(By.id("gobutton")).click();
            Thread.sleep(3000);
            sumOfMutRnd.add(webDriver.findElement(By.tagName("h2")).getText());
        }

        int sum = 0;
        for(int i = 0 ; i < sumOfMutRnd.size() ; i++){
            sum += Integer.parseInt(sumOfMutRnd.get(i));
        }
        System.out.println("the Sum Of Mutable Rnd number: " + sum);
    }

    @AfterClass
    public void endSession() {
        webDriver.quit();
    }
}
