package lesson06;

import org.testng.annotations.Test;

public class Gil {

    @Test(dependsOnMethods = {"testTwo"})
    public void testOne(){
        System.out.println("Hello Gil 1");
    }
    @Test
    public void testTwo(){
        System.out.println("Hello Gil 2");
    }
}
