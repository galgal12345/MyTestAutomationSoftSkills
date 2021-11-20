package lesson15;

import com.opencsv.CSVWriter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class W3SchoolsTable {

    WebDriver webDriver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://www.w3schools.com/html/html_tables.asp");
    }

    @Test(priority = 1)
    public void testNumberOfRowsAndColumns() {
        System.out.println("the num of Row: " + webDriver.findElements(By.xpath("//table[@id='customers']/tbody/tr")).size());
        System.out.println("the num of Columns: " + webDriver.findElements(By.xpath("//table[@id='customers']/tbody/tr/th")).size());
    }

    @Test(priority = 2)
    public void testPrintCompanyNamesInEurope() {
        List<WebElement> europeCountries = webDriver.findElements(By.xpath("//table[@id='customers']/tbody/tr/td[text()='Germany' or text()='Austria' or text()='' or text()='UK' or text()='Italy']"));
        for (WebElement europeCountry : europeCountries) {
            System.out.println(europeCountry.getText());
        }
    }

    @Test(priority = 3)
    public void testGetAllCellsIntoCsvFile() throws IOException {

        List<String> cellsArrayList = new ArrayList<>();
        List<WebElement> titleCells = webDriver.findElements(By.xpath("//table[@id='customers']/tbody/tr/th"));
        List<WebElement> allOtherCells = webDriver.findElements(By.xpath("//table[@id='customers']/tbody/tr/td"));

        for (WebElement titleCell : titleCells)
            cellsArrayList.add(titleCell.getText());

        for (WebElement cell : allOtherCells)
            cellsArrayList.add(cell.getText());

        String[] cellsArray = new String[cellsArrayList.size()];
        cellsArray = cellsArrayList.toArray(cellsArray);

        String csv = "C:\\Users\\GIL\\IdeaProjects\\TestAutomtion\\Selenium\\csv-files\\cells.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));

        writer.writeNext(cellsArray);
        writer.close();

        System.out.println(cellsArrayList);
        System.out.println(cellsArrayList.size());
    }

    @AfterClass
    public void endSession() {
        webDriver.quit();
    }
}
