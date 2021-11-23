package lesson20.reqres;

import io.qameta.allure.Description;
import lesson20.reqres.Extensions.CommonOps;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
@Listeners(tools.Listeners.class)
public class ReqRes extends CommonOps {

    @Test(priority = 1, description = "Verify page 1")
    @Description("This test checks that....")
    public void test1() {
        reqResPage.getButton().click();
        basicsResponseOperations("api/users?page=1", TimeUnit.MILLISECONDS, "Date");
    }
    @Test(priority = 2, dataProvider = "index-page-data",description = "Verify page 1")
    @Description("This test checks that....")
    public void test2(int indexDataProvider) {
        checkIdsWithDataProvider(indexDataProvider, "1");
    }
    @Test(priority = 3,description = "Verify page 1")
    @Description("This test checks that....")
    public void test3(){
        getOnePerson("3");
    }
    @Test(priority = 4,description = "Verify page 1")
    @Description("This test checks that....")
    public void test4(){
        postNewPerson("Gil", "Almuly");
    }
    @Test(priority = 5, description = "asdasd")
    @Description("asdasdasdasdasd")
    public void test5(){
        updatePerson("-----", "-------");
    }
    @Test(priority = 6, description = "asdasd")
    @Description("asdasdasdasdasd")
    public void test6(){

        deletePerson();

    }




}
