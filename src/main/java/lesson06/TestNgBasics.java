package lesson06;

import org.testng.annotations.*;

public class TestNgBasics {

    @BeforeClass()
    public void testOne(){
        System.out.println("Hello BeforeClass");
    }
    @BeforeMethod
    public void testTwo(){
        System.out.println("Hello BeforeMethod");
    }
    @Test
    public void testThree(){
        System.out.println("Hello Test1");
    }
    @Test
    public void testFour(){
        System.out.println("Hello Test2");
    }
    @AfterMethod
    public void testFive(){
        System.out.println("Hello AfterMethod");
    }
    @AfterClass
    public void testSix(){
        System.out.println("Hello AfterClass");
    }

}
