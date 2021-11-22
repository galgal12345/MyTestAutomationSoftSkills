package lesson19;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.io.IOException;

public class HtmlResponse {

    Document doc;

    @Test
    public void test01() throws IOException {
        doc = Jsoup.connect("https://www.ebay.com/").get();

        String height = "200";
        String width = "250";
        String txt = "All Categories";

        Element elem = doc.getElementById("gh-cat");
        Element elem1 = doc.getElementById("gh-logo");

        assertEquals(elem1.attr("height"), height);
        assertEquals(elem1.attr("width"), width);
        assertEquals(elem.text(), txt);
    }


}
