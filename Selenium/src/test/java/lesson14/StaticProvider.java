package lesson14;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StaticProvider {

    @DataProvider(name = "data-provider")
    public static Object[][] createData() {

        return CSVDataProvider.getCSVData("C:\\Users\\GIL\\IdeaProjects\\TestAutomtion\\Selenium\\Comma-separated-values-files\\data.csv");

    }

}

