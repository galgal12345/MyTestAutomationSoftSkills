package lesson21;

import org.testng.annotations.Test;

public class MyTestNG2 {

    @Test(groups = {"noam"})
    public void test1(){
        System.out.println("-------------------------");
        System.out.println("MyTestNG2 i am test 1");
    }
    @Test(groups = {"noam"})
    public void test2(){
        System.out.println("MyTestNG2 i am test 2");
    }
    @Test(groups = {"noam", "ishay"})
    public void test3(){
        System.out.println("MyTestNG2 i am test 3");
    }
}
