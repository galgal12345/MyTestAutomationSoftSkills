package lesson11;

import static org.testng.Assert.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ExterrnalFiles {

    WebDriver webDriver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(getData("URL"));
    }

    @Test
    public void testBmi() {
        webDriver.findElement(By.id("weight")).sendKeys(getData("Weight"));
        webDriver.findElement(By.id("hight")).sendKeys(getData("Height"));
        webDriver.findElement(By.id("calculate_data")).click();

        String bmiExpectedResult = "21";
        String bmiActualResult =  webDriver.findElement(By.id("bmi_result")).getAttribute("value");
        assertEquals(bmiActualResult, bmiExpectedResult, "BMI result Not equal");

    }

    @AfterClass
    public void endSession() {
        webDriver.quit();
    }

    // Function
    public String getData(String nodeName) {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmlFile = new File("C:\\Users\\GIL\\IdeaProjects\\TestAutomtion\\Selenium\\Configuration\\Configuration.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        } catch (Exception e) {
            System.out.println("Exception in reading XML file: " + e);
        }
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }
}
