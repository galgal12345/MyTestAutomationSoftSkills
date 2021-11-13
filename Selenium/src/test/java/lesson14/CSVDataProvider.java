package lesson14;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class CSVDataProvider {

    private static Object[][] data = null;

    private static FileReader getPath(String fileName) throws FileNotFoundException {
        return new FileReader(fileName);
    }

    private static void getData(String fileName) {

        try {
            data = new Object[3][2];
            CSVReader reader = new CSVReader(getPath(fileName));

            // this will load content into list
            List<String[]> li = reader.readAll();
            System.out.println("Total rows which we have is " + li.size());

            // create Iterator reference
            Iterator<String[]> i1 = li.iterator();

            // Iterate all values
            for (int j = 0 ; i1.hasNext() ; j++) {
                String[] str = i1.next();
                for (int i = 0; i < str.length; i++)
                    data[j][i] =  str[i];

            }

        } catch (Exception fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    public static Object[][] getCSVData(String fileName) {
        getData(fileName);
        return data;
    }
}

