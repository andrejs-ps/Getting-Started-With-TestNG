package baseclasses;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

public class UnitTestBaseClass {

    @BeforeSuite
    public void globalSetup(){
        System.out.println("Global setup - will run once for all tests");
    }

    @BeforeMethod
    public void globalBeforeMethodSetup(){
        System.out.println("This is global beforeMethod");
    }

    // don't use inheritance
    @DataProvider
    protected Object[][] invalidEmailProvider(){
        return new Object[][] {
                {""},
                {"johnemail.com"},
                {"john@emailcom"}
        };
    }
}
