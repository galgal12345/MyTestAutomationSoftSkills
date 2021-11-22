package lesson21;

import org.testng.annotations.Test;

public class MyTestNG1 {

    @Test(groups = {"ishay"})
    public void test1(){
        System.out.println("MyTestNG1 i am test 1");
    }
    @Test(groups = {"ishay"})
    public void test2(){
        System.out.println("MyTestNG1 i am test 2");
    }
    @Test(groups = {"ishay","noam"})
    public void test3(){
        System.out.println("MyTestNG1 i am test 3");
    }
}
