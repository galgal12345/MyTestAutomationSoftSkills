package lesson14;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StaticProvider {

    @DataProvider(name = "data-provider")
    public static Object[][] createData() {

        return new Object[][]{

                {"Israel", "Israel"},
                {"Automation", "Automation"},
                {"BlahBlah", "Search results"}
        };
    }


}